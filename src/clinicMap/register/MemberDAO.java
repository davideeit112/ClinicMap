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
		
		if(type.equals("member")) {
			hqlStr = "from Member where memberAccount = :account and memberPwd = :pwd";
		}
		if(type.equals("clinic")) {
			hqlStr = "from Clinic where clinicAccount = :account and clinicPwd = :pwd";
		}
		
		Query<Member> query = session.createQuery(hqlStr, Member.class);
		query.setParameter("account", account);
		query.setParameter("pwd", pwd);
		System.out.println("acc:" + account);
		System.out.println("pwd:" + pwd);
		Member m = session.get(Member.class, 101);
		System.out.println("acc:" + m.getMemberAccount());
		System.out.println("photo:" + m.getMemberPhoto());
		
		
		return query.uniqueResult();
	}
		
	public void doRegisterM(Member m) {
		Session session = sessionFactory.getCurrentSession();		
		session.save(m);
	}

	public void doRegisterC(String account, String email){
		Session session = sessionFactory.getCurrentSession();
		String hqlStr = "update Clinic set clinicEmail = :email where clinicAccount = :account";
		Query query = session.createQuery(hqlStr);
		query.setParameter("email", email);
		query.setParameter("account", account);
		query.executeUpdate();
	}
	
	public Member getInfoWithCode(String code) {
		//時間上的判定，要帶入給sql做，還是先抓出來，再controller裡面做??
		
		Session session = sessionFactory.getCurrentSession();
		String hqlStr = "from Member where memberVerifiedCode = :code";
		Query<Member> query = session.createQuery(hqlStr, Member.class);
		query.setParameter("code", code);
		
		System.out.println("Get Info with code");
		return query.uniqueResult();
	}
	
	//時間內驗證email，將狀態(memberStatus)改為 啟用(1)
	public void setActiveStatus(String code) {
		Session session = sessionFactory.getCurrentSession();
		String hqlStr = "update Member set memberStatus = :status where memberVerifiedCode = :code";
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
		String hqlStr = "update Member set memberVerifiedCode = :codeNew where memberAccount = :account";
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
	public String getCodeWithAccount(String account){
		Session session = sessionFactory.getCurrentSession();
		String hqlStr = "from Member where memberAccount = :account";
		Query<Member> query = session.createQuery(hqlStr, Member.class);
		query.setParameter("account", account);
		return query.uniqueResult().getMemberVerifiedCode();
	}

	public void updateDeadline(String account, long newDeadline){
		Session session = sessionFactory.getCurrentSession();
		String hqlStr = "update Member set memberRegisterDeadline = :deadline where memberAccount = :account";
		Query query = session.createQuery(hqlStr);
		query.setParameter("deadline", newDeadline);
		query.setParameter("account", account);
		query.executeUpdate();
		System.out.println("deadline update");
	}
	
	//忘記密碼時，設定的暫時密碼
	public void setTempPwd(String account, String tempPwd) {
		Session session = sessionFactory.getCurrentSession();
		String hqlStr = "update Member set memberPwd = :tempPwd where memberAccount = :account";
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
			hqlStr = "from Member where memberAccount = :account";
		}
		if(loginLevel.equals("clinic")) {
			hqlStr = "from Clinic where clinicAccount = :account";
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
		String hqlStr = "from Member where memberEmail = :email";
		Query query = session.createQuery(hqlStr);
		query.setParameter("email", email);
		List list = query.list();
		
		return !list.isEmpty();
	}

	//members
	public List<Member> queryAllMember() {
		Session session = sessionFactory.getCurrentSession();
		Query<Member> query = session.createQuery("from Member", Member.class);
		return query.list();
	}

	public Object RememberMePwd(String type, String account) {
		Session session = sessionFactory.getCurrentSession();
		String hqlStr = "";
		
		if(type.equals("member")) {
			hqlStr = "from Member where memberAccount = :account";
		}
		if(type.equals("clinic")) {
			hqlStr = "from Clinic where clinicAccount = :account";
		}
		
		Query query = session.createQuery(hqlStr);
		query.setParameter("account", account);
		
		return query.uniqueResult();
	}
}
