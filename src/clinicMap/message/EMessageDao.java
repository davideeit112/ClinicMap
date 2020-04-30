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
import clinicMap.tingyen.model.Appointment;
import clinicMap.tingyen.model.AppointmentStatus;

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
			msg.setClinicID(emessage.getClinicID());
			msg.setAppointmentID(emessage.getAppointmentID());
			msg.setMemberID(emessage.getMemberID());
			msg.setEvaluation(emessage.getEvaluation());
			msg.setMessage(emessage.getMessage());
			msg.setEvaluationTime(emessage.getEvaluationTime());
			session.save(emessage);
			System.out.println(emessage.getAppointmentID());
			Appointment abean = session.get(Appointment.class,emessage.getAppointmentID());
			
			abean.setAppointmentStatus("OS6");
			session.save(abean);
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
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
	
	public List<EMessage> querymsg() {
		Session session = sessionfactory.getCurrentSession();
		Query<EMessage> query = session.createQuery("from EMessage order by messageID desc", EMessage.class);
		List<EMessage> list = query.list();
//		System.out.println(list);
		return list;
	}
	
	public List<EMessage> queryitem() {
		Session session = sessionfactory.getCurrentSession();
		Query<EMessage> query = session.createQuery("from EMessage order by clinicID", EMessage.class);
		List<EMessage> list = query.list();
//		System.out.println(list);
		return list;
	}

	public List<ResultBean> total() {
		Session session = sessionfactory.getCurrentSession();
		Query<ResultBean> query = session.createQuery("from ResultBean", ResultBean.class);
		List<ResultBean> list = query.list();
//		System.out.println(list);
		return list;
	}

	public List<ResultBean> top3blog() {
		Session session = sessionfactory.getCurrentSession();
		Query<ResultBean> query = session.createQuery("from ResultBean order by total desc", ResultBean.class);
		List<ResultBean> list = query.list();
//		System.out.println(list);
		return list;
	}