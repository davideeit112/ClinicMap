package clinicMap.eddiechen.backendsystem.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
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
//@SessionAttributes(names = { "acc", "pwd", "errors" })
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
			return new ModelAndView("redirect:/backLogin.html");
		}

		//model.addAttribute("acc", acc);
		//model.addAttribute("pwd", pwd);

		boolean status = sysService.checkLogin(new SysManagement(acc, pwd));
		//model.addAttribute("status", status);
		if (status) {
			Cookie cookie = new Cookie("LoginOK", acc);
			cookie.setPath("/");
			cookie.setMaxAge(60*60*24);
			res.addCookie(cookie);
			return new ModelAndView("redirect:/System.html");
		}

		errors.put("msg", "username or password is not correct.");

		return new ModelAndView("redirect:/backLogin.html");
	}

	@RequestMapping(path = "/loadClinicData", method = RequestMethod.GET)
	public void loadClinicData(HttpServletResponse res, HttpServletRequest req) throws IOException, ServletException {
		Map<String, String> checkLog = new HashMap<String, String>();
		Cookie[] cookies = req.getCookies();
		for(Cookie cook: cookies) {
			checkLog.put(cook.getName() , cook.getValue());
		}
		System.out.println(checkLog.get("LoginOK"));
		if(checkLog.get("LoginOK") == null) {	
			RequestDispatcher rd = req.getRequestDispatcher("backLogin.html");
			rd.forward(req, res);
		}
		
		if(checkLog.get("LoginOK").equals("Tost180.")) {
		
		// StringBuilder data = new StringBuilder();
		String clinicStatus;
		List<Clinicchen> data1 = sysService.clinicData();

		JSONArray js = new JSONArray();
		for (Clinicchen c : data1) {
//			String photoFilePathToSave = ("D:\\DataSource\\WORKSPACE\\FinalWorkSpace\\SpringBackEndSystem\\WebContent\\eddiechen\\imgs\\" + c.getClinicAccount() + ".jpg");
//	        sysService.readPhotoOfPerson(c.getClinicID(), photoFilePathToSave);
			String FinalPath = ("eddiechen/imgs/" + c.getClinicAccount() + ".jpg");
			String Description = "";
			String Time = "";
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
			
			if(c.getClinicDescription()!=null) {
				Description = c.getClinicDescription();
			}
			
			if(c.getClinicTime()!=null) {
				Time = c.getClinicTime();
			}
			
			
			String clinicLicense= "<img src='" + FinalPath + "' style='width:60px; height:80px;' id='"+ c.getClinicAccount() +"1' 	/>	" +
				    "<div class='modal fade' id='" + c.getClinicAccount()+ "' role='dialog'>" + 
					"		<div class='modal-dialog modal-lg'>" + 
					"			<div class='modal-content'>" + 
					"				<div class='modal-body'>" + 
					"					<img src='" + FinalPath + "' style='height:800px;' />" + 
					"				</div>" + 
					"			</div>" + 
					"		</div>" + 
					"	</div> <script type='text/javascript'> $('#"+c.getClinicAccount() +"1').click(function(){$('#" +c.getClinicAccount() +"').modal()})   </script>";
			
			String emailVerify = "<form method='post' action='emailActivate'><input type='hidden' value='"
					+ c.getClinicEmail() + "' name='clinicEmail'> <input type='hidden' value = '" + c.getClinicID()
					+ "' name = 'clinicID'><input type = 'submit' value = '信箱認證' class='btn btn-light'> </form>"
					
						+ "<button type='button' data-toggle='modal' data-target='#" + c.getClinicID() + "1' id = '" + c.getClinicID() + "1' class='btn btn-light' style='margin-top:5px'>詳細資料</button>"
						+ "<div class='modal fade' id ='" + c.getClinicID() + "' role='dialog'>"
						+ "		<div class='modal-dialog'>" 
						+ "			<div class='modal-content' style='width:500px;'>"  
						+ "				<div class='modal-body' style='width:100%; padding:0px;'>"  
						+ "					<form action='updateData' method='post' style='margin:0px;width:100%'>"
						+ "						<table  style='width:498px; margin:0px'>"
						+ "							<tbody  style='width:498px;'>"
						+ "							<tr style='width:498px'><td>ClinicID:<input type='text' value='" + c.getClinicID() + "' readonly style='float:right;width:300px' name='clinicid'/></td></tr>"
						+ "							<tr style='width:498px'><td>ClinicName:<input type='text' value='" + c.getClinicName() + "' readonly style='float:right;width:300px' name='clinicname'/></td></tr>"
						+ "							<tr style='width:498px'><td>ClinicAccount:<input type='text' value='" + c.getClinicAccount() + "' readonly style='float:right;width:300px' name='clinicaccount'/></td></tr>"
						+ "							<tr style='width:498px'><td>ClinicPwd:<input type='text' value='" + c.getClinicPwd()  + "' readonly style='float:right;width:300px' name='clinicpwd'/></td></tr>"
						+ "							<tr style='width:498px'><td>ClinicAddress:<input type='text' value='" + c.getClinicAddress() + "' readonly style='float:right;width:300px' name='clinicaddress'/></td></tr>"
						+ "							<tr style='width:498px'><td>ClinicDescription:<input type='text' value='" + Description + "' readonly style='float:right;width:300px' name='clinicdescription'/></td></tr>"
						+ "							<tr style='width:498px'><td>ClinicPhone:<input type='text' value='" + c.getClinicPhone() + "' readonly style='float:right;width:300px' name='clinicphone'/></td></tr>"
						+ "							<tr style='width:498px'><td>ClinicEmail:<input type='text' value='" + c.getClinicEmail() + "' readonly style='float:right;width:300px' name='clinicemail'/></td></tr>"
						+ "							<tr style='width:498px'><td>ClinicClass:<input type='text' value='" + c.getClinicClass() + "' readonly style='float:right;width:300px' name='clinicclass'/></td></tr>"
						+ "							<tr style='width:498px'><td>ClinicType:<input type='text' value='" + c.getClinicType() + "' readonly style='float:right;width:300px' name='clinictype'/></td></tr>"
						+ "							<tr style='width:498px'><td>ClinicTime:<input type='text' value='" + Time + "' readonly style='float:right;width:300px' name='clinictime'/></td></tr>"
						+ "							<tr style='width:498px'><td>ClinicLat:<input type='text' value='" + c.getCliniclat() + "' readonly style='float:right;width:300px' name='cliniclat'/></td></tr>"
						+ "							<tr style='width:498px'><td>ClinicLng:<input type='text' value='" + c.getCliniclng() + "' readonly style='float:right;width:300px' name='cliniclng'/></td></tr>"
						+ "							<tr style='width:498px'><td>ClinicStatus:<input type='text' value='" + clinicStatus + "' readonly style='float:right;width:300px' name='clinicstatus'/></td></tr>"
						+ "							<tr style='width:498px;'><td><input type='submit' value='修改資料' style='margin:auto'/></td></tr>"
						+ "							</tbody>"
						+ "						</table>"
						+ "					</form>"
						+ "				</div>"  
						+ "			</div>"  
						+ "		</div>"  
						+ "	</div><script type='text/javascript'> $('#" + c.getClinicID() + "1').click(function(){$('#" + c.getClinicID() + "').modal()}) </script>" ;
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
		}else {
			
			// StringBuilder data = new StringBuilder();
			String clinicStatus;
			List<Clinicchen> data1 = sysService.clinicData();
			System.out.println("hello");
			JSONArray js = new JSONArray();
			for (Clinicchen c : data1) {
//				String photoFilePathToSave = ("D:\\DataSource\\WORKSPACE\\FinalWorkSpace\\SpringBackEndSystem\\WebContent\\eddiechen\\imgs\\" + c.getClinicAccount() + ".jpg");
//		        sysService.readPhotoOfPerson(c.getClinicID(), photoFilePathToSave);
				String FinalPath = ("eddiechen/imgs/" + c.getClinicAccount() + ".jpg");
				String Description = "";
				String Time = "";
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
				
				if(c.getClinicDescription()!=null) {
					Description = c.getClinicDescription();
				}
				
				if(c.getClinicTime()!=null) {
					Time = c.getClinicTime();
				}
					

				String clinicLicense= "<img src='" + FinalPath + "' style='width:60px; height:80px;' id='"+ c.getClinicAccount() +"1' 	/>	" +
					    "<div class='modal fade' id='" + c.getClinicAccount()+ "' role='dialog'>" + 
						"		<div class='modal-dialog modal-lg'>" + 
						"			<div class='modal-content'>" + 
						"				<div class='modal-body'>" + 
						"					<img src='" + FinalPath + "' style='height:800px;' />" + 
						"				</div>" + 
						"			</div>" + 
						"		</div>" + 
						"	</div> <script type='text/javascript'> $('#"+c.getClinicAccount() +"1').click(function(){$('#" +c.getClinicAccount() +"').modal()})   </script>";
				
				String emailVerify = "<form method='post' action='emailActivate'><input type='hidden' value='"
						+ c.getClinicEmail() + "' name='clinicEmail'> <input type='hidden' value = '" + c.getClinicID()
						+ "' name = 'clinicID'><input type = 'submit' value = '信箱認證' class='btn btn-light'> </form>"

						+ "<button type='button' data-toggle='modal' data-target='#" + c.getClinicID() + "1' id = '" + c.getClinicID() + "1' class='btn btn-light' style='margin-top:5px'>詳細資料</button>"
						+ "<div class='modal fade' id ='" + c.getClinicID() + "' role='dialog'>"
						+ "		<div class='modal-dialog'>" 
						+ "			<div class='modal-content' style='width:500px;'>"  
						+ "				<div class='modal-body' style='width:100%; padding:0px;'>"  
						+ "					<form action='updateData' method='post' style='margin:0px;width:100%'>"
						+ "						<table  style='width:498px; margin:0px'>"
						+ "							<tbody  style='width:498px;'>"
						+ "							<tr style='width:498px'><td>ClinicID:<input type='text' value='" + c.getClinicID() + "' readonly style='float:right;width:300px' name='clinicid'/></td></tr>"
						+ "							<tr style='width:498px'><td>ClinicName:<input type='text' value='" + c.getClinicName() + "' style='float:right;width:300px' name='clinicname'/></td></tr>"
						+ "							<tr style='width:498px'><td>ClinicAccount:<input type='text' value='" + c.getClinicAccount() + "' style='float:right;width:300px' name='clinicaccount'/></td></tr>"
						+ "							<tr style='width:498px'><td>ClinicPwd:<input type='text' value='" + c.getClinicPwd()  + "' style='float:right;width:300px' name='clinicpwd'/></td></tr>"
						+ "							<tr style='width:498px'><td>ClinicAddress:<input type='text' value='" + c.getClinicAddress() + "' style='float:right;width:300px' name='clinicaddress'/></td></tr>"
						+ "							<tr style='width:498px'><td>ClinicDescription:<input type='text' value='" + Description + "' style='float:right;width:300px' name='clinicdescription'/></td></tr>"
						+ "							<tr style='width:498px'><td>ClinicPhone:<input type='text' value='" + c.getClinicPhone() + "' style='float:right;width:300px' name='clinicphone'/></td></tr>"
						+ "							<tr style='width:498px'><td>ClinicEmail:<input type='text' value='" + c.getClinicEmail() + "' style='float:right;width:300px' name='clinicemail'/></td></tr>"
						+ "							<tr style='width:498px'><td>ClinicClass:<input type='text' value='" + c.getClinicClass() + "' style='float:right;width:300px' name='clinicclass'/></td></tr>"
						+ "							<tr style='width:498px'><td>ClinicType:<input type='text' value='" + c.getClinicType() + "' style='float:right;width:300px' name='clinictype'/></td></tr>"
						+ "							<tr style='width:498px'><td>ClinicTime:<input type='text' value='" + Time + "' style='float:right;width:300px' name='clinictime'/></td></tr>"
						+ "							<tr style='width:498px'><td>ClinicLat:<input type='text' value='" + c.getCliniclat() + "' style='float:right;width:300px' name='cliniclat'/></td></tr>"
						+ "							<tr style='width:498px'><td>ClinicLng:<input type='text' value='" + c.getCliniclng() + "' style='float:right;width:300px' name='cliniclng'/></td></tr>"
						+ "							<tr style='width:498px'><td>ClinicStatus:<input type='text' value='" + clinicStatus + "' readonly style='float:right;width:300px' name='clinicstatus'/></td></tr>"
						+ "							<tr style='width:498px; margin:auto;' ><td class='dt-center'><input type='submit' value='修改資料' /></td></tr>"
						+ "							</tbody>"
						+ "						</table>"
						+ "					</form>"
						+ "				</div>"  
						+ "			</div>"  
						+ "		</div>"  
						+ "	</div><script type='text/javascript'> $('#" + c.getClinicID() + "1').click(function(){$('#" + c.getClinicID() + "').modal()}) </script>" ;
				
				cP.setClinicID(c.getClinicID());
				cP.setClinicAccount(c.getClinicAccount());
				cP.setClinicName(c.getClinicName());
				cP.setClinicAddress(c.getClinicAddress());
				cP.setClinicStatus(clinicStatus);
				cP.setClinicEmail(emailVerify);
				cP.setClinicLicense(clinicLicense);

				JSONObject jo = new JSONObject(cP);
				js.put(jo);

//				if (c.getClinicStatus().equals("CS0")) {
//					data.append("<tr><td><img src='" + FinalPath + "' /> </td>");
//					data.append("<td>" + c.getClinicAccount() + "</td>");
//					data.append("<td>" + c.getClinicName() + "</td>");
//					data.append("<td>" + c.getClinicAddress() + "</td>");
//					data.append("<td>" + clinicStatus + "</td>");
//					data.append("<td><form method='post' action='emailActivate'><input type='hidden' value='"
//							+ c.getClinicEmail() + "' name='clinicEmail'> <input type='hidden' value = '" + c.getClinicID()
//							+ "' name = 'clinicID'><input type = 'submit' value = '信箱認證' class='btn btn-light'> </form></td></tr>");
//				} else {
//					data.append("<tr><td><img src='" + FinalPath + "' /> </td>");
//					data.append("<td>" + c.getClinicAccount() + "</td>");
//					data.append("<td>" + c.getClinicName() + "</td>");
//					data.append("<td>" + c.getClinicAddress() + "</td>");
//					data.append("<td>" + clinicStatus + "</td>");
//					data.append("<td></td></tr>");
//				}
			}

//			String fullData = data.toString();
			PrintWriter out = res.getWriter();
			out.print(js);
		}
	}

	@RequestMapping(path = "/insertPhoto", method = RequestMethod.GET)
	public String insertPhoto() throws IOException {
		String photoFilePathToRead = "D:\\Test\\p19.jpg";
		
		for(int count = 1005; count<=1100; count ++){
		sysService.savePersonWithPhoto(photoFilePathToRead, count);
		}
		return "backLogin";
	}

//	@RequestMapping(path="/readPhoto", method = RequestMethod.GET)
//	public String readPhoto(HttpServletResponse res) throws IOException {
//        return "System";
//	}

	@RequestMapping(path = "/emailActivate", method = RequestMethod.POST)
	public ModelAndView email(HttpServletRequest req, HttpServletResponse res) throws IOException {
		Map<String, String> checkLog = new HashMap<String, String>();
		Cookie[] cookies = req.getCookies();
		for(Cookie cook: cookies) {
			checkLog.put(cook.getName() , cook.getValue());
		}
			if(checkLog.get("LoginOK") == null) {
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
		return "ClinicProfile";
	}
	
	@RequestMapping(path="/Logout", method = RequestMethod.POST)
	public String Logout(HttpServletResponse res){
		Cookie cookie = new Cookie("LoginOK", null); 
		cookie.setPath("/");
		cookie.setMaxAge(0); 
		res.addCookie(cookie);
		return "backLogin";
	}
	
	@RequestMapping(path="/updateData", method = RequestMethod.POST)
	public ModelAndView updatedata(@RequestParam("clinicid") String clinicid, @RequestParam("clinicname") String clinicname,@RequestParam("clinicaccount") String clinicaccount, @RequestParam("clinicpwd") String clinicpwd,@RequestParam("clinicaddress") String clinicaddress, @RequestParam("clinicdescription") String clinicdescription,@RequestParam("clinicphone") String clinicphone, @RequestParam("clinicemail") String clinicemail,@RequestParam("clinicclass") String clinicclass, @RequestParam("clinictype") String clinictype,@RequestParam("clinictime") String clinictime, @RequestParam("cliniclat") String cliniclat, @RequestParam("cliniclng") String cliniclng, @RequestParam("clinicstatus") String clinicstatus) {
		String clinicStatus="";
		if (clinicstatus.equals("尚未核對")) {
			clinicStatus = "CS0";
		} else if (clinicstatus.equals("信箱認證中")) {
			clinicStatus = "CS1";
		} else if(clinicstatus.equals("已認證")){
			clinicStatus = "CS2";
		}else {
			clinicStatus = "CS3";
		}
		sysService.updateData(Integer.parseInt(clinicid), clinicname, clinicaccount, clinicpwd, clinicaddress, clinicdescription, clinicphone, clinicemail, clinicclass, clinictype, clinictime, cliniclat, cliniclng, clinicStatus);
		return new ModelAndView("redirect:/System.html");
	}
	
	@RequestMapping(path="/announcement", method=RequestMethod.POST)
	public ModelAndView UpdateAnnouncement(@RequestParam("id") String id, @RequestParam("type") String type,@RequestParam("text") String text ) {
		
		sysService.updateAnnouncement(id, type, text);
		return new ModelAndView("redirect:/System.html");
	}
	                       
	@RequestMapping(path="/AnnouncementText", method=RequestMethod.POST)
	public void AnnouncementText(HttpServletResponse res) throws IOException {
		System.out.println("0000");
		String text = sysService.UpdateAnnouncementText();
		res.setContentType("text/html;charset=UTF-8");
		PrintWriter out = res.getWriter();
		System.out.println(text);
		out.write(text);
		
	}
	
	
}