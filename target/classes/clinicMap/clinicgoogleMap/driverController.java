package clinicMap.clinicgoogleMap;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import clinicMap.message.ResultBean;

@Controller
public class driverController {
	private driverService dservice;
	
	@Autowired
	public driverController(driverService dservice) {
		this.dservice = dservice;
	}

	@RequestMapping(path = "/driverlogin", method = RequestMethod.GET)
	public String dlogin() {
		return "DriverLogin";
	}

	@RequestMapping(path = "/dlogin.do", method = RequestMethod.POST)
	public void processlogin(@RequestParam("driveraccount") String driveraccount, @RequestParam("driverpassword") String driverpassword,
			Model model, HttpServletResponse res,driver driver) throws IOException {
		List<driver> list = dservice.showdriver();
		model.addAttribute("driveraccount", driveraccount);
		model.addAttribute("driverpassword", driverpassword);
		boolean check = dservice.DLogin(new driver(driveraccount,driverpassword));
		res.setContentType("text/html;charset=UTF-8");
		PrintWriter out = res.getWriter();
		if (check == true) {
			
			for(driver dr:list) {
				if(driveraccount.equals(dr.getdriveraccount())) {
//					System.out.println("............................."+dr.getdrivername());
					Cookie dname = new Cookie("drivername", dr.getdrivername());
					dname.setMaxAge(-1); 
					dname.setPath("/clinicMap/driver");
					res.addCookie(dname);
					out.print(dr.getdrivername());
				}
			}
		} else {
//			System.out.println(": " + driveraccount + ": " + driverpassword);
			out.print(0);
		}
	}
}
