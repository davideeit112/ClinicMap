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

import clinicMap.clinicgoogleMap.Clinic;
import clinicMap.clinicgoogleMap.ClinicService;

@Controller
public class testcontrollerclinic {

	@Autowired
	private ClinicService service;
	
	@RequestMapping(path = "/start.do", method = RequestMethod.GET)
	 public String start() {
	  return "index";
	 }

//�^�Ǧa�}���覡	
	@RequestMapping(value = "/addGoogles", method = RequestMethod.POST)
	public void submit(HttpServletResponse res) throws IOException {
		List<Clinic> qa = service.queryAllData();
		JSONArray jaray = new JSONArray();
		for (Clinic i : qa) {
			JSONObject jso = new JSONObject();
			jso.put("clinicAddress", i.getClinicAddress());
			jso.put("clinicName", i.getClinicName());
			jaray.put(jso);
		}

		res.setContentType("text/html;charset=UTF-8");
		PrintWriter out = res.getWriter();
		out.print(jaray);

	}

//���j�M
	@RequestMapping(value = "/checkboxvalues", method = RequestMethod.POST)
	public void createRpt(@RequestParam(value = "selectval") String selectval,HttpServletResponse res) throws IOException {
		String sqlstr = selectval;
		System.out.println(sqlstr);
		
		List<Clinic> qa = service.selectData(sqlstr);
		
		JSONArray jaray = new JSONArray();
		for (Clinic i : qa) {
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
		System.out.println(message2);
		
		List<Clinic> qa = service.selectmesData(message2);
		
		JSONArray jaray = new JSONArray();
		for (Clinic i : qa) {
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
	

	// �j�M��PUBLIC-2
//		@RequestMapping(value = "/checkboxvalues", method = RequestMethod.POST)
//		public void createRpt(HttpServletRequest request, HttpServletResponse response)throws IOException {
//			 response.setContentType("text/html;charset=UTF-8");
//			 request.setCharacterEncoding("UTF-8");
//			
//
//		    String selectval = request.getParameter("selectval");
//			 
//			String sqlstr=selectval;
//			System.out.println(selectval);			
//		
//		}
		

	// ----------�B�z�e�ݸ�Ƨ令SQL�y�k
//		String[] tokens = clinicClass.split(",");
//		String sqlstr = "select*from Clinic where clinicClass like '";
//		// �n�����U���o��Select * FROM Clinic Where clinicClass LIKE '%1003%,%1005%'
//		for (int j = 0; j <= tokens.length; j++) {
//
//			sqlstr +="%" + tokens[j] + "%,";
//		}
//
//		sqlstr = sqlstr.substring(0, sqlstr.length() - 1);
//		sqlstr += "'";
//
//		System.out.println(sqlstr);

	// ----------�}�lDAO�����
//		List<Clinic> sd = service.selectData(sqlstr);
//		JSONArray jaray = new JSONArray();
//		for (Clinic i : sd) {
//			JSONObject jso = new JSONObject();
//			jso.put("clinicName", i.getClinicName());
//			jso.put("clinicLat", i.getClinicLat());
//			jso.put("clinicLng", i.getClinicLng());
//			jaray.put(jso);
//		}

//		res.setContentType("text/html;charset=UTF-8");
//		PrintWriter out = res.getWriter();
//		out.print(jaray);

}
