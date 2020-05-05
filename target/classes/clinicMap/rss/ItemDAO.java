package clinicMap.rss;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;

@Repository
public class ItemDAO {
	private SessionFactory sessionf;

	@Autowired
	public ItemDAO(@Qualifier(value = "sessionFactory") SessionFactory sessionFactory) {
		this.sessionf = sessionFactory;
	}

	public List<Item> ItemList() {
		Session session = sessionf.getCurrentSession();
		String hqlStr = "from Item";
		Query<Item> query = session.createQuery(hqlStr, Item.class);

		List<Item> list = query.list();
		

		return list;
	}

	public Item ItemID(int id) {
		Session session = sessionf.getCurrentSession();
		String hqlStr = "from Item where id=:id";
		Query<Item> query = session.createQuery(hqlStr, Item.class);
		query.setParameter("id", id);

		Item i = query.uniqueResult();

		
		
		return i;
	}
}
