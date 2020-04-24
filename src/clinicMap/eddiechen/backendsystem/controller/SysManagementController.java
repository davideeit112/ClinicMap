package clinicMap.eddiechen.backendsystem.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

import clinicMap.eddiechen.backendsystem.model.Clinicchen;
import clinicMap.eddiechen.backendsystem.model.ClinicP;
import clinicMap.eddiechen.backendsystem.model.SysManagement;
import clinicMap.eddiechen.backendsystem.model.SysManagementService;

@Controller
@SessionAttributes(names = { "acc", "pwd", "errors" })
public class SysManagementController {
	private SysManagementService sysService;

	@Autowired
	public SysManagementController(SysManagementService sysService) {
		this.sysService = sysService;
	}

	@RequestMapping(path = "/checkLogin", method = RequestMethod.POST)
	public ModelAndView loginAction(@RequestParam("sysAcc") String acc, @RequestParam("sysPwd") String pwd,
			HttpServletRequest req, HttpServletResponse res, Model model) {
		
		Map<String, String> errors = new HashMap<String, String>();
		model.addAttribute("errors", errors);

		if (acc == null || acc.length() == 0) {
			errors.put("name", "name is required.");
		}

		if (pwd == null || pwd.length() == 0) {
			errors.put("pwd", "password is required.");
		}

		if (errors != null && !errors.isEmpty()) {
			return new ModelAndView("redirect:/Login.html");
		}

		model.addAttribute("acc", acc);
		model.addAttribute("pwd", pwd);

		boolean status = sysService.checkLogin(new SysManagement(acc, pwd));
		model.addAttribute("status", status);
		if (status) {
			Cookie cookie = new Cookie("LoginOK", acc);
			cookie.setPath("/");
			cookie.setMaxAge(60*10);
			res.addCookie(cookie);
			return new ModelAndView("redirect:/System.html");
		}

		errors.put("msg", "username or password is not correct.");

		return new ModelAndView("redirect:/Login.html");
	}

	@RequestMapping(path = "/loadClinicData", method = RequestMethod.GET)
	public void loadClinicData(HttpServletResponse res, HttpServletRequest req) throws IOException {
		
//		HttpSession session = req.getSession(false);
//		String acc = (String)session.getAttribute("acc");
//		System.out.println(acc);
		// StringBuilder data = new StringBuilder();
		String clinicStatus;
		List<Clinicchen> data1 = sysService.clinicData();

		JSONArray js = new JSONArray();
		for (Clinicchen c : data1) {
//			String photoFilePathToSave = ("D:\\DataSource\\WORKSPACE\\FinalWorkSpace\\SpringBackEndSystem\\WebContent\\eddiechen\\imgs\\" + c.getClinicAccount() + ".jpg");
//	        sysService.readPhotoOfPerson(c.getClinicID(), photoFilePathToSave);
			String FinalPath = ("eddiechen/imgs/" + c.getClinicAccount() + ".jpg");
			ClinicP cP = new ClinicP();

			if (c.getClinicStatus().equals("CS0")) {
				clinicStatus = "尚未核對";
			} else if (c.getClinicStatus().equals("CS1")) {
				clinicStatus = "信箱認證中";
			} else if(c.getClinicStatus().equals("CS2")){
				clinicStatus = "已認證";
			}else {
				clinicStatus = "已認證且贊助升級";
			}

			String clinicLicense= "<img src='" + FinalPath + "' style='width:60px; height:80px;' onclick='BigPic'/> ";
			String emailVerify = "<form method='post' action='emailActivate'><input type='hidden' value='"
					+ c.getClinicEmail() + "' name='clinicEmail'> <input type='hidden' value = '" + c.getClinicID()
					+ "' name = 'clinicID'><input type = 'submit' value = '信箱認證' class='btn btn-light'> </form>";
			cP.setClinicID(c.getClinicID());
			cP.setClinicAccount(c.getClinicAccount());
			cP.setClinicName(c.getClinicName());
			cP.setClinicAddress(c.getClinicAddress());
			cP.setClinicStatus(clinicStatus);
			cP.setClinicEmail(emailVerify);
			cP.setClinicLicense(clinicLicense);

			JSONObject jo = new JSONObject(cP);
			js.put(jo);

//			if (c.getClinicStatus().equals("CS0")) {
//				data.append("<tr><td><img src='" + FinalPath + "' /> </td>");
//				data.append("<td>" + c.getClinicAccount() + "</td>");
//				data.append("<td>" + c.getClinicName() + "</td>");
//				data.append("<td>" + c.getClinicAddress() + "</td>");
//				data.append("<td>" + clinicStatus + "</td>");
//				data.append("<td><form method='post' action='emailActivate'><input type='hidden' value='"
//						+ c.getClinicEmail() + "' name='clinicEmail'> <input type='hidden' value = '" + c.getClinicID()
//						+ "' name = 'clinicID'><input type = 'submit' value = '信箱認證' class='btn btn-light'> </form></td></tr>");
//			} else {
//				data.append("<tr><td><img src='" + FinalPath + "' /> </td>");
//				data.append("<td>" + c.getClinicAccount() + "</td>");
//				data.append("<td>" + c.getClinicName() + "</td>");
//				data.append("<td>" + c.getClinicAddress() + "</td>");
//				data.append("<td>" + clinicStatus + "</td>");
//				data.append("<td></td></tr>");
//			}
		}

//		String fullData = data.toString();
		PrintWriter out = res.getWriter();
		out.print(js);

	}

	@RequestMapping(path = "/insertPhoto", method = RequestMethod.GET)
	public String insertPhoto() throws IOException {
		String photoFilePathToRead = "D:\\Test\\p19.jpg";
		
		for(int count = 1005; count<=1100; count ++){
		sysService.savePersonWithPhoto(photoFilePathToRead, count);
		}
		return "Login";
	}

//	@RequestMapping(path="/readPhoto", method = RequestMethod.GET)
//	public String readPhoto(HttpServletResponse res) throws IOException {
//        return "System";
//	}

	@RequestMapping(path = "/emailActivate", method = RequestMethod.POST)
	public ModelAndView email(HttpServletRequest req, HttpServletResponse res) throws IOException {
		HttpSession session = req.getSession(false);
		String acc = (String)session.getAttribute("acc");
			if(acc == null) {
				return new ModelAndView("redirect:/Login.html");
			}
		int clinicID = Integer.parseInt(req.getParameter("clinicID"));
		sysService.emailActivate(clinicID);
		sysService.changeStatus(clinicID);
		return new ModelAndView("redirect:/System.html");
	}

	@RequestMapping(path = "/changeStatus", method = RequestMethod.GET)
	public String changeStatus(HttpServletRequest req, int clinicID) {
		sysService.changeStatus2(clinicID);
		return "System";
	}
	
	@RequestMapping(path="/Logout", method = RequestMethod.POST)
	public String Logout(HttpServletResponse res){
		Cookie cookie = new Cookie("LoginOK", null); 
		cookie.setPath("/");
		cookie.setMaxAge(0); 
		res.addCookie(cookie);
		return "Login";
	}
}