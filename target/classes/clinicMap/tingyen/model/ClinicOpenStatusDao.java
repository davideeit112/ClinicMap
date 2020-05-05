package clinicMap.tingyen.model;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class ClinicOpenStatusDao implements IClinicOpenStatusDao {
	@Autowired
	private SessionFactory sessionFactory;
	
	public ClinicOpenStatus updateStatus(String clinicID,boolean openStatus,int currentNum,String openDescription) {
		Session session = sessionFactory.getCurrentSession();
		
		ClinicOpenStatus clinicStatusBean=null;
		try {
		if(session.get(ClinicOpenStatus.class,clinicID) == null) {
			clinicStatusBean = new ClinicOpenStatus();
			clinicStatusBean.setClinicID(clinicID);
			clinicStatusBean.setClinicOpenStatus(openStatus);
			clinicStatusBean.setClinicCurrentNumber(currentNum);
			clinicStatusBean.setOpenDescription(openDescription);
			session.save(clinicStatusBean);
			System.out.println("updateStrat");
			return clinicStatusBean;
		}
		clinicStatusBean = session.get(ClinicOpenStatus.class,clinicID);
		clinicStatusBean.setClinicOpenStatus(openStatus);
		clinicStatusBean.setClinicCurrentNumber(currentNum);
		clinicStatusBean.setOpenDescription(openDescription);
		System.out.println("updateStrat2");
		}catch(Exception e) {
			e.printStackTrace();
		}
		return clinicStatusBean;
	}
	@Override
	public ClinicOpenStatus getCurrentNumber(String clinicID) {
		Session session = sessionFactory.getCurrentSession();
		ClinicOpenStatus clinicStatusBean = session.get(ClinicOpenStatus.class,clinicID);
		return clinicStatusBean;
	}
	@Override
	public ClinicOpenStatus saveCurrentNum(String clinicID, int currentNum) {
		Session session = sessionFactory.getCurrentSession();
		ClinicOpenStatus clinicStatusBean = session.get(ClinicOpenStatus.class,clinicID);
		clinicStatusBean.setClinicCurrentNumber(currentNum);
		return clinicStatusBean;
	}
	@Override
	public ClinicOpenStatus updateStatus(String clinicID, boolean openStatus, int currentNum) {
		// TODO Auto-generated method stub
		return null;
	}

}
