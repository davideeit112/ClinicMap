package clinicMap.clinicgoogleMap;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;

@Repository
public class driverDAO {
	private SessionFactory sessionFactory;
	
	@Autowired
	public driverDAO(@Qualifier("sessionFactory") SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}
	
	public SessionFactory getSession() {
		return sessionFactory;
	}
	
	public List<driver> showdriver(){
		Session sd = sessionFactory.getCurrentSession();
		Query<driver> query = sd.createQuery("From driver", driver.class);
		List<driver> list = query.list();
		return list;
	}
	
}
