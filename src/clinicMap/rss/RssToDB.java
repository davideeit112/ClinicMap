package clinicMap.rss;

import java.util.List;
import java.net.URL;
import java.util.ArrayList;
import java.util.Iterator;

import org.dom4j.Document;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.query.Query;

public class RssToDB {

	public static void main(String[] args) throws Exception {
		new RssToDB().parse();
	}

	public void parse() throws Exception {

		URL url = new URL("https://www.mohw.gov.tw/rss-16-1.html");
		SAXReader sax = new SAXReader();
		Document doc = sax.read(url);

		Element channel = (Element) doc.getRootElement().element("channel");

		Iterator<Element> i = channel.elementIterator("item");

		StandardServiceRegistry serviceRegistry = new StandardServiceRegistryBuilder().configure().build();
		SessionFactory factory = new MetadataSources(serviceRegistry).buildMetadata().buildSessionFactory();

		Session session = factory.getCurrentSession();
		session.beginTransaction();

		// 先用NEWSID排序
		//List<Item> list = new ArrayList<Item>();


//		while (i.hasNext()) {
//
//			Element e = i.next();
//
//			Item it = new Item();
//			it.setId(id);
//			it.setTitle(e.elementText("title"));
//			it.setDescrip(e.elementText("description"));
//			it.setLink(e.elementText("link"));
//			it.setDate(e.elementText("pubDate"));
//			it.setDeptname(e.elementText("DeptName"));
//			it.setNewsId(Integer.parseInt(e.elementText("NewsID")));
//
//			list.add(it);
//			id++;
//		}
		
		Query<Item> query1 = session.createQuery("from Item", Item.class);

		List<Item> list = query1.list();
		
		int id = list.size()+1;


		while (i.hasNext()) {

			Element e = i.next();

			Item it = new Item();
			it.setId(id);
			it.setTitle(e.elementText("title"));
			it.setDescrip(e.elementText("description"));
			it.setLink(e.elementText("link"));
			it.setDate(e.elementText("pubDate"));
			it.setDeptname(e.elementText("DeptName"));
			it.setNewsId(Integer.parseInt(e.elementText("NewsID")));
			
			String hqlStr = "from Item where newsid=:newsid";
			Query<Item> query2 = session.createQuery(hqlStr, Item.class);
			query2.setParameter("newsid", it.getNewsId());

			Item i2 = query2.uniqueResult();
			
			if(i2 == null) {
				session.save(it);
			}
			id++;
		}

		session.getTransaction().commit();
		session.close();
	}
}
