package clinicMap.eddiechen.ecpay.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import clinicMap.eddiechen.ecpay.model.EcpayService;

@Controller
public class EcpayController {


	@RequestMapping(path = "/AdPayment", method = RequestMethod.POST)
	public void AdPayment(HttpServletRequest request, HttpServletResponse res) throws IOException {
		EcpayService.initial();
		
//		Cookie[] cookies = request.getCookies();
//		Map<String, String> cookieMap = new HashMap<String,String>();
//		
//		for(Cookie c: cookies) {
//			cookieMap.put(c.getName(), c.getValue());
//		}
//		
//		String clinicID = cookieMap.get("clinicID");
		
		UUID uid = UUID.randomUUID();
		String uuid = uid.toString().replaceAll("-", "").substring(0, 20);
		
		res.setContentType("text/html;charset=UTF-8");
		PrintWriter out = res.getWriter();
		out.write(EcpayService.genAdPayment(uuid/*, clinicID*/));
		
	}
	
	
	@RequestMapping(path="/changeStatusAfterPay", method=RequestMethod.GET)
	public String changeStatusAfterPay(@RequestParam("id") String uuid, /*String clinicID, */HttpServletRequest req, HttpServletResponse res) throws IOException {

		EcpayService.changeStatus(1001/*clinicID*/);
		
		return "loginMember";
	}
	
	@RequestMapping(path = "/TexiPayment", method = RequestMethod.GET)
	public void TexiPayment(@RequestParam("pricetotal") String pricetotal, @RequestParam("positionidcheck")String positionidcheck, HttpServletRequest request, HttpServletResponse res) throws IOException {
		
		System.out.println("測試"+positionidcheck);
		EcpayService.initial();
//		Cookie[] cookies = request.getCookies();
//		Map<String, String> cookieMap = new HashMap<String, String>();
//		
//		for(Cookie c: cookies) {
//			cookieMap.put(c.getName(), c.getValue());
//		}
		
		UUID uid = UUID.randomUUID();
		String uuid = uid.toString().replaceAll("-", "").substring(0, 20);
		PrintWriter out = res.getWriter();
		out.print(EcpayService.genTexiPayment(uuid, pricetotal, positionidcheck));
		
	
		
	}
	
	@RequestMapping(path= "/ToAdPayment", method=RequestMethod.POST)
	public ModelAndView ToAdPayment() {
		
		return new ModelAndView("redirect:/TestForAdPayment.html");
	}
	
	@RequestMapping(path="/ToTexiPayment", method=RequestMethod.POST)
	public ModelAndView ToTexiPayment() {
		
		return new ModelAndView("redirect:/TestForPayment.html");
	}
	
	@RequestMapping(path="/TexiPayed", method=RequestMethod.GET)
	public ModelAndView TexiPayed(HttpServletRequest req) {
		
		HttpSession session = req.getSession();
		session.setAttribute("TexiPay", "Success");
		
		return new ModelAndView("redirect:/TestForPayment.html");
	}
}
