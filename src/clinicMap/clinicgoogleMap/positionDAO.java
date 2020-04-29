package clinicMap.clinicgoogleMap;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;

@Repository
public class positionDAO {
	private SessionFactory sessionFactory;

	@Autowired
	public positionDAO(@Qualifier("sessionFactory") SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

	public SessionFactory getSession() {
		return sessionFactory;
	}

	public void updateguestData(int positionID, String guestlat, String guestlng) {
		Session sd = sessionFactory.getCurrentSession();
		position mypositionBean = sd.get(position.class, positionID);
		System.out.println("testDAO");
		mypositionBean.setguestlat(guestlat);
		mypositionBean.setguestlng(guestlng);
		sd.update(mypositionBean);

	}

	public position updatedriverData(int positionID, String driverlat, String driverlng) {
		Session sd = sessionFactory.getCurrentSession();
		position mypositionBean = sd.get(position.class, positionID);
		if (mypositionBean != null) {
			mypositionBean.setdriverlat(driverlat);
			mypositionBean.setdriverlng(driverlng);
			sd.update(mypositionBean);
			return mypositionBean;
		}
		return null;
	}

	public position updatedestinationData(int positionID, String destinationlat, String destinationlng) {
		Session sd = sessionFactory.getCurrentSession();
		position mypositionBean = sd.get(position.class, positionID);
		if (mypositionBean != null) {
			mypositionBean.setdestinationlat(destinationlat);
			mypositionBean.setdestinationlng(destinationlng);
			sd.update(mypositionBean);
			return mypositionBean;
		}
		return null;
	}

	public position selectpositionData(int positionID) {
		Session sd = sessionFactory.getCurrentSession();
		position mypositionBean = sd.get(position.class, positionID);
		if (mypositionBean == null) {
			return null;
		}
		return mypositionBean;
	}

	public List<position> selectAllOrderData(String drivername) {
		Session sd = sessionFactory.getCurrentSession();
		String newsqlstr = "from position where drivername like '%"+drivername+"%'";
		Query<position> query = sd.createQuery(newsqlstr, position.class);
		List<position> list = query.list();
		return list;
	}
	
	public position neworderinData(position Pposition) {
		Session sd = sessionFactory.getCurrentSession();
		sd.save(Pposition);	    
		return null;
	}

}
