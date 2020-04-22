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
	
	
	public List<Clinicdavid> selectmesData(String mes) {
		Session sd = sessionFactory.getCurrentSession();
		String newsqlstr = "from Clinicdavid where clinicName like '%"+mes+"%'";
		Query<Clinicdavid> query = sd.createQuery(newsqlstr, Clinicdavid.class);
		List<Clinicdavid> list = query.list();
		return list;
 
		
	}
	
	
	public List<Clinicdavid> selectData(String clinicType) {
		Session sd = sessionFactory.getCurrentSession();
		String newsqlstr = "from Clinicdavid where clinicType like '%"+clinicType+"%'";
		Query<Clinicdavid> query = sd.createQuery(newsqlstr, Clinicdavid.class);
		List<Clinicdavid> list = query.list();
		return list;
 
		
	}
	
	
	public Clinicdavid queryData(String clinicID) {
		Session sd = sessionFactory.getCurrentSession();
		Clinicdavid myClinicBean = sd.get(Clinicdavid.class, clinicID);
		if(myClinicBean==null) {
			return null;
		}
		return myClinicBean;
	}
	
	
	public List<Clinicdavid> queryAllData() {
		Session sd = sessionFactory.getCurrentSession();
		Query<Clinicdavid> query = sd.createQuery("From Clinicdavid", Clinicdavid.class);
		List<Clinicdavid> list = query.list();
		return list;

	}
	
	public Clinicdavid updateData(String clinicID,String clinicName,String clinicAddress,String clinicLng,String clinicLat) {
		Session sd = sessionFactory.getCurrentSession();
		Clinicdavid myClinicBean = sd.get(Clinicdavid.class, clinicID);
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


