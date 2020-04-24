package clinicMap.register;

import java.io.IOException;
import java.util.Date;
import java.util.List;
import java.util.Properties;
import java.util.UUID;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;

import org.hibernate.SessionFactory;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;


@Controller
public class ActionController {
	
	@Autowired
	private MemberDAO mDao;
	
	@RequestMapping(path = "/loginPage", method = RequestMethod.POST)
	public void loginVerified(@RequestParam("loginLevel") String loginLevel, @RequestParam("account") String account, @RequestParam("pwd") String pwd, HttpServletResponse response) {
		try {
			boolean checklogin = false, checkstatus = false;
			Object testReturn = mDao.checkLogin(loginLevel, account, pwd);
			if(testReturn == null) {
				//表示沒有資料
				System.out.println("need to register");
				response.getWriter().print(checklogin);
			}else {
				checklogin = true;
				//有資料 => 檢查啟用狀態
				if(loginLevel.equals("member")) {
					Memberde member = (Memberde)testReturn;
					if(member.getMemberStatus().equals("MS1")) {
						checkstatus = true;
						
						//建立cookie
						Cookie memberAccountCookie = new Cookie("memberID", Integer.toString(member.getMemberID()));
						memberAccountCookie.setMaxAge(-1); //-1:browser結束即關閉
						memberAccountCookie.setPath("/clinicMap");
						response.addCookie(memberAccountCookie);

						System.out.println("Member : already active");
					}else {
						System.out.println("Member : not active yet");
					}
				}else {
					Clinicchuder clinic = (Clinicchuder)testReturn;
					if(clinic.getClinicStatus().equals("CS1")) {
						checkstatus = true;
						
						//建立cookie
						Cookie clinicAccountCookie = new Cookie("clinicID", Integer.toString(clinic.getClinicID()));
						clinicAccountCookie.setMaxAge(-1); //-1:browser結束即關閉
						clinicAccountCookie.setPath("/clinicMap");
						response.addCookie(clinicAccountCookie);

						System.out.println("Clinic : already active");
					}else {
						System.out.println("Clinic : not active yet");
					}
				}
				response.getWriter().print(checklogin + "," + checkstatus);
			}// else: searching Info : not null
		}catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	@RequestMapping(path = "/memberRegisterPage", method = RequestMethod.POST)
	public String memberRegister(@RequestParam("memberAccount") String account, @RequestParam("memberName") String name, @RequestParam("memberPwd") String pwd, @RequestParam("memberBirth") Date birth,
			@RequestParam("memberAddress") String address, @RequestParam("memberHeight") int height, @RequestParam("memberWeight") int weight, @RequestParam("memberEmail") String email, 
			@RequestParam("memberPhone") String phone, @RequestParam("memberIdNumber") String IdNumber, @RequestParam("memberGender") String gender) {
		
		String code = uuidCode();
		
		//之後再加格式的檢查
		
		Memberde m = new Memberde();
		m.setMemberAccount(account);
		m.setMemberName(name);
		m.setMemberPwd(pwd);
		m.setMemberBirth(birth);
		m.setMemberAddress(address);
		m.setMemberHeight(height);
		m.setMemberWeight(weight);
		m.setMemberEmail(email);
		m.setMemberPhone(phone);
		m.setMemberIdNumber(IdNumber);
		if(gender.equals("male")) {
			m.setMemberGender("MG1");
		}else {
			m.setMemberGender("MG2");
		}
		m.setMemberStatus("MS0");
		m.setMemberVerifiedCode(code);
		m.setMemberRegisterDeadline(new Date().getTime());
		
		mDao.doRegisterM(m);
		sendEmail("verified", email, code);
		
		return "login";
	}
	
	@RequestMapping(path = "/clinicRegisterPage", method = RequestMethod.POST)
	public void clinicRegister(@RequestParam("clinicAccount") String account, @RequestParam("clinicEmail") String email, HttpServletResponse response) {
		mDao.doRegisterC(account, email);
		try {
			response.getWriter().print("");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
//*************************************
	@RequestMapping(path = "/verifiedEmail", method = RequestMethod.GET, produces = "text/plain;charset=UTF-8")
	public @ResponseBody String emailVerified(@RequestParam("code") String code) {
		
		//之後再改成 Y/N 啟動成功，丟到不同頁面???
		Member member = mDao.getInfoWithCode(code);
		if(member!=null) { //不是過期後又重複點同一封 驗證信
			long deadLine = member.getMemberRegisterDeadline();
			
			//java.util.Date().getTime() 取得的時間單位為 "毫秒"
			//now-deadLine(ms)*1000=>(sec)*60=>(minute)
			int timeLimit = 1000*60*5; //5分鐘
			long now = new Date().getTime();
			System.out.println("Now= " + now);
			
			//時間內註冊成功
			if(now-deadLine<=timeLimit) {
				mDao.setActiveStatus(code);
				return "Verified Success";
			}
		}
		return "verified out of time, please get verified emai again";
	}

	@RequestMapping(path = "forgetPwdPage", method = RequestMethod.POST)
	public void forgetPwd(@RequestParam("account") String account, @RequestParam("email") String email, HttpServletResponse response) {
		//產生tempCode 的初始資料
		String codeData = "0123456789abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ!@#$%^&*";
		String tempPwd = ""; //8位數暫時密碼
		
		while(tempPwd.length()<8) {
			tempPwd += codeData.charAt((int)((Math.random()*70)+1));
		}
		
		mDao.setTempPwd(account, tempPwd);
		sendEmail("forget", email, tempPwd);
		
		//下面的兩種方法選一個，否則會無限迴圈寄暫時密碼
		try {
			response.getWriter().print("");
		} catch (IOException e) {
			e.printStackTrace();
		}
//		return "login";
	}
	
	@RequestMapping(path = "/isAccountExist", method = RequestMethod.POST)
	public void checkAccountExist(@RequestParam("loginLevel") String loginLevel, @RequestParam("account") String account, HttpServletResponse response) {
		try {
			response.getWriter().print(mDao.checkAccountExistDao(loginLevel, account));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	@RequestMapping(path = "/isEmailExist", method = RequestMethod.POST)
	public void checkEmailRepeat(@RequestParam("email") String email, HttpServletResponse response) {
		try {
			response.getWriter().print(mDao.checkEmailExistDao(email));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}


//*******************************************
	@RequestMapping(path = "/isIdNumExist", method = RequestMethod.POST)
	public void checkIdNumRepeat(@RequestParam("IdNum") String IdNum, HttpServletResponse response){
		try {
			response.getWriter().print(mDao.checkIdNumExistDao(IdNum));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	
//*******************************************
	@RequestMapping(path = "/getEmailAgainPage", method = RequestMethod.POST)
	public void getEmailAgain(@RequestParam("account") String account, @RequestParam("email") String email, HttpServletResponse response) {

		String codeAgain = uuidCode();
		mDao.setActiveStatus(codeAgain, account);

		//更改 deadline的時間
		mDao.updateDeadline(account, new Date().getTime());
		
		//重新寄出驗證信的狀況
		sendEmail("verified", email, codeAgain);
		
		try {
			response.getWriter().print("");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	@RequestMapping(path = "/queryAllMembers", method = RequestMethod.POST)
	public void queryAllM(HttpServletResponse response) {
		List<Memberde> members = mDao.queryAllMember();
		
		JSONArray json = new JSONArray();
		for(Memberde m : members) {
			JSONObject jobj = new JSONObject(m);
			json.put(jobj);
		}
		
		try {
			response.getWriter().print(json);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	@RequestMapping(path = "/getPwdForRm", method = RequestMethod.POST)
	public void getPwdForRememberMe(@RequestParam("loginLevel") String loginLevel, @RequestParam("account") String account, HttpServletResponse response) throws IOException {
		Object result = mDao.RememberMePwd(loginLevel, account);
		if(result == null) { System.out.println("Error??");}
		else {
			if(loginLevel.equals("member")) {
				Memberde m = (Memberde)result;
				response.getWriter().print(m.getMemberPwd());
			}else {
				Clinicchuder c = (Clinicchuder)result;
				response.getWriter().print(c.getClinicPwd());
			}
		}
	}
	
	//----------上：RequestMapping------------下：method------------------------------------------
	
	public String uuidCode() {
		UUID uuid = UUID.randomUUID();
		String code = uuid.toString().replaceAll("-", "");
		return code;
	}
	
	public void sendEmail(String action, String email, String code) {
		String from = "johnnyproject.test@gmail.com";
	    final String username = "johnnyproject.test";
	    final String password = "uexcrypqlhywruhf";
	    String host = "smtp.gmail.com";
	    
	    Properties props = new Properties();
	    props.put("mail.smtp.auth", "true");
	    props.put("mail.smtp.starttls.enable", "true");
	    props.put("mail.smtp.host", host);
	    props.put("mail.smtp.port", "25");
	    
	    Session session = Session.getInstance(props, new javax.mail.Authenticator() {
	 	          protected PasswordAuthentication getPasswordAuthentication() {
	 	             return new PasswordAuthentication(username, password);
	 	          }
	 		});

	    try {
		  	  Message message = new MimeMessage(session);
			  message.setFrom(new InternetAddress(from));
			  message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(email));
			  
			  String content = "";
			  //===============================================
			  if(action.equals("verified")) {
				  message.setSubject("ClinicMap Account Verified");
				  content = "<html><head>"
//				  		+ "<script src=\"http://ajax.googleapis.com/ajax/libs/jquery/1.8.0/jquery.min.js\"></script>"
					   		+ "</head><body><a href='http://localhost:8080/clinicMap/verifiedEmail?code=" + code + "'>click to verified</a>"
//					   				+ "<script>$(function(){\r\n" + 
//					   				"            $.get({\r\n" + 
//					   				"                url:\"../verifiedEmail\",\r\n" + 
//					   				"                data:{\r\n" + 
//					   				"                    \"code\":$(\"a.value\")\r\n" + 
//					   				"                }\r\n" + 
//					   				"            })\r\n" + 
//					   				"        })</script>"
					   				+ "</body></html>";
			  }
			  
			  if(action.equals("forget")) {
				  System.out.println("forget");
				  message.setSubject("ClinicMap Temp Password");
				  content = "<html><head></head><body>親愛的用戶，您好：<br/>您的暫時密碼為：" + code 
						  + "<br/>為保障您的帳戶安全，請您盡早登入並修改密碼。<br/>謝謝</body></html>";
			  }
			  
			  //===============================================
			   message.setContent(content, "text/html;charset=UTF-8");

			   // Send message
			   Transport.send(message);
			   System.out.println("Sent message successfully....");

		    } catch (MessagingException e) {
		       throw new RuntimeException(e);
		    }
	}
	//網址
	@RequestMapping(path = "/loginIn")
	public String login() {
		return "loginMember";
	}
	@RequestMapping(path="/RegisterPage")
	public String RegisterPage() {
		return "register";
	}
}
