package clinicMap.register;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;

@Repository
public class MemberDAO {

	private SessionFactory sessionFactory;

	@Autowired
	public MemberDAO(@Qualifier("sessionFactory") SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}
	
	//這裡叫MemberDAO，Y/N全部先做Member的部分就好，
	//Clinic 的部分，到時候再複製一份???
	
	public Object checkLogin(String type, String account, String pwd) {
		Session session = sessionFactory.getCurrentSession();
		String hqlStr = "";
		System.out.println(account+" "+type+" "+pwd);


		if(type.equals("member")) {
			hqlStr = "from Memberde where memberAccount = :account and memberPwd = :pwd";
			Query<Memberde> query = session.createQuery(hqlStr, Memberde.class);
			query.setParameter("account", account);
			query.setParameter("pwd", pwd);
			return query.uniqueResult();
		}
		
			hqlStr = "from Clinicchuder where clinicAccount = :account and clinicPwd = :pwd";
			Query<Clinicchuder> query = session.createQuery(hqlStr, Clinicchuder.class);
			query.setParameter("account", account);
			query.setParameter("pwd", pwd);
			return query.uniqueResult();
		
		


	}
		
	public void doRegisterM(Memberde m) {
		Session session = sessionFactory.getCurrentSession();		
		session.save(m);
	}

	public void doRegisterC(String account, String email){
		Session session = sessionFactory.getCurrentSession();
		String hqlStr = "update Clinicchuder set clinicEmail = :email where clinicAccount = :account";
		Query query = session.createQuery(hqlStr);
		query.setParameter("email", email);
		query.setParameter("account", account);
		query.executeUpdate();
	}
	
	public Memberde getInfoWithCode(String code) {
		//時間上的判定，要帶入給sql做，還是先抓出來，再controller裡面做??
		
		Session session = sessionFactory.getCurrentSession();
		String hqlStr = "from Memberde where memberVerifiedCode = :code";
		Query<Memberde> query = session.createQuery(hqlStr, Memberde.class);
		query.setParameter("code", code);
		
		System.out.println("Get Info with code");
		return query.uniqueResult();
	}
	
	//時間內驗證email，將狀態(memberStatus)改為 啟用(1)
	public void setActiveStatus(String code) {
		Session session = sessionFactory.getCurrentSession();
		String hqlStr = "update Memberde set memberStatus = :status where memberVerifiedCode = :code";
		Query query = session.createQuery(hqlStr);
		query.setParameter("status", "MS1");
		query.setParameter("code", code);
		query.executeUpdate();
		System.out.println("Verified Success");
	}
	
	//新版：用account修改 => 為了讓別的地方也可以用
	//驗證email超過時間，將驗證碼改掉(memberVerifiedCode)
	public void setActiveStatus(String codeNew, String account) {
		Session session = sessionFactory.getCurrentSession();
		String hqlStr = "update Memberde set memberVerifiedCode = :codeNew where memberAccount = :account";
		Query query = session.createQuery(hqlStr);
		query.setParameter("codeNew", codeNew);
		query.setParameter("account", account);		
		query.executeUpdate();
		System.out.println("verified out of time, had changed verified code");
	}
	
	//原版：用code修改
//	//驗證email超過時間，將驗證碼改掉(memberVerifiedCode)
//	public void setActiveStatus(String codeNew, String code) {
//		Session session = sessionFactory.getCurrentSession();
//		String hqlStr = "update Member set memberVerifiedCode = :codeNew where memberVerifiedCode = :code";
//		Query query = session.createQuery(hqlStr);
//		query.setParameter("codeNew", codeNew);
//		query.setParameter("code", code);		
//		query.executeUpdate();
//		System.out.println("verified out of time, had changed verified code");
//	}
	
