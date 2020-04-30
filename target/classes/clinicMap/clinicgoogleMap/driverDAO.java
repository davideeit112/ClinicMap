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
	
	public boolean DLogin(driver driver) {
		try {
			Session session = sessionFactory.getCurrentSession();
			String hqlstr = "from driver where driveraccount=:driveraccount and driverpassword=:driverpassword";
			Query<driver> query = session.createQuery(hqlstr, driver.class);
			query.setParameter("driveraccount", driver.getdriveraccount());
			query.setParameter("driverpassword", driver.getdriverpassword());
			driver dacc = query.uniqueResult();
			if (dacc != null) {
				return true;
			}
			return false;
		} catch (Exception e) {
			System.out.println(e);
			return false;
		}
	}
}
