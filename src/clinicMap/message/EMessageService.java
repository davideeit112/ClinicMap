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
	
	public List<memberBean> querymember() {
		return emsgdao.querymember();
	}
	
	public List<clinicBean> queryclinic() {
		return emsgdao.queryclinic();
	}
	
	public List<EMessage> querymsg1() {
		return emsgdao.querymsg1();
	}
	
	public List<EMessage> querymsg2() {
		return emsgdao.querymsg2();
	}
	
	public List<EMessage> querymsg3() {
		return emsgdao.querymsg3();
	}
	public int query3Clinic() {
		return emsgdao.query3Clinic();
	}
//	public List<ReplyBean> queryreply(){
//		return emsgdao.queryreply();
//	}
}
