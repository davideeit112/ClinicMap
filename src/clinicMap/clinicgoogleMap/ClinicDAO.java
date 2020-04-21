package clinicMap.clinicgoogleMap;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;

@Repository
public class ClinicDAO {

	private SessionFactory sessionFactory;
	
	@Autowired
	public ClinicDAO(@Qualifier("sessionFactory") SessionFactory sessionFactory) {
       this.sessionFactory = sessionFactory;
	}
	
	public SessionFactory getSession() {
		return sessionFactory;
	}
	
	
	public List<Clinic> selectmesData(String mes) {
		Session sd = sessionFactory.getCurrentSession();
		String newsqlstr = "from Clinic where clinicName like '%"+mes+"%'";
		Query<Clinic> query = sd.createQuery(newsqlstr, Clinic.class);
		List<Clinic> list = query.list();
		return list;
 
		
	}
	
	
	public List<Clinic> selectData(String clinicType) {
		Session sd = sessionFactory.getCurrentSession();
		String newsqlstr = "from Clinic where clinicType like '%"+clinicType+"%'";
		Query<Clinic> query = sd.createQuery(newsqlstr, Clinic.class);
		List<Clinic> list = query.list();
		return list;
 
		
	}
	
	
	public Clinic queryData(String clinicID) {
		Session sd = sessionFactory.getCurrentSession();
		Clinic myClinicBean = sd.get(Clinic.class, clinicID);
		if(myClinicBean==null) {
			return null;
		}
		return myClinicBean;
	}
	
	
	public List<Clinic> queryAllData() {
		Session sd = sessionFactory.getCurrentSession();
		Query<Clinic> query = sd.createQuery("From Clinic", Clinic.class);
		List<Clinic> list = query.list();
		return list;

	}
	
	public Clinic updateData(String clinicID,String clinicName,String clinicAddress,String clinicLng,String clinicLat) {
		Session sd = sessionFactory.getCurrentSession();
		Clinic myClinicBean = sd.get(Clinic.class, clinicID);
		if (myClinicBean != null) {
			myClinicBean.setClinicID(clinicID);
			myClinicBean.setClinicLng(clinicLng);
			myClinicBean.setClinicLat(clinicLat);
			sd.update(myClinicBean);
			return myClinicBean;
		}
		return null;
	}

	
}


