package clinicMap.rss;

import org.hibernate.Session;

public class ItemCRUD {
	private Session session ;
	
	public ItemCRUD(Session session) {
		this.session = session;
	}
	
	public Session getSession() {
		return session;
	}
	
	public void insert(Item i) {
		getSession().get(Item.class, 1);
		this.getSession().save(i);

	}
}