	//目前先假定是 只有 Member的情況
//	public String getCodeWithAccount(String account){
//		Session session = sessionFactory.getCurrentSession();
//		String hqlStr = "from Memberde where memberAccount = :account";
//		Query<Memberde> query = session.createQuery(hqlStr, Memberde.class);
//		query.setParameter("account", account);
//		return query.uniqueResult().getMemberVerifiedCode();
//	}

	public void updateDeadline(String account, long newDeadline){
		Session session = sessionFactory.getCurrentSession();
		String hqlStr = "update Memberde set memberRegisterDeadline = :deadline where memberAccount = :account";
		Query query = session.createQuery(hqlStr);
		query.setParameter("deadline", newDeadline);
		query.setParameter("account", account);
		query.executeUpdate();
		System.out.println("deadline update");
	}
	
	//忘記密碼時，設定的暫時密碼
	public void setTempPwd(String account, String tempPwd) {
		Session session = sessionFactory.getCurrentSession();
		String hqlStr = "update Memberde set memberPwd = :tempPwd where memberAccount = :account";
		Query query = session.createQuery(hqlStr);
		query.setParameter("tempPwd", tempPwd);
		query.setParameter("account", account);
		query.executeUpdate();
		System.out.println("set temp Pwd");
	}

	//註冊時的 帳號是否重複
	public boolean checkAccountExistDao(String loginLevel, String account) {
		Session session = sessionFactory.getCurrentSession();
		String hqlStr = "";
		if(loginLevel.equals("member")) {
			hqlStr = "from Memberde where memberAccount = :account";
		}
		if(loginLevel.equals("clinic")) {
			hqlStr = "from Clinicchuder where clinicAccount = :account";
		}
		 
		Query query = session.createQuery(hqlStr);
		query.setParameter("account", account);
		if(query.uniqueResult()!=null) {
			return true;
		}
		return false;
	}
	
	//重新寄信時，email是否已經存在
	public boolean checkEmailExistDao(String email) {
		Session session = sessionFactory.getCurrentSession();
		String hqlStr = "from Memberde where memberEmail = :email";
		Query query = session.createQuery(hqlStr);
		query.setParameter("email", email);
		List list = query.list();
		
		return !list.isEmpty();
	}

	//members
	public List<Memberde> queryAllMember() {
		Session session = sessionFactory.getCurrentSession();
		Query<Memberde> query = session.createQuery("from Memberde", Memberde.class);
		return query.list();
	}

	public Object RememberMePwd(String type, String account) {
		Session session = sessionFactory.getCurrentSession();
		String hqlStr = "";
		
		if(type.equals("member")) {
			hqlStr = "from Memberde where memberAccount = :account";
		}
		if(type.equals("clinic")) {
			hqlStr = "from Clinicchuder where clinicAccount = :account";
		}
		
		Query query = session.createQuery(hqlStr);
		query.setParameter("account", account);
		
		return query.uniqueResult();
	}
	public boolean checkIdNumExistDao(String IdNum){
		Session session = sessionFactory.getCurrentSession();
		String hqlStr = "from Memberde where memberIdNumber = :IdNum";
		Query query = session.createQuery(hqlStr);
		query.setParameter("IdNum", IdNum);
		
		if(query.uniqueResult()!=null) {
			return true;
		}
		return false;
	}
	
	public void photoupload(String account, byte[] photo) {
		Session session = sessionFactory.getCurrentSession();
		String hqlStr = "update Memberde set memberPhoto = :photo where memberAccount = :account";
		Query query = session.createQuery(hqlStr);
		query.setParameter("photo", photo);
		query.setParameter("account", account);
		query.executeUpdate();
	}
	
	public boolean doubleCheckInfo(String account, String email) {
		Session session = sessionFactory.getCurrentSession();
		String hqlStr = "from Memberde where memberAccount = :account and memberEmail = :email";
		Query query = session.createQuery(hqlStr);
		query.setParameter("account", account);
		query.setParameter("email", email);
		if(query.uniqueResult()!= null) {
			return true;
		}
		return false;
	}
}
