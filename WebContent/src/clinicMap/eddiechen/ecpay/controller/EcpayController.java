package clinicMap.eddiechen.ecpay.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import clinicMap.eddiechen.ecpay.model.EcpayService;

@Controller
public class EcpayController {
	

	@RequestMapping(path = "/test.all", method = RequestMethod.POST)
	private String AllInOne(HttpServletRequest request) throws IOException {
		EcpayService.initial();
		Cookie[] cookie = request.getCookies();
		String clinicAccount = cookie[0].getValue();
		request.setCharacterEncoding("UTF-8");
		request.setAttribute("formData", EcpayService.genAioCheckOutALL(clinicAccount));
		
		return "Test";
	}
	
}
