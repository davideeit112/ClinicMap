package clinicMap.tingyen.model;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class AppointmentDao implements IAppointmentDao {
	
	@Autowired
	private SessionFactory sessionFactory;

	@Override
	public Appointment saveAppoint(String appointmentID,Appointment appointment) {
		Session session = sessionFactory.getCurrentSession();
		if(session.get(Appointment.class,appointmentID) == null) {
			session.save(appointment);
		}		
		return appointment;
	}

	@Override
	public List<Appointment> queryAllAppointment(String clinicID, String today) {
		Session session = sessionFactory.getCurrentSession();
		String hql = "from Appointment where clinicID=" + clinicID +" and Convert(varchar,appointmentTime,120) like '"+today+"%'";
		System.out.println(hql);
		List<Appointment> list=null;
		try {
		Query<Appointment> query = session.createQuery(hql,Appointment.class);
		
		list = query.list();
		}catch(Exception e) {
			e.printStackTrace();
		}
		return list;
	}

	@Override
	public Appointment querySingleAppointment(String appointmentID) {
		Session session = sessionFactory.getCurrentSession();
		Query query = session.createQuery("from Appointment where appointmentID=:id");
		query.setParameter("id", appointmentID);
		Appointment result = (Appointment)query.uniqueResult();
		return result;
	}

	@Override
	public Appointment updateAppointmentStatus(String appointmentID, String appointmentStatus) {
		Session session = sessionFactory.getCurrentSession();
		Query query = session.createQuery("from Appointment where appointmentID=:id");
		query.setParameter("id", appointmentID);
		Appointment aBean = (Appointment)query.uniqueResult();
		aBean.setAppointmentStatus(appointmentStatus);
		return aBean;
	}

	@Override
	public Appointment updateAppointmentData(String appointmentID,String appointmentType, String sickDescription, int appointmentNumber) {
		Session session = sessionFactory.getCurrentSession();
		Query query = session.createQuery("from Appointment where appointmentID=:id");
		query.setParameter("id", appointmentID);
		Appointment aBean = (Appointment)query.uniqueResult();
		aBean.setAppointmentType(appointmentType);
		aBean.setSickDescription(sickDescription);
		aBean.setAppointmentNumber(appointmentNumber);
		return aBean;
	}

	@Override
	public boolean deleteAppointment(String appointmentID) {
		Session session = sessionFactory.getCurrentSession();
		Appointment queryBean = session.get(Appointment.class,appointmentID);
		if(queryBean!=null) {
			session.delete(queryBean);
			return true;
		}
		return false;
	}

	@Override
	public Map<String,Integer> analyzeAppointmentData(String clinicID, String dateForm) {
		Session session = sessionFactory.getCurrentSession();
		String hql = "from Appointment where clinicID=" + clinicID +" and Convert(varchar,appointmentTime,120) like '"+dateForm+"%'";
		Query<Appointment> query = session.createQuery(hql,Appointment.class);
		
		List<Appointment> list = query.list();
		
		int appointmentNum = list.size();
		int firstTimeNum = 0;
		int completeDiagnosis = 0;
		int unReportNum = 0;
		int deleteApp = 0;
		for(Appointment aBean : list) {
			if(aBean.getAppointmentType().equals("OT2")) {
				firstTimeNum++;
			}else if(aBean.getAppointmentStatus().equals("OS3")) {
				completeDiagnosis++;
			}else if(aBean.getAppointmentStatus().equals("OS5")) {
				deleteApp++;
			}else if(aBean.getAppointmentStatus().equals("OS4")) {
				unReportNum++;
			}
		}
		
		Map<String,Integer> analyzeData = new HashMap<String,Integer>();
		analyzeData.put("appointmentNum", appointmentNum);
		analyzeData.put("firstTimeNum", firstTimeNum);
		analyzeData.put("completeDiagnosis", completeDiagnosis);
		analyzeData.put("unReportNum", unReportNum);
		analyzeData.put("unReportNum", deleteApp);
		return analyzeData;
	}
}
