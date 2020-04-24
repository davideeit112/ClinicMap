package clinicMap.message;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;

import clinicMap.order.clinicBean;
import clinicMap.order.memberBean;

@Repository
public class EMessageDao {
	private SessionFactory sessionfactory;

	@Autowired
	public EMessageDao(@Qualifier(value = "sessionFactory") SessionFactory sessionfactory) {
		this.sessionfactory = sessionfactory;
	}

	public boolean inputmessage(EMessage emessage) {
		try {
			Session session = sessionfactory.getCurrentSession();
			EMessage msg = new EMessage();
			System.out.println("11");
			msg.setClinicID(emessage.getClinicID());
			msg.setAppointmentID(emessage.getAppointmentID());
			msg.setMemberID(emessage.getMemberID());
			msg.setEvaluation(emessage.getEvaluation());
			msg.setMessage(emessage.getMessage());
			msg.setEvaluationTime(emessage.getEvaluationTime());
			System.out.println("22");
			session.save(emessage);
			return true;
		} catch (Exception e) {
			System.out.println(e);
			return false;
		}
	}

	public boolean deletemessage(int messageID) {
		try {
			Session session = sessionfactory.getCurrentSession();
			String hqlstr = "delete from EMessage where messageID=:messageID";
			Query query = session.createQuery(hqlstr);
			query.setParameter("messageID", messageID);
			int msgDao = query.executeUpdate();
			if(msgDao!=0) {
				return true;
			}
			return false;
		} catch (Exception e) {
			System.out.println(e);
			return false;
		}
	}
	public int query3Clinic() {
	String sqlstr="select clinicID from EMessage group by Clinicid order by EVALUATIONTIME desc";
	
	Session session = sessionfactory.getCurrentSession();
	Query<EMessage> query = session.createQuery(sqlstr,EMessage.class);
	query.list();
	return query.list().size();
	}

	public List<clinicBean> queryclinic() {
		Session session = sessionfactory.getCurrentSession();
		Query<clinicBean> query = session.createQuery("from clinicBean", clinicBean.class);
		List<clinicBean> list = query.list();
		return list;
	}
	
	public List<memberBean> querymember() {
		Session session = sessionfactory.getCurrentSession();
		Query<memberBean> query = session.createQuery("from memberBean", memberBean.class);
		List<memberBean> list = query.list();
		return list;
	}

	public List<EMessage> querymsg1() {
		Session session = sessionfactory.getCurrentSession();
		System.out.println("2.");
		Query<EMessage> query = session.createQuery("from EMessage where clinicID=1393 order by evaluationTime desc", EMessage.class);
		System.out.println("2..");
		List<EMessage> list = query.list();
		System.out.println(list);
		return list;
	}

	public List<EMessage> querymsg2() {
		Session session = sessionfactory.getCurrentSession();
		Query<EMessage> query = session.createQuery("from EMessage where clinicID=1502 order by evaluationTime desc", EMessage.class);
		List<EMessage> list = query.list();
		System.out.println(list);
		return list;
	}
	
	public List<EMessage> querymsg3() {
		Session session = sessionfactory.getCurrentSession();
		Query<EMessage> query = session.createQuery("from EMessage where clinicID=1036 order by evaluationTime desc", EMessage.class);
		List<EMessage> list = query.list();
		System.out.println(list);
		return list;
	}	
	
//	public List<ReplyBean> queryreply(){
//		Session session = sessionfactory.getCurrentSession();
//		Query<ReplyBean> query = session.createQuery("from ReplyBean", ReplyBean.class);
//		List<ReplyBean> list = query.list();
//		return list;
//	}
}
