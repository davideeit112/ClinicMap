package clinicMap.tingyen.model;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;

import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

@Controller
public class ClinicController {
	@Autowired
	private AppointmentService aService;
	@Autowired
	private MemberService mService;
	@Autowired
	private ClinicService cService;
	@Autowired
	private ClinicOpenStatusService cosService;
	
	@RequestMapping(path = "/todayAppointments.do", method = RequestMethod.GET)
	public void todayAppointment(HttpServletRequest request, HttpServletResponse response) throws IOException { 
		SimpleDateFormat sdFormat = new SimpleDateFormat("yyyy-MM-dd"); 
		Date date = new Date();
		String today = sdFormat.format(date);
		
		Cookie[] cookieArray = request.getCookies(); 
		Map<String,Cookie> cookieMap = new HashMap<String,Cookie>();
		for(Cookie cookie:cookieArray) {
			cookieMap.put(cookie.getName(), cookie);
		}
		Cookie cookie = cookieMap.get("clinicId");
		System.out.println("clinicId:" + cookie.getValue());
		System.out.println("today:" + today);

		List<Appointment> beanList = aService.queryAllAppointment(cookie.getValue(), today); 

		JSONArray jsonArray = new JSONArray(); 
		PrintWriter out = response.getWriter();
		for (Appointment aBean : beanList) {
			JSONObject jsonObj = new JSONObject(aBean);
			Member mBean = mService.queryMemberById(aBean.getMemberID());
			jsonObj.put("memberName", mBean.getMemberName());
			jsonArray.put(jsonObj);
		}
		out.print(jsonArray);
	}

	@RequestMapping(path = "/updateAppointmentStatus.do", method = RequestMethod.GET)
	public void updateAppointmentStatus(@RequestParam(name = "appointmentID")String appointmentID, @RequestParam(name = "appointmentStatus")String appointmentStatus) { 
		if (appointmentStatus.equals("報到")) {
			appointmentStatus = "OS2";
		} else if (appointmentStatus.equals("過號")) {
			appointmentStatus = "OS4";
		}
		aService.updateAppointmentStatus(appointmentID, appointmentStatus);
	}

	@RequestMapping(path = "/finishAppointment.do", method = RequestMethod.GET)
	public void finishAppointment(@RequestParam(name = "currentNumber") int currentNumber, HttpServletRequest request) {
		System.out.println(currentNumber);
		SimpleDateFormat sdFormat = new SimpleDateFormat("yyyy-MM-dd");
		Date date = new Date();
		String today = sdFormat.format(date);

		Cookie[] cookieArray = request.getCookies();
		Map<String,Cookie> cookieMap = new HashMap<String,Cookie>();
		for(Cookie cookie:cookieArray) {
			cookieMap.put(cookie.getName(), cookie);
		}
		Cookie cookie = cookieMap.get("clinicId");

		List<Appointment> beanList = aService.queryAllAppointment(cookie.getValue(), today);

		for (Appointment aBean : beanList) {
			if (aBean.getAppointmentNumber() == currentNumber && aBean.getAppointmentStatus().equals("OS2")){
				aBean.setAppointmentStatus("OS3");
			}
		}
	}
	
