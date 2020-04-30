package clinicMap.message;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
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

	@RequestMapping(path = "/allboard.do", method = RequestMethod.GET)
	public String allboard() {
		return "AllBoard";
	}

	@RequestMapping(path = "/allclinic.do", method = {RequestMethod.GET, RequestMethod.POST})
	public void queryallclinic(HttpServletResponse res) throws IOException {
		List<clinicBean> list = emsgservice.queryclinic();
		String hint = "";
		for (clinicBean cbean : list) {
			hint += (hint.equals("")) ? cbean.getClinicName() : ", " + cbean.getClinicName();
		}
		res.setContentType("text/html;charset=UTF-8");
		PrintWriter out = res.getWriter();
		out.print(hint);
	}

	@RequestMapping(path = "/start.do", method = RequestMethod.GET)
	public String start() {
		return "memberedit";
	}
	
	@RequestMapping(path = "/retotal.do", method = RequestMethod.POST)
	public void totals(HttpServletResponse res) throws IOException {
		List<ResultBean> list = emsgservice.total();
		List<clinicBean> list2 = emsgservice.queryclinic();
		JSONArray jarray = new JSONArray();
		for (ResultBean rbean : list) {
			for (clinicBean cbean : list2) {
				if (rbean.getClinicID() == cbean.getClinicId()) {
					JSONObject obj = new JSONObject(rbean);
					obj.put("clinicPhone", cbean.getClinicphone());
					obj.put("clinicName", cbean.getClinicName());
					obj.put("clinicPhoto", cbean.getClinicPhoto());
					jarray.put(obj);
				}
			}
		}
		res.setContentType("text/html;charset=UTF-8");
		PrintWriter out = res.getWriter();
		out.print(jarray);
	}

	
	@RequestMapping(path = "/search.do", method = RequestMethod.POST)
	public void search(HttpServletResponse res, HttpServletRequest request) throws IOException {
//		String cname = request.getParameter("cname");
		List<ResultBean> list = emsgservice.total();
		List<clinicBean> list2 = emsgservice.queryclinic();
		JSONArray jarray = new JSONArray();
		for (ResultBean rbean : list) {
			for (clinicBean cbean : list2) {
				if (rbean.getClinicID() == cbean.getClinicId()) {
					JSONObject obj = new JSONObject();
					obj.put("clinicId", cbean.getClinicId());
					obj.put("clinicName", cbean.getClinicName());
					obj.put("clinicPhoto", cbean.getClinicPhoto());
					obj.put("clinicPhone", cbean.getClinicphone());
					obj.put("ave", rbean.getAve());
					obj.put("total", rbean.getTotal());
					jarray.put(obj);
				} 
			}
		}
//		if(jarray.isEmpty()) {
//			JSONObject obj2 = new JSONObject();
//			obj2.put("clinicName", "�L���E�Ҹ��");
//			jarray.put(obj2);
//		}
		res.setContentType("text/html;charset=UTF-8");
		PrintWriter out = res.getWriter();
		out.print(jarray);
	}
	
	@RequestMapping(path = "/blog.{clinicID}", method = RequestMethod.GET)
	public String startall(HttpServletResponse res, HttpServletRequest req, @PathVariable int clinicID) throws IOException {
		PrintWriter out = res.getWriter();
//		System.out.println("clinicID..............." + clinicID + "........................");
		out.print(clinicID);
		HttpSession session = req.getSession();
		session.setAttribute("clinicID", clinicID);
//		System.out.println("clinicID2222..........." + clinicID + "........................");
		return "EachCblog";
	}
	
	@RequestMapping(path = "/msg.do", method = RequestMethod.POST)
	public void queryclinic1msg(HttpServletResponse res, HttpServletRequest req) throws IOException {
		HttpSession session = req.getSession();
		int clinicID = (int) session.getAttribute("clinicID");
//		System.out.println("clinicID3333..........." + clinicID + "........................");
		List<EMessage> list = emsgservice.queryitem();
		List<memberBean> list2 = emsgservice.querymember();
		List<clinicBean> list3 = emsgservice.queryclinic();
		JSONArray jarray = new JSONArray();
		for (EMessage ebean  : list) {
			for (memberBean mbean : list2) {
				for (clinicBean cbean : list3) {
					if (ebean.getClinicID() == clinicID) {
					JSONObject obj1 = new JSONObject();
					if (ebean.getMemberID() == mbean.getMemberID() && ebean.getClinicID() == cbean.getClinicId()) {
						obj1.put("clinicId", cbean.getClinicId());
						obj1.put("ClinicDescription", cbean.getClinicDescription());
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
					}
//				System.out.print(cbean.getClinicId());
				}
			}
		}
		res.setContentType("text/html;charset=UTF-8");
		System.out.print("-----------ok-------------------");
		PrintWriter out = res.getWriter();
		out.print(jarray);
	}
	
//	@RequestMapping(path = "/blog1.do", method = RequestMethod.GET)
//	public String start1() {
//		return "Cblog";
//	}
//
//	@RequestMapping(path = "/msg1.do", method = RequestMethod.POST)
//	public void queryallmsg(HttpServletResponse res) throws IOException {
//		List<EMessage> list = emsgservice.querymsg1();
//		List<memberBean> list2 = emsgservice.querymember();
//		List<clinicBean> list3 = emsgservice.queryclinic();
//		JSONArray jarray = new JSONArray();
//		for (EMessage ebean  : list) {
//			for (memberBean mbean : list2) {
//				for (clinicBean cbean : list3) {
//					JSONObject obj1 = new JSONObject();
//					if (ebean.getMemberID() == mbean.getMemberID() && ebean.getClinicID() == cbean.getClinicId()) {
//						obj1.put("clinicPhoto", cbean.getClinicPhoto());
//						obj1.put("clinicName", cbean.getClinicName());
//						obj1.put("clinicPhone", cbean.getClinicphone());
//						obj1.put("messageID", ebean.getMessageID());
//						obj1.put("MemberID", ebean.getMemberID());
//						obj1.put("Evaluation", ebean.getEvaluation());
//						obj1.put("Message", ebean.getMessage());
//						obj1.put("evaluationTime", ebean.getEvaluationTime());
//						obj1.put("messageName", mbean.getMemberAccount());
//						jarray.put(obj1);
//					}
////				System.out.println(ebean.getMessage().length());
//				}
//			}
//		}
//		res.setContentType("text/html;charset=UTF-8");
//		PrintWriter out = res.getWriter();
//		out.print(jarray);
//	}
//
//	@RequestMapping(path = "/blog2.do", method = RequestMethod.GET)
//	public String start2() {
//		return "Cblog2";
//	}
//
//	@RequestMapping(path = "/msg2.do", method = {RequestMethod.POST,RequestMethod.GET})
//	public void queryclinic2msg(HttpServletResponse res) throws IOException {
//		List<EMessage> list = emsgservice.querymsg2();
//		List<memberBean> list2 = emsgservice.querymember();
//		List<clinicBean> list3 = emsgservice.queryclinic();
//		JSONArray jarray = new JSONArray();
//		for (EMessage ebean  : list) {
//			for (memberBean mbean : list2) {
//				for (clinicBean cbean : list3) {
//					JSONObject obj1 = new JSONObject();
//					if (ebean.getMemberID() == mbean.getMemberID() && ebean.getClinicID() == cbean.getClinicId()) {
//						obj1.put("clinicPhoto", cbean.getClinicPhoto());
//						obj1.put("clinicName", cbean.getClinicName());
//						obj1.put("clinicPhone", cbean.getClinicphone());
//						obj1.put("messageID", ebean.getMessageID());
//						obj1.put("MemberID", ebean.getMemberID());
//						obj1.put("Evaluation", ebean.getEvaluation());
//						obj1.put("Message", ebean.getMessage());
//						obj1.put("evaluationTime", ebean.getEvaluationTime());
//						obj1.put("messageName", mbean.getMemberAccount());
//						jarray.put(obj1);
//					}
////				System.out.println(ebean.getMessage().length());
//				}
//			}
//		}
//		res.setContentType("text/html;charset=UTF-8");
//		PrintWriter out = res.getWriter();
//		out.print(jarray);
//	}
//
//	@RequestMapping(path = "/blog3.do", method = RequestMethod.GET)
//	public String start3() {
//		return "Cblog3";
//	}
//
//	@RequestMapping(path = "/msg3.do", method = RequestMethod.POST)
//	public void queryclinic3msg(HttpServletResponse res) throws IOException {
//		List<EMessage> list = emsgservice.querymsg3();
//		List<memberBean> list2 = emsgservice.querymember();
//		List<clinicBean> list3 = emsgservice.queryclinic();
//		JSONArray jarray = new JSONArray();
//		for (EMessage ebean : list) {
//			for (memberBean mbean : list2) {
//				for (clinicBean cbean : list3) {
//					JSONObject obj1 = new JSONObject();
//					if (ebean.getMemberID() == mbean.getMemberID() && ebean.getClinicID() == cbean.getClinicId()) {
//						obj1.put("clinicPhoto", cbean.getClinicPhoto());
//						obj1.put("clinicName", cbean.getClinicName());
//						obj1.put("clinicPhone", cbean.getClinicphone());
//						obj1.put("messageID", ebean.getMessageID());
//						obj1.put("MemberID", ebean.getMemberID());
//						obj1.put("Evaluation", ebean.getEvaluation());
//						obj1.put("Message", ebean.getMessage());
//						obj1.put("evaluationTime", ebean.getEvaluationTime());
//						obj1.put("messageName", mbean.getMemberAccount());
//						jarray.put(obj1);
//					}
////				System.out.println(ebean.getMessage());
//				}
//			}
//		}
//		res.setContentType("text/html;charset=UTF-8");
//		PrintWriter out = res.getWriter();
//		out.print(jarray);
//	}

	@RequestMapping(path = "/order.do", method = RequestMethod.GET)
	public String intoappointment() {
		return "Appointment";
	}

	@RequestMapping(path = "/message", method = RequestMethod.POST)
	public String processmessage(@RequestParam("clinicID") int clinicID,
			@RequestParam("appointmentID") String appointmentID, @RequestParam("Evaluation") float Evaluation,
			@RequestParam("Message") String Message, @RequestParam("memberID") int memberID,
			@RequestParam("evaluationTime") Date evaluationTime, Model model, RedirectAttributes redirectAttributes) {

		model.addAttribute("clinicID", clinicID);
		model.addAttribute("appointmentID", appointmentID);
		model.addAttribute("memberID", memberID);
		model.addAttribute("Evaluation", Evaluation);
		model.addAttribute("Message", Message);
		model.addAttribute("evaluationTime", evaluationTime);
		boolean check = emsgservice
				.inputmessage(new EMessage(clinicID, appointmentID, Evaluation, Message, memberID, evaluationTime));
		if (check == true) {
			System.out.println("input success...");
		}
		return "memberedit";
	}

	@RequestMapping(path = "/deletemsg.do", method = RequestMethod.POST)
	public String processdelmessage(@RequestParam("messageID") int messageID, Model model,
			RedirectAttributes redirectAttributes) {
		model.addAttribute("messageID", messageID);
		boolean checkdel = emsgservice.deletemessage(messageID);
		if (checkdel == true) {
			System.out.println("del success...");
			redirectAttributes.addAttribute("id", messageID);
			return "redirect:/start.do";
		}
		System.out.println("delfalse");
		return "redirect:/start.do";
	}

	@RequestMapping(path = "/replymsg.do", method = RequestMethod.GET)
	public void processreplymessage(@RequestParam("messageID") int messageID, @RequestParam("clinicID") int clinicID,
			@RequestParam("replyMessage") String replyMessage, Model model, RedirectAttributes redirectAttributes) {
		model.addAttribute("messageID", messageID);
		model.addAttribute("clinicID", clinicID);
		model.addAttribute("replyMessage", replyMessage);
		System.out.println("3");
		boolean check = emsgservice.inputreplymsg(new ReplyBean(clinicID, clinicID, replyMessage));
		System.out.println("4");
		if (check == true) {
			System.out.println("input success...");
//			redirectAttributes.addAttribute("id", clinicID);
//			return "redirect:/start.do";
		}
		System.out.println("empty");
//		return "redirect:/start.do";
	}

	@RequestMapping(path = "/msgtop3.do", method = RequestMethod.POST)
	public void querymsgtop3(HttpServletResponse res) throws IOException {
		List<EMessage> list = emsgservice.querymsg();
		List<memberBean> list2 = emsgservice.querymember();
		List<clinicBean> list3 = emsgservice.queryclinic();
		JSONArray jarray = new JSONArray();
		System.out.println("1.");
		for (EMessage ebean : list) {
			for (memberBean mbean : list2) {
				for (clinicBean cbean : list3) {
					JSONObject obj1 = new JSONObject();
					if (ebean.getMemberID() == mbean.getMemberID() && ebean.getClinicID() == cbean.getClinicId()) {
						obj1.put("clinicName", cbean.getClinicName());
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
}
