package clinicMap.message;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import clinicMap.order.clinicBean;
import clinicMap.order.memberBean;

@Service
public class EMessageService {

	private EMessageDao emsgdao;
	
	@Autowired
	public EMessageService(EMessageDao emsgdao) {
		this.emsgdao = emsgdao;
	}
	
	public boolean inputmessage(EMessage emessage) {
		return emsgdao.inputmessage(emessage);
	}

	
	public boolean deletemessage(int messageID) {
		return emsgdao.deletemessage(messageID);
	}
	
	public boolean inputreplymsg(ReplyBean rbean) {
		return emsgdao.inputreplymsg(rbean);
	}
	
	public List<memberBean> querymember() {
		return emsgdao.querymember();
	}
	
	public List<clinicBean> queryclinic() {
		return emsgdao.queryclinic();
	}
	
//	public List<EMessage> queryallclinic(int clinicID) {
//		return emsgdao.queryallclinic(clinicID);
//	}
	
	public List<EMessage> queryitem() {
		return emsgdao.queryitem();
	}
	
	public List<ResultBean> total() {
		return emsgdao.total();
	}
	
	public List<EMessage> querymsg() {
		return emsgdao.querymsg();
	}
	
//	public List<EMessage> querymsg1() {
//		return emsgdao.querymsg1();
//	}
//	
//	public List<EMessage> querymsg2() {
//		return emsgdao.querymsg2();
//	}
//	
//	public List<EMessage> querymsg3() {
//		return emsgdao.querymsg3();
//	}
	
	public List<ReplyBean> queryreply(){
		return emsgdao.queryreply();
	}
}
