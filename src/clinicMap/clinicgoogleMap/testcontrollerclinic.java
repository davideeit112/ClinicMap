package clinicMap.clinicgoogleMap;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import clinicMap.clinicgoogleMap.Clinicdavid;
import clinicMap.clinicgoogleMap.ClinicServiceDavid;

@Controller
public class testcontrollerclinic {

	@Autowired
	private ClinicServiceDavid service;
	
	@RequestMapping(path = "/googleMap", method = RequestMethod.GET)
	 public String start() {
	  return "index";
	 }

	@RequestMapping(value = "/addGoogles", method = RequestMethod.POST)
	public void submit(HttpServletResponse res) throws IOException {
		List<Clinicdavid> qa = service.queryAllData();
		JSONArray jaray = new JSONArray();
		for (Clinicdavid i : qa) {
			JSONObject jso = new JSONObject();
			jso.put("clinicAddress", i.getClinicAddress());
			jso.put("clinicName", i.getClinicName());
			jaray.put(jso);
		}

		res.setContentType("text/html;charset=UTF-8");
		PrintWriter out = res.getWriter();
		out.print(jaray);

	}

	@RequestMapping(value = "/checkboxvalues", method = RequestMethod.POST)
	public void createRpt(@RequestParam(value = "selectval") String selectval,HttpServletResponse res) throws IOException {
		String sqlstr = selectval;
		
		List<Clinicdavid> qa = service.selectData(sqlstr);
		
		JSONArray jaray = new JSONArray();
		for (Clinicdavid i : qa) {
			JSONObject jso = new JSONObject();
			jso.put("clinicName", i.getClinicName());
			jso.put("clinicLat", i.getClinicLat());
			jso.put("clinicLng", i.getClinicLng());
			jaray.put(jso);
			
		}
		res.setContentType("text/html;charset=UTF-8");
		PrintWriter out = res.getWriter();
		out.print(jaray);
		

	}
	
	
//��r�j�M��
	@RequestMapping(value = "/selectmes", method = RequestMethod.POST)
	public void messageselect(@RequestParam(value = "message") String message,HttpServletResponse res) throws IOException {
		String message2 = message;
		
		List<Clinicdavid> qa = service.selectmesData(message2);
		
		JSONArray jaray = new JSONArray();
		for (Clinicdavid i : qa) {
			JSONObject jso = new JSONObject();
			jso.put("clinicName", i.getClinicName());
			jso.put("clinicLat", i.getClinicLat());
			jso.put("clinicLng", i.getClinicLng());
			jaray.put(jso);
		}
		res.setContentType("text/html;charset=UTF-8");
		PrintWriter out = res.getWriter();
		out.print(jaray);
		

	}	
	


}
