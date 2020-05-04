package clinicMap.alarm;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class AnnouncementDAO implements IAnnouncementDAO {

	@Autowired
	private SessionFactory sessionFactory;
	
	@Override
	public List<Announcement1> forAnnouncement() {
		Session session = sessionFactory.getCurrentSession();
		Query<Announcement1> query = session.createQuery("From Announcement1", Announcement1.class);
		List<Announcement1> list = query.list();
		return list;
	}

}
