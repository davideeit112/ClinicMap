package clinicMap.order;

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
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class orderController {
	@Autowired
	private orderService service;

	@RequestMapping(path = "/ordername", method = RequestMethod.GET)
	public String orderdate() throws IOException {

		service.insert();
		return "test";
	}

	@RequestMapping(path = "/mapshow", method = RequestMethod.POST)
	public void mapshow(HttpServletResponse response) throws IOException {
		System.out.println("hi");
		List<clinicBean> list = service.query();
		System.out.println("hi2");
		JSONArray arry = new JSONArray();
		for (clinicBean cbean : list) {
			System.out.println(cbean.getClinicStatus());
			if(cbean.getClinicStatus().equals("CS1")) {
			JSONObject jobj = new JSONObject();

			jobj.put("clinicaddress", cbean.getClinicAddress());
			jobj.put("clinicId", cbean.getClinicId());
			jobj.put("clinicPhone", cbean.getClinicphone());
			jobj.put("clinicType", cbean.getClinicType());
			jobj.put("cliniclat", cbean.getCliniclat());
			jobj.put("cliniclng", cbean.getCliniclan());
			jobj.put("clinicName", cbean.getClinicName());
			arry.put(jobj);
			
			System.out.println(cbean.getCliniclat() + " " + cbean.getCliniclan());
//				System.out.println(cbean.getClinicName()+" "+i[0]+" "+cbean.getClinicphone()+cbean.getClinicType());
			}
		}
		response.setContentType("text/html ;charset=UTF-8");
		PrintWriter out = response.getWriter();
		out.print(arry);
	}

	@RequestMapping(path = "/ordertime", method = RequestMethod.POST)
	public void order(@RequestParam("clinicid") String clinicid, @RequestParam("memberid") String memberid,
			@RequestParam("description") String description, HttpServletResponse response) throws IOException {
		int size = service.order(clinicid, memberid, description);
		response.setContentType("text/html ;charset=UTF-8");
		PrintWriter out = response.getWriter();
		out.print(size);
	}

	@RequestMapping(path = "/peopleNumber", method = RequestMethod.POST)
	public void number(@RequestParam("clinicid") String clinicid, HttpServletResponse response) throws IOException {

		int size = service.peoplewait(clinicid);
		response.setContentType("text/html ;charset=UTF-8");
		PrintWriter out = response.getWriter();

		out.print(size);
	}
	//經緯度轉換
	@RequestMapping(path = "/addlnglat", method = RequestMethod.POST)
	public void addlnglat(@RequestParam("data") String data) {
		System.out.println(data);
		String[] list = data.replace("(", "").replace(")", "").replace(" ", "").split(",");
		service.insertlnglat(list);
	}
	@RequestMapping(path="/cancel",method=RequestMethod.POST)
	public void cancel(@RequestParam("AppointmentID")String AppointmentID) {
		service.deleteorder(AppointmentID);
	}
	@RequestMapping(path="/memberset",method = RequestMethod.POST)
	public void memberdate(@RequestParam("memberid")int memberid,HttpServletResponse response) throws IOException {
		memberBean mbean = service.memberquery(memberid);
		List<orderbean> list = service.orderquery(memberid);
		JSONObject mjobj= new JSONObject();
		mjobj.put("memberid", mbean.getMemberID());
		mjobj.put("memberName", mbean.getmemberName());
		mjobj.put("memberpwd", mbean.getMemberPwd());
		mjobj.put("memberemail", mbean.getMemberemail());
		mjobj.put("memberHeight", mbean.getMemberHeight());
		mjobj.put("memberWeight", mbean.getMemberWeight());
		mjobj.put("memberAddress", mbean.getMemberAddress());
		mjobj.put("memberGender", mbean.getMemberGender());
		mjobj.put("memberstatus", mbean.getMemberStatus());
		mjobj.put("memberPhone", mbean.getMemberPhone());
		mjobj.put("memberAccount", mbean.getMemberAccount());
		
		JSONArray jary=new JSONArray();
		
		JSONArray ojary=new JSONArray();
		for (orderbean obean:list) {
			JSONObject jobj= new JSONObject();
			jobj.put("AppointmentID",obean.getAppointmentID());
			jobj.put("AppointmentStatus", obean.getAppointmentStatus());
			jobj.put("AppointmentTime", obean.getAppointmentTime());
			jobj.put("AppointmentType", obean.getAppointmentType());
			clinicBean cbean = service.clinicquery(obean.getClinicID());
			jobj.put("clinicName", cbean.getClinicName());
			jobj.put("AppointmentNumber",obean.getAppointmentNumber());
			ojary.put(jobj);
		}
			mjobj.put("orderlist", ojary);
			jary.put(mjobj);
			response.setContentType("text/html ;charset=UTF-8");
			PrintWriter out = response.getWriter();
			out.print(jary);
	}
}