	@RequestMapping(path = "/sendRemind.do", method = RequestMethod.GET)
	public void sendRemind(@RequestParam(name = "currentNumber") int currentNumber, HttpServletRequest request) {
		SimpleDateFormat sdFormat = new SimpleDateFormat("yyyy-MM-dd");
		Date date = new Date();
		String today = sdFormat.format(date);

		Cookie[] cookieArray = request.getCookies();
		Map<String,Cookie> cookieMap = new HashMap<String,Cookie>();
		for(Cookie cookie:cookieArray) {
			cookieMap.put(cookie.getName(), cookie);
		}
		Cookie cookie = cookieMap.get("clinicId");

		List<Appointment> beanList = aService.queryAllAppointment(cookie.getValue(), today);

		for (Appointment aBean : beanList) {
			if (aBean.getAppointmentNumber() - currentNumber == 3 && aBean.getAppointmentStatus() != "OS5") {
				Member mBean = mService.queryMemberById(aBean.getMemberID());				
				String to = mBean.getMemberEmail();	//user email
				String from = "clinicofmap@gmail.com";	//developer email
				String host = "smtp.gmail.com";
				int port = 587;
				String user = "clinicofmap@gmail.com";
				String password = "clinicmapemail";
				Properties properties = System.getProperties();
				properties.put("mail.smtp.auth", "true");
				properties.put("mail.smtp.starttls.enable", "true");
				properties.put("mail.smtp.host", host);
				properties.put("mail.smtp.port", port);
				properties.put("mail.debug", "true");
				Session session = Session.getDefaultInstance(properties, new Authenticator() {
			        protected PasswordAuthentication  getPasswordAuthentication() {
			            return new PasswordAuthentication(user, password);
			                }
			        });
				try {
					MimeMessage message = new MimeMessage(session);					
					message.setFrom(new InternetAddress(from));
					message.addRecipient(Message.RecipientType.TO, new InternetAddress(to));
					message.setSubject("診所預約提醒");
					message.setText("即將到您的預約號碼，請盡速前往診所報到。");
					Transport.send(message);	//寄出
				} catch (MessagingException mex) {
					mex.printStackTrace();
				}
			}
		}
	}
	
	@RequestMapping(path = "/ClinicProfile.do", method = RequestMethod.GET)
	public void queryClinicProfile(HttpServletRequest request,HttpServletResponse response) throws IOException {
		Cookie[] cookieArray = request.getCookies();
		Map<String,Cookie> cookieMap = new HashMap<String,Cookie>();
		for(Cookie cookie:cookieArray) {
			cookieMap.put(cookie.getName(), cookie);
		}
		Cookie cookie = cookieMap.get("clinicId");
		System.out.println("clinicId:" + cookie.getValue());
		Clinic cBean = cService.queryClinicProfile(Integer.valueOf(cookie.getValue()));
		String strBean = "";
		strBean += cBean.getClinicID() + "," + cBean.getClinicName()+"," + cBean.getClinicAccount() + "," +cBean.getClinicPwd() + "," + 
		cBean.getClinicAddress() + "," + cBean.getClinicDescription() + "," + cBean.getClinicPhoto() + "," + cBean.getClinicPhone() + "," +
		cBean.getClinicClass() + "," + cBean.getClinicType() +  "," + cBean.getClinicStatus();
		PrintWriter out = response.getWriter();
		out.print(strBean);
	}
	
	@RequestMapping(path = "/UpdateClinicProfile.do", method = RequestMethod.POST)
	public String updateClinicProfile(@RequestParam(name = "clinicID")String clinicID, 
			@RequestParam(name = "clinicName")String clinicName, 
			@RequestParam(name = "clinicAccount")String clinicAccount, 
			@RequestParam(name = "clinicPwd")String clinicPwd,
			@RequestParam(name = "clinicAddress")String clinicAddress, 
			@RequestParam(name = "clinicDescription")String clinicDescription, 
			@RequestParam(name = "clinicPhoto")MultipartFile  clinicPhotoFile, 
			@RequestParam(name = "clinicLicense")MultipartFile  clinicLicenseFile, 
			@RequestParam(name = "clinicEmail")String clinicEmail,
			@RequestParam(name = "clinicPhone")String clinicPhone, 
			@RequestParam(name = "clinicClass")String clinicClass,
			@RequestParam(name = "clinicType")String clinicType, 
			@RequestParam(name = "clinicStatus")String clinicStatus,HttpServletRequest request) throws IOException {

			HttpHeaders header=new HttpHeaders();
			header.setContentType(MediaType.IMAGE_JPEG);
			String clinicPhotoPath = request.getSession().getServletContext().getRealPath("/")+clinicPhotoFile.getOriginalFilename();	
			File savefile=new File(clinicPhotoPath);
			clinicPhotoFile.transferTo(savefile);
			InputStream is1 = new FileInputStream(clinicPhotoPath);
			byte[] clinicPhoto=new byte[is1.available()];
			is1.read(clinicPhoto);
			is1.close();

			HttpHeaders header1=new HttpHeaders();
			header1.setContentType(MediaType.IMAGE_JPEG);
			String clinicLicensePath = request.getSession().getServletContext().getRealPath("/")+clinicLicenseFile.getOriginalFilename();	
			File savefile1=new File(clinicLicensePath);
			clinicLicenseFile.transferTo(savefile1);
			InputStream is2 = new FileInputStream(clinicLicensePath);
			byte[] clinicLicense=new byte[is2.available()];
			is2.read(clinicLicense);
			is2.close();
		
		
		cService.updateClinicProfile(Integer.valueOf(clinicID), clinicName, clinicAccount, clinicPwd, clinicAddress, clinicDescription, clinicPhoto, clinicLicense, clinicEmail, clinicPhone, clinicClass, clinicType, clinicStatus);
		return "ClinicProfile";
	}
	
