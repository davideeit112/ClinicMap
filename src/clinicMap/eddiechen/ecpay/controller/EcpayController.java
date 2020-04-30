package clinicMap.eddiechen.ecpay.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import clinicMap.eddiechen.ecpay.model.EcpayService;

@Controller
public class EcpayController {


	@RequestMapping(path = "/AdPayment", method = RequestMethod.POST)
	public String AdPayment(HttpServletRequest request, HttpServletResponse res) throws IOException {
		EcpayService.initial();
//		Cookie[] cookies = request.getCookies();
//		for(Cookie c: cookies) {
//		}
//		String clinicID = cookie[0].getValue();
		UUID uid = UUID.randomUUID();
		String id = uid.toString().replaceAll("-", "").substring(0, 20);
//		request.setCharacterEncoding("UTF-8");
//		request.setAttribute("formData", EcpayService.genAdPayment(id));
		PrintWriter out = res.getWriter();
		out.print(EcpayService.genAdPayment(id));
		System.out.println();
		
		return "Test";
	}
	
	
	@RequestMapping(path="/changeStatusAfterPay", method=RequestMethod.GET)
	public String changeStatusAfterPay(@RequestParam("id") String id, HttpServletRequest req, HttpServletResponse res) throws IOException {

		EcpayService.changeStatus(1001);
		
		return "Test";
	}
	
	@RequestMapping(path = "/TexiPayment", method = RequestMethod.POST)
	public String TexiPayment(@RequestParam("pricetotal") String pricetotal, HttpServletRequest request, HttpServletResponse res) throws IOException {
		EcpayService.initial();
//		Cookie[] cookies = request.getCookies();
//		for(Cookie c: cookies) {
//		}
//		String clinicID = cookie[0].getValue();
		UUID uid = UUID.randomUUID();
		String id = uid.toString().replaceAll("-", "").substring(0, 20);
//		request.setCharacterEncoding("UTF-8");
//		request.setAttribute("formData", EcpayService.genTexiPayment(id));
		PrintWriter out = res.getWriter();
		out.print(EcpayService.genTexiPayment(id, pricetotal));
		System.out.println();
		
		return "TestTing";
	}
}
