package tw.tingyen.controller;

import org.springframework.http.MediaType;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.Date;
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
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import tw.tingyen.model.Appointment;
import tw.tingyen.model.AppointmentService;
import tw.tingyen.model.Clinic;
import tw.tingyen.model.ClinicOpenStatus;
import tw.tingyen.model.ClinicOpenStatusService;
import tw.tingyen.model.ClinicService;
import tw.tingyen.model.Member;
import tw.tingyen.model.MemberService;

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
	
	@RequestMapping(path = "/enter.do", method = RequestMethod.GET)
	public String enter() {
		return "Clinic";
	}
	
	@RequestMapping(path = "/appointmentNum.do", method = RequestMethod.GET)
	public String enterAppointmentNum() {
		return "TestAppointmentNum";
	}
	
	@RequestMapping(path = "/download.do", method = RequestMethod.GET)
	public String enterDownload() {
		return "TestDownload";
	}
	
	@RequestMapping(path = "/todayAppointments.do", method = RequestMethod.GET)
	public void todayAppointment(HttpServletRequest request, HttpServletResponse response) throws IOException { 
		SimpleDateFormat sdFormat = new SimpleDateFormat("yyyy-MM-dd"); 
		Date date = new Date();
		String today = sdFormat.format(date);

		Cookie cookie1 = new Cookie("clinicId", "201332546");
		response.addCookie(cookie1);

		Cookie[] cookieArray = request.getCookies(); 
		Cookie cookie = cookieArray[1];
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
		if (appointmentStatus.equals("report")) {
			appointmentStatus = "OS3";
		} else if (appointmentStatus.equals("pass")) {
			appointmentStatus = "OS4";
		}
		aService.updateAppointmentStatus(appointmentID, appointmentStatus);
	}

	@RequestMapping(path = "/finishAppointment.do", method = RequestMethod.GET)
	public void finishAppointment(@RequestParam(name = "currentNumber") int currentNumber, HttpServletRequest request) { // 透過目前遷號碼修改預約狀態為已看診
		System.out.println(currentNumber);
		SimpleDateFormat sdFormat = new SimpleDateFormat("yyyy-MM-dd");
		Date date = new Date();
		String today = sdFormat.format(date);

		Cookie[] cookieArray = request.getCookies();
		Cookie cookie = cookieArray[1];

		List<Appointment> beanList = aService.queryAllAppointment(cookie.getValue(), today);

		for (Appointment aBean : beanList) {
			if (aBean.getAppointmentNumber() == currentNumber) {
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
		Cookie cookie = cookieArray[1];

		List<Appointment> beanList = aService.queryAllAppointment(cookie.getValue(), today);

		for (Appointment aBean : beanList) {
			if (aBean.getAppointmentNumber() - currentNumber == 3) {
				Member mBean = mService.queryMemberById(aBean.getMemberID());				
				String to = mBean.getMemberEmail();	//user email
				String from = "yen1996213@gmail.com";	//developer email
				String host = "smtp.gmail.com";
				int port = 587;
				String user = "yen1996213@gmail.com";
				String password = "yen12369874";
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
		Cookie cookie = cookieArray[1];
		System.out.println("clinicId:" + cookie.getValue());
		Clinic cBean = cService.queryClinicProfile(cookie.getValue());
		String strBean = "";
		strBean += cBean.getClinicID() + "," + cBean.getClinicName()+"," + cBean.getClinicAccount() + "," +cBean.getClinicPwd() + "," + 
		cBean.getClinicAddress() + "," + cBean.getClinicDescription() + "," + cBean.getClinicPhoto() + "," + cBean.getClinicPhone() + "," +
		cBean.getClinicClass() + "," + cBean.getClinicType() + "," + cBean.getClinicTime() + "," + cBean.getClinicStatus();
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
			@RequestParam(name = "clinicPhone")String clinicPhone, 
			@RequestParam(name = "clinicClass")int clinicClass,
			@RequestParam(name = "clinicType")int clinicType, 
			@RequestParam(name = "clinicTime")int clinicTime, 
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
		cService.updateClinicProfile(clinicID, clinicName, clinicAccount, clinicPwd, clinicAddress, clinicDescription, clinicPhoto, clinicPhone, clinicClass, clinicType, clinicTime, clinicStatus);
		return "ClinicProfile";
	}
	
	@RequestMapping(path = "/DataAnalyze.do", method = RequestMethod.GET)
	public void dailyAppointmentDataAnalyze(@RequestParam(name="date")String timeType,HttpServletRequest request, HttpServletResponse response) throws IOException {
		Cookie[] cookieArray = request.getCookies();
		Cookie cookie = cookieArray[1];
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
		Cookie cookie = cookieArray[1];
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
			@RequestParam(name="currentNum")String currentNum,HttpServletRequest request) {
		Cookie[] cookieArray = request.getCookies();
		Cookie cookie = cookieArray[1];
		String clinicID = cookie.getValue();
		boolean openStatusBoolean;
		if(openStatus.equals("open")) {
			openStatusBoolean = true;
		}else if(openStatus.equals("close")){
			currentNum = "0";
			openStatusBoolean = false;
		}else {
			openStatusBoolean = false;
		}
		System.out.println(clinicID);
		System.out.println("status:"+openStatus);
		System.out.println("Num:"+currentNum);
		cosService.updateStatus(clinicID,openStatusBoolean, Integer.valueOf(currentNum));
	}
	
	@RequestMapping(path = "/GetCurrentNumber.do", method = RequestMethod.GET)
	public void getCurrentNumber(HttpServletRequest request,HttpServletResponse response) throws IOException {
		Cookie[] cookieArray = request.getCookies();
		Cookie cookie = cookieArray[1];
		String clinicID = cookie.getValue();
		ClinicOpenStatus cosBean = cosService.getCurrentNumber(clinicID);
		PrintWriter out = response.getWriter();
		out.print(cosBean.getClinicCurrentNumber());
	}
	
	@RequestMapping(path = "/GetClinicProfile.do", method = RequestMethod.GET)
	public void clinicProfile(HttpServletRequest request,HttpServletResponse response) throws IOException {
		Cookie[] cookieArray = request.getCookies();
		Cookie cookie = cookieArray[1];
		String clinicID = cookie.getValue();
		System.out.println("clinicID:" + clinicID);
		Clinic cBean = cService.queryClinicProfile(clinicID);
		JSONObject jsonObj = new JSONObject(cBean);
		PrintWriter out = response.getWriter();
		out.print(jsonObj);
	}
}
