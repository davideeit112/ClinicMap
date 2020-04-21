package tw.eddiechen.backendsystem.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;

import tw.eddiechen.backendsystem.model.Clinic;
import tw.eddiechen.backendsystem.model.SysManagement;
import tw.eddiechen.backendsystem.model.SysManagementService;

@Controller
@SessionAttributes(names = { "acc", "pwd", "errors" })
public class SysManagementController {

	private SysManagementService sysService;

	@Autowired
	public SysManagementController(SysManagementService sysService) {
		this.sysService = sysService;
	}

	@RequestMapping(path = "/Login", method = RequestMethod.GET)
	public String processAction() {
		return "Login";
	}

	@RequestMapping(path = "/checkLogin", method = RequestMethod.POST)
	public String loginAction(@RequestParam("sysAcc") String acc, @RequestParam("sysPwd") String pwd, Model model) {

		Map<String, String> errors = new HashMap<String, String>();
		model.addAttribute("errors", errors);

		if (acc == null || acc.length() == 0) {
			errors.put("name", "name is required.");
		}

		if (pwd == null || pwd.length() == 0) {
			errors.put("pwd", "password is required.");
		}

		if (errors != null && !errors.isEmpty()) {
			return "Login";
		}

		model.addAttribute("acc", acc);
		model.addAttribute("pwd", pwd);

		boolean status = sysService.checkLogin(new SysManagement(acc, pwd));

		if (status) {
			return "System";
		}

		errors.put("msg", "username or password is not correct.");

		return "Login";
	}
	
	@RequestMapping(path = "/loadClinicData", method = RequestMethod.POST)
	public void loadClinicData(HttpServletResponse res) throws IOException {
		StringBuilder data = new StringBuilder();
		String clinicStatus;
		List<Clinic> data1 = sysService.clinicData();
		for(Clinic c: data1){
			String photoFilePathToSave = ("D:\\DataSource\\WORKSPACE\\FinalWorkSpace\\SpringBackEndSystem\\WebContent\\eddiechen\\imgs\\" + c.getClinicAccount() + ".jpg");
	        sysService.readPhotoOfPerson(c.getClinicID(), photoFilePathToSave);
	        String FinalPath = ("eddiechen/imgs/" + c.getClinicAccount() + ".jpg");
			if(c.getClinicStatus().equals("CS0") ){
				clinicStatus = "未認證";
			}else if (c.getClinicStatus().equals("CS1") ){
				clinicStatus = "已認證";
			}else {
				clinicStatus = "已認證且贊助升級";
			}
			System.out.println(FinalPath);
			if(c.getClinicStatus().equals("CS0")) {
			data.append("<tr><td><img src='" + FinalPath + "' /> </td>" );
			data.append("<td>" + c.getClinicAccount() + "</td>");
			data.append("<td>" + c.getClinicName() + "</td>");
			data.append("<td>" + c.getClinicAddress() + "</td>");
			data.append("<td>" + clinicStatus + "</td>");
			data.append("<td><form method='post' action='emailActivate'><input type='hidden' value='" + c.getClinicEmail() + "' name='clinicEmail'> <input type='hidden' value = '" + c.getClinicID() + "' name = 'clinicID'><input type = 'submit' value = '信箱認證' class='btn btn-light'> </form></td></tr>");
			}else{
				data.append("<tr><td><img src='" + FinalPath + "' /> </td>" );
				data.append("<td>" + c.getClinicAccount() + "</td>");
				data.append("<td>" + c.getClinicName() + "</td>");
				data.append("<td>" + c.getClinicAddress() + "</td>");
				data.append("<td>" + clinicStatus + "</td>");
				data.append("<td></td></tr>");
			}
		}
		
		String fullData = data.toString();
		PrintWriter out = res.getWriter();
		out.print(fullData);
		
	}
	
	@RequestMapping(path="/insertPhoto" , method = RequestMethod.GET)
	public String insertPhoto() throws IOException {
	       String photoFilePathToRead = "D:\\Test\\p19.jpg";
	       sysService.savePersonWithPhoto(photoFilePathToRead);
	       return "Login";
	}
	
//	@RequestMapping(path="/readPhoto", method = RequestMethod.GET)
//	public String readPhoto(HttpServletResponse res) throws IOException {
//        return "System";
//	}
	
	@RequestMapping(path="/emailActivate", method = RequestMethod.POST)
	public String email(HttpServletRequest req) {
		int clinicID = Integer.parseInt(req.getParameter("clinicID"));
		sysService.emailActivate(clinicID);
		return "System";
	}
	
	@RequestMapping(path="/changeStatus", method = RequestMethod.GET)
	public String changeStatus( HttpServletRequest req, int clinicID) {
		sysService.changeStatus(clinicID);
		return "System";
	}
}
