package clinicMap.alarm;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class AnnouncementController {

	@Autowired
	private AnnouncementService aService;

	@RequestMapping(path = "/posting.do", method = RequestMethod.GET)
	public void AnnoucementControl(HttpServletResponse response) throws IOException {
		try {
			List<Announcement1> list = aService.forAnnouncement();
			JSONArray jsonArray01 = new JSONArray();
			for(Announcement1 aBean:list) {
				JSONObject jsonObject01 = new JSONObject(aBean);
				jsonArray01.put(jsonObject01);
			}
			PrintWriter out = response.getWriter();
			System.out.println("Test:"+jsonArray01.toString());
			out.print(jsonArray01);
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
}
