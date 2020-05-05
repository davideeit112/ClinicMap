package clinicMap.rss;

import java.net.URL;
import java.util.Iterator;
import java.util.List;

import org.dom4j.Document;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
@Controller
public class RssToDB {
	private SessionFactory sessionfactory;

	@Autowired
	public void test(@Qualifier("sessionFactory") SessionFactory sessionfactory) {
		this.sessionfactory = sessionfactory;}

	@RequestMapping(path="/rssget",method = RequestMethod.GET)
	public String parse() throws Exception {

		URL url = new URL("https://www.mohw.gov.tw/rss-16-1.html");
		SAXReader sax = new SAXReader();
		Document doc = sax.read(url);

		Element channel = (Element) doc.getRootElement().element("channel");

		Iterator<Element> i = channel.elementIterator("item");
		Session session = sessionfactory.getCurrentSession();

		
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
		return "blog";
	}
	@RequestMapping(path="/rssdetail",method = RequestMethod.GET)
	public String blogdetail() {
		return "single-blog";
	}
}