	@RequestMapping(path = "/DataAnalyze.do", method = RequestMethod.GET)
	public void dailyAppointmentDataAnalyze(@RequestParam(name="date")String timeType,HttpServletRequest request, HttpServletResponse response) throws IOException {
		Cookie[] cookieArray = request.getCookies();
		Map<String,Cookie> cookieMap = new HashMap<String,Cookie>();
		for(Cookie cookie:cookieArray) {
			cookieMap.put(cookie.getName(), cookie);
		}
		Cookie cookie = cookieMap.get("clinicId");
		System.out.println("clinicId:" + cookie.getValue());
	
		Map<String, Integer> mapData = aService.analyzeAppointmentData(cookie.getValue(), timeType);		
		String data = "";
		data += mapData.get("appointmentNum") + "," + mapData.get("firstTimeNum") + "," + mapData.get("completeDiagnosis") + "," + mapData.get("unReportNum");
		PrintWriter out = response.getWriter();
		out.print(data);
	}
	
	@RequestMapping(path = "/SaveReport.do", method = RequestMethod.GET)
	public void saveReport(@RequestParam(name="date")String timeType,HttpServletRequest request){
//		Map<String,Integer> map = new HashMap<String,Integer>();
//		map.put("預約人數", 10);
//		map.put("已診療人數", 7);
//		map.put("初診人數", 3);
//		map.put("未報到人數", 3);
		Cookie[] cookieArray = request.getCookies();
		Map<String,Cookie> cookieMap = new HashMap<String,Cookie>();
		for(Cookie cookie:cookieArray) {
			cookieMap.put(cookie.getName(), cookie);
		}
		Cookie cookie = cookieMap.get("clinicId");
		Map<String, Integer> map = aService.analyzeAppointmentData(cookie.getValue(), timeType);
		String dataHead[] = {"預約人數","已診療人數","初診人數","未報到人數"};
		int dataArray[] = {map.get("預約人數"),map.get("已診療人數"),map.get("初診人數"),map.get("未報到人數")};
		String timeTypeStr = "";
		if(timeType.length() == 4) {
			timeTypeStr="Yearly";
		}else if(timeType.length() == 7) {
			timeTypeStr="Monthly";
		}else{
			timeTypeStr="Daily";
		}
		HSSFWorkbook workbook= new HSSFWorkbook();
		HSSFSheet sheet= workbook.createSheet(timeTypeStr + "data");
		HSSFRow header= sheet.createRow(0);
		HSSFRow row= sheet.createRow(1);
		for(int i = 0;i<map.size();i++) {
			HSSFCell hCell= header.createCell(i);
			hCell.setCellValue(dataHead[i]);
			HSSFCell cell = row.createCell(i);
			cell.setCellValue(dataArray[i]);
		}
		try {
			FileOutputStream out = new FileOutputStream("D:/"+timeTypeStr+" report.xls");
			workbook.write(out);
			out.flush();
			out.close();
		} catch (IOException e) {
			e.printStackTrace();
		}		
	}
	
