package clinicMap.message;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Date;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import clinicMap.order.clinicBean;
import clinicMap.order.memberBean;

@Controller
public class EMessageController {
	private EMessageService emsgservice;

	@Autowired
	public EMessageController(EMessageService emsgservice) {
		this.emsgservice = emsgservice;
	}

	@RequestMapping(path = "/ClinicComment", method = RequestMethod.GET)
	public String start() {
		return "Board";
	}

	@RequestMapping(path = "/clinic.do", method = RequestMethod.POST)
	public void queryclinic(HttpServletResponse res) throws IOException {
		List<clinicBean> list = emsgservice.queryclinic();
		System.out.println("1");
		JSONArray jarray = new JSONArray();
		System.out.println("1.");
		for (clinicBean cbean : list) {
			JSONObject obj = new JSONObject();
			obj.put("clinicId", cbean.getClinicId());
			obj.put("clinicPhone", cbean.getClinicphone());
			obj.put("clinicName", cbean.getClinicName());
			obj.put("clinicPhoto", cbean.getClinicPhoto());
			jarray.put(obj);
			System.out.println(cbean.getClinicName() + "," + cbean.getClinicId());
		}
		res.setContentType("text/html;charset=UTF-8");
		PrintWriter out = res.getWriter();
		out.print(jarray);
	}

	@RequestMapping(path = "/blog.do", method = RequestMethod.GET)
	public String start1() {
		return "Cblog";
	}

	@RequestMapping(path = "/msg1.do", method = RequestMethod.POST)
	public void queryclinic1msg(HttpServletResponse res) throws IOException {
		List<EMessage> list = emsgservice.querymsg1();
		List<memberBean> list2 = emsgservice.querymember();
		List<clinicBean> list3 = emsgservice.queryclinic();
		JSONArray jarray = new JSONArray();
		System.out.println("1.");
		for (EMessage ebean : list) {
			for (memberBean mbean : list2) {
				for (clinicBean cbean : list3) {
				JSONObject obj1 = new JSONObject();
				if (ebean.getMemberID() == mbean.getMemberID() && ebean.getClinicID()==cbean.getClinicId()) {
					obj1.put("clinicPhoto", cbean.getClinicPhoto());
					obj1.put("clinicName", cbean.getClinicName());
					obj1.put("clinicPhone", cbean.getClinicphone());
					obj1.put("messageID", ebean.getMessageID());
					obj1.put("MemberID", ebean.getMemberID());
					obj1.put("Evaluation", ebean.getEvaluation());
					obj1.put("Message", ebean.getMessage());
					obj1.put("evaluationTime", ebean.getEvaluationTime());
					obj1.put("messageName", mbean.getMemberAccount());
					jarray.put(obj1);
				}
//				System.out.println(ebean.getMessage().length());
				}
			}
		}
		res.setContentType("text/html;charset=UTF-8");
		System.out.println("1..");
		PrintWriter out = res.getWriter();
		out.print(jarray);
	}
	
	@RequestMapping(path = "/blog2.do", method = RequestMethod.GET)
	public String start2() {
		return "Cblog2";
	}

	@RequestMapping(path = "/msg2.do", method = RequestMethod.POST)
	public void queryclinic2msg(HttpServletResponse res) throws IOException {
		List<EMessage> list = emsgservice.querymsg2();
		List<memberBean> list2 = emsgservice.querymember();
		List<clinicBean> list3 = emsgservice.queryclinic();
		JSONArray jarray = new JSONArray();
		System.out.println("1.");
		for (EMessage ebean : list) {
			for (memberBean mbean : list2) {
				for (clinicBean cbean : list3) {
				JSONObject obj1 = new JSONObject();
				if (ebean.getMemberID() == mbean.getMemberID() && ebean.getClinicID()==cbean.getClinicId()) {
					obj1.put("clinicPhoto", cbean.getClinicPhoto());
					obj1.put("clinicName", cbean.getClinicName());
					obj1.put("clinicPhone", cbean.getClinicphone());
					obj1.put("messageID", ebean.getMessageID());
					obj1.put("MemberID", ebean.getMemberID());
					obj1.put("Evaluation", ebean.getEvaluation());
					obj1.put("Message", ebean.getMessage());
					obj1.put("evaluationTime", ebean.getEvaluationTime());
					obj1.put("messageName", mbean.getMemberAccount());
					jarray.put(obj1);
				}
//				System.out.println(ebean.getMessage().length());
				}
			}
		}
		res.setContentType("text/html;charset=UTF-8");
		System.out.println("1..");
		PrintWriter out = res.getWriter();
		out.print(jarray);
	}
	
