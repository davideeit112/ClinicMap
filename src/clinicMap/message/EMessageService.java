package clinicMap.message;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import clinicMap.order.clinicBean;

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
	
	public List<memberBeans> querymember() {
		return emsgdao.querymember();
	}
	
	public List<clinicBean> queryclinic() {
		return emsgdao.queryclinic();
	}
	
	public List<EMessage> queryitem() {
		return emsgdao.queryitem();
	}
	
	public List<ResultBean> total() {
		return emsgdao.total();
	}
	
	public List<EMessage> querymsg() {
		return emsgdao.querymsg();
	}
	
	public List<ResultBean> top3blog() {
		return emsgdao.top3blog();
	}
}