	@RequestMapping(path = "/UpdateOpenStatus.do", method = RequestMethod.GET)
	public void updateOpenStatus(@RequestParam(name="openStatus")String openStatus, 
			@RequestParam(name="currentNum")String currentNum,	HttpServletRequest request) {
		try {
			Cookie[] cookieArray = request.getCookies();
			Map<String,Cookie> cookieMap = new HashMap<String,Cookie>();
			for(Cookie cookie:cookieArray) {
				cookieMap.put(cookie.getName(), cookie);
			}
			Cookie cookie = cookieMap.get("clinicId");
			String clinicID = cookie.getValue();
			boolean openStatusBoolean;
			if(openStatus.equals("開診")) {
				openStatusBoolean = true;
			}else if(openStatus.equals("休診")){
				currentNum = "0";
				openStatusBoolean = false;
			}else {
				openStatusBoolean = false;
			}
			System.out.println(clinicID);	
			System.out.println("status:"+openStatus);
			System.out.println("Num:"+currentNum);
			cosService.updateStatus(clinicID,openStatusBoolean, Integer.valueOf(currentNum),openStatus);
		}catch(Exception e) {
			e.printStackTrace();
		}
		
	}
	
	@RequestMapping(path = "/saveCurrentNum.do", method = RequestMethod.GET)
	public void saveCurrentNum(	@RequestParam(name="currentNumber")String currentNum,HttpServletRequest request) {
		Cookie[] cookieArray = request.getCookies();
		Cookie cookie = cookieArray[0];
		String clinicID = cookie.getValue();
		System.out.println("currentNum:" + Integer.valueOf(currentNum)+1);
		cosService.saveCurrentNum(clinicID, Integer.valueOf(currentNum)+1);
	}
	
	@RequestMapping(path = "/GetCurrentStatus.do", method = RequestMethod.GET)
	public void getCurrentStatus(HttpServletRequest request,HttpServletResponse response) throws IOException {
		Cookie[] cookieArray = request.getCookies();
		Map<String,Cookie> cookieMap = new HashMap<String,Cookie>();
		for(Cookie cookie:cookieArray) {
			cookieMap.put(cookie.getName(), cookie);
		}
		Cookie cookie = cookieMap.get("clinicId");
		String clinicID = cookie.getValue();
		ClinicOpenStatus cosBean = cosService.getCurrentNumber(clinicID);
		
		PrintWriter out = response.getWriter();
		out.print(cosBean.getOpenDescription());
	}
	
	@RequestMapping(path = "/GetCurrentNumber.do", method = RequestMethod.GET)
	public void getCurrentNumber(HttpServletRequest request,HttpServletResponse response) throws IOException {
		Cookie[] cookieArray = request.getCookies();
		Map<String,Cookie> cookieMap = new HashMap<String,Cookie>();
		for(Cookie cookie:cookieArray) {
			cookieMap.put(cookie.getName(), cookie);
		}
		Cookie cookie = cookieMap.get("clinicId");
		String clinicID = cookie.getValue();
		ClinicOpenStatus cosBean = cosService.getCurrentNumber(clinicID);
		PrintWriter out = response.getWriter();
		out.print(cosBean.getClinicCurrentNumber());
	}
	
	@RequestMapping(path = "/GetClinicProfile.do", method = RequestMethod.GET)
	public void clinicProfile(HttpServletRequest request,HttpServletResponse response) throws IOException {
		Cookie[] cookieArray = request.getCookies();
		Map<String,Cookie> cookieMap = new HashMap<String,Cookie>();
		for(Cookie cookie:cookieArray) {
			cookieMap.put(cookie.getName(), cookie);
		}
		Cookie cookie = cookieMap.get("clinicId");
		String clinicID = cookie.getValue();
		System.out.println("clinicID:" + clinicID);
		Clinic cBean = cService.queryClinicProfile(Integer.valueOf(clinicID));
		JSONObject jsonObj = new JSONObject(cBean);
		PrintWriter out = response.getWriter();
		out.print(jsonObj);
	}
	@RequestMapping(path="/clinicMain",method=RequestMethod.GET)
	public String clinicMain() {
		return "ClinicMain";
		
	}
	@RequestMapping(path="/clinicProfile",method=RequestMethod.GET)
	public String clinicProfile() {
		return "ClinicProfile";
		
	}
	@RequestMapping(path="/count",method=RequestMethod.GET)
	public String count() {
		return "Count";
		
	}
}
