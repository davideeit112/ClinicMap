package clinicMap.rss;

import java.io.PrintWriter;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class RssListController {
	private ItemService iService;

	@Autowired
	public RssListController(ItemService iService) {
		this.iService = iService;
	}

	@RequestMapping(path = "/rsslist", method = RequestMethod.POST)
	public void RssListAction(HttpServletResponse response) throws Exception {

		List<Item> list = iService.ItemList();

		for (int k = 0; k < list.size(); k++) {
			for (int j = 0; j < list.size() - k - 1; j++) {
				if (list.get(j).getNewsId() < list.get(j + 1).getNewsId()) {

					String temp;
					temp = list.get(j + 1).getTitle();
					list.get(j + 1).setTitle(list.get(j).getTitle());
					list.get(j).setTitle(temp);

					temp = list.get(j + 1).getDescrip();
					list.get(j + 1).setDescrip(list.get(j).getDescrip());
					list.get(j).setDescrip(temp);

					temp = list.get(j + 1).getLink();
					list.get(j + 1).setLink(list.get(j).getLink());
					list.get(j).setLink(temp);

					temp = list.get(j + 1).getDate();
					list.get(j + 1).setDate(list.get(j).getDate());
					list.get(j).setDate(temp);

					temp = list.get(j + 1).getDeptname();
					list.get(j + 1).setDeptname(list.get(j).getDeptname());
					list.get(j).setDeptname(temp);

					int t = 0;
					t = list.get(j + 1).getNewsId();
					list.get(j + 1).setNewsId(list.get(j).getNewsId());
					list.get(j).setNewsId(t);
				}
			}
		}

		JSONArray array = new JSONArray();

		for (Item i : list) {

			JSONObject jobj = new JSONObject();

			String date = i.getDate();
			String[] d2 = date.split(",| ");

			String descrip = i.getDescrip();
			String[] des2 = descrip.split("<p>");
			String[] des3 = des2[1].split("</p>");
			String[] des4 = des3[0].split("<br />");

			jobj.put("title", i.getTitle());
			jobj.put("descrip", i.getDescrip());
			jobj.put("link", i.getLink());
			jobj.put("date", i.getDate());
			jobj.put("deptname", i.getDeptname());
			jobj.put("week", d2[0]);
			jobj.put("day", d2[2]);
			jobj.put("des", des4[0]);

			array.put(jobj);
		}

		response.setContentType("text/html; charset=UTF-8");
		PrintWriter out = response.getWriter();
		out.print(array);
	}

	@RequestMapping(path = "/search", method = RequestMethod.POST)
	public void search(@RequestParam("search") String key, HttpServletResponse response) throws Exception {
		List<Item> list = iService.ItemList();

		for (int k = 0; k < list.size(); k++) {
			for (int j = 0; j < list.size() - k - 1; j++) {
				if (list.get(j).getNewsId() < list.get(j + 1).getNewsId()) {

					String temp;
					temp = list.get(j + 1).getTitle();
					list.get(j + 1).setTitle(list.get(j).getTitle());
					list.get(j).setTitle(temp);

					temp = list.get(j + 1).getDescrip();
					list.get(j + 1).setDescrip(list.get(j).getDescrip());
					list.get(j).setDescrip(temp);

					temp = list.get(j + 1).getLink();
					list.get(j + 1).setLink(list.get(j).getLink());
					list.get(j).setLink(temp);

					temp = list.get(j + 1).getDate();
					list.get(j + 1).setDate(list.get(j).getDate());
					list.get(j).setDate(temp);

					temp = list.get(j + 1).getDeptname();
					list.get(j + 1).setDeptname(list.get(j).getDeptname());
					list.get(j).setDeptname(temp);

					int t = 0;
					t = list.get(j + 1).getNewsId();
					list.get(j + 1).setNewsId(list.get(j).getNewsId());
					list.get(j).setNewsId(t);
				}
			}
		}
		
		JSONArray array = new JSONArray();
		
		for(Item i:list) {
			
			JSONObject jobj = new JSONObject();
			
			String search = i.getTitle() + i.getDescrip() + i.getDeptname() +i.getDate() ;
			
			//System.out.println(search);
			
			if(search.indexOf(key) >= 0) {
				
				System.out.println(i.getTitle());
				
				String date = i.getDate();
				String[] d2 = date.split(",| ");

				String descrip = i.getDescrip();
				String[] des2 = descrip.split("<p>");
				String[] des3 = des2[1].split("</p>");
				String[] des4 = des3[0].split("<br />");

				jobj.put("title", i.getTitle());
				jobj.put("descrip", i.getDescrip());
				jobj.put("link", i.getLink());
				jobj.put("date", i.getDate());
				jobj.put("deptname", i.getDeptname());
				jobj.put("week", d2[0]);
				jobj.put("day", d2[2]);
				jobj.put("des", des4[0]);
				jobj.put("id", i.getId());

				array.put(jobj);
			}
		}
		response.setContentType("text/html; charset=UTF-8");
		PrintWriter out = response.getWriter();
		out.print(array);
	}
	
	@RequestMapping(path = "/rsspage", method = RequestMethod.POST)
	public void RssPage(@RequestParam("page") String page, HttpServletResponse response) throws Exception {

		List<Item> list = iService.ItemList();
		

		for (int k = 0; k < list.size(); k++) {
			for (int j = 0; j < list.size() - k - 1; j++) {
				if (list.get(j).getNewsId() < list.get(j + 1).getNewsId()) {

					String temp;
					temp = list.get(j + 1).getTitle();
					list.get(j + 1).setTitle(list.get(j).getTitle());
					list.get(j).setTitle(temp);

					temp = list.get(j + 1).getDescrip();
					list.get(j + 1).setDescrip(list.get(j).getDescrip());
					list.get(j).setDescrip(temp);

					temp = list.get(j + 1).getLink();
					list.get(j + 1).setLink(list.get(j).getLink());
					list.get(j).setLink(temp);

					temp = list.get(j + 1).getDate();
					list.get(j + 1).setDate(list.get(j).getDate());
					list.get(j).setDate(temp);

					temp = list.get(j + 1).getDeptname();
					list.get(j + 1).setDeptname(list.get(j).getDeptname());
					list.get(j).setDeptname(temp);

					int t = 0;
					t = list.get(j + 1).getNewsId();
					list.get(j + 1).setNewsId(list.get(j).getNewsId());
					list.get(j).setNewsId(t);
				}
			}
		}
		
		JSONArray array = new JSONArray();
		
		int pageNumber = Integer.parseInt(page);
		
		for(int i=0 ; i<5 ; i++) {
			
			JSONObject jobj = new JSONObject();
			
			int p =pageNumber*5 +i;
			
			if(p >= list.size()) {
				continue;
			}
			
			String date = list.get(p).getDate();
			String[] d2 = date.split(",| ");
			
			String descrip = list.get(p).getDescrip();
			String[] des2 = descrip.split("<p>");
			String[] des3 = des2[1].split("</p>");
			String[] des4 = des3[0].split("<br />");

			jobj.put("title", list.get(p).getTitle());
			jobj.put("descrip", list.get(p).getDescrip());
			jobj.put("link", list.get(p).getLink());
			jobj.put("date", list.get(p).getDate());
			jobj.put("deptname", list.get(p).getDeptname());
			jobj.put("week", d2[0]);
			jobj.put("day", d2[2]);
			jobj.put("des", des4[0]);
			jobj.put("id", list.get(p).getId());

			array.put(jobj);
		}
		
		response.setContentType("text/html; charset=UTF-8");
		PrintWriter out = response.getWriter();
		out.print(array);
	}
}







