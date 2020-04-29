package clinicMap.rss;

import java.net.URL;
import java.util.Iterator;
import java.util.List;

import org.dom4j.Document;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
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

	public void RssToDB() throws Exception {
		URL url = new URL("https://www.mohw.gov.tw/rss-16-1.html");
		SAXReader sax = new SAXReader();
		Document doc = sax.read(url);

		Element channel = (Element) doc.getRootElement().element("channel");

		Iterator<Element> i = channel.elementIterator("item");

		Session session = sessionf.getCurrentSession();
		
		int id = 1;

		while (i.hasNext()) {

			Element e = i.next();

			Item it = new Item();
			it.setId(id);
			it.setTitle(e.elementText("title"));
			it.setDescrip(e.elementText("description"));
			it.setLink(e.elementText("link"));
			it.setDate(e.elementText("pubDate"));
			it.setDeptname(e.elementText("DeptName"));
			session.save(it);
			id++;
		}
		
		System.out.println("inserts");
		session.close();
	}

	public void clearDB() {
		Session session = sessionf.getCurrentSession();
		String hqlStr = "delete Item ";
		@SuppressWarnings("rawtypes")
		Query query = session.createQuery(hqlStr);
		
		query.executeUpdate();
		System.out.println("1");
		session.getTransaction().commit();
		session.close();
	}
}