	@RequestMapping(path = "/blog3.do", method = RequestMethod.GET)
	public String start3() {
		return "Cblog3";
	}
	
	@RequestMapping(path = "/msg3.do", method = RequestMethod.POST)
	public void queryclinic3msg(HttpServletResponse res) throws IOException {
		List<EMessage> list = emsgservice.querymsg3();
		List<memberBean> list2 = emsgservice.querymember();
		List<clinicBean> list3 = emsgservice.queryclinic();
		JSONArray jarray = new JSONArray();
		System.out.println("1.");
		for (EMessage ebean : list) {
			for (memberBean mbean : list2) {
				for (clinicBean cbean : list3) {
				JSONObject obj1 = new JSONObject();
				if (ebean.getMemberID() == mbean.getMemberID() && ebean.getClinicID()==cbean.getClinicId()) {
					obj1.put("clinicPhoto", cbean.getClinicPhoto());
					obj1.put("clinicName", cbean.getClinicName());
					obj1.put("clinicPhone", cbean.getClinicphone());
					obj1.put("messageID", ebean.getMessageID());
					obj1.put("MemberID", ebean.getMemberID());
					obj1.put("Evaluation", ebean.getEvaluation());
					obj1.put("Message", ebean.getMessage());
					obj1.put("evaluationTime", ebean.getEvaluationTime());
					obj1.put("messageName", mbean.getMemberAccount());
					jarray.put(obj1);
				}
//				System.out.println(ebean.getMessage());
				}
			}
		}
		res.setContentType("text/html;charset=UTF-8");
		System.out.println("1..");
		PrintWriter out = res.getWriter();
		out.print(jarray);
	}

	@RequestMapping(path = "/order.do", method = RequestMethod.GET)
	public String intoappointment() {
		return "Appointment";
	}

//	@RequestMapping(path = "/inputmsg.do", method = RequestMethod.POST)
//	public String processmsg() {
//		return "Message";
//	}

	@RequestMapping(path = "/message.do", method = RequestMethod.POST)
	public void processmessage(@RequestParam("clinicID") int clinicID,
			@RequestParam("appointmentID") String appointmentID, @RequestParam("Evaluation") float Evaluation,
			@RequestParam("Message") String Message, @RequestParam("memberID") int memberID,
			@RequestParam("evaluationTime") Date evaluationTime, Model model, RedirectAttributes redirectAttributes) {

		model.addAttribute("clinicID", clinicID);
		model.addAttribute("appointmentID", appointmentID);
		model.addAttribute("memberID", memberID);
		model.addAttribute("Evaluation", Evaluation);
		model.addAttribute("Message", Message);
		model.addAttribute("evaluationTime", evaluationTime);
		System.out.println("3");
		boolean check = emsgservice
				.inputmessage(new EMessage(clinicID, appointmentID, Evaluation, Message, memberID, evaluationTime));
		System.out.println("4");
		if (check == true) {
			System.out.println("input success...");
			redirectAttributes.addAttribute("id", clinicID);
			
		}
		System.out.println("empty");
}

	@RequestMapping(path = "/deletemsg.do", method = RequestMethod.POST)
	public String processdelmessage(@RequestParam("messageID") int messageID, Model model,RedirectAttributes redirectAttributes) {
		model.addAttribute("messageID", messageID);
		boolean checkdel = emsgservice.deletemessage(messageID);
		System.out.println("5");
		if (checkdel == true) {
			System.out.println("del success...");
			redirectAttributes.addAttribute("id", messageID);
			return "redirect:/start.do";
		}
		System.out.println("delfalse");
		return "redirect:/start.do";
	}

}
