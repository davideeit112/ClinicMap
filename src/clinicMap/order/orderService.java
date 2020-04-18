package clinicMap.order;

import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class orderService {
	@Autowired
 private savedata save;
	public orderService (savedata save) {
		this.save=save;
	}
	public clinicBean insert() throws IOException {
	return save.savedate();
	}
	public  List<clinicBean> query() {
		return save.query();
	}
	
	public int order(String clinicid, String memberid,String description) {
		return save.order( clinicid,memberid,description);
		
	}
	public int querynumber(String clinicid, String memberid) {
		return save.querynumber(clinicid, memberid);
	}
	public int peoplewait(String clinicid) {
		return save.peoplewait(clinicid);
	}
	public int insertlnglat(String []list) {
		return save.insertlnglat(list);	
	}
	public int deleteorder(String AppointmentID) {
		return	save.deleteorder(AppointmentID);
	}
	public memberBean memberquery(int memberid) {
		return save.memberquery(memberid);
	}
	public List<orderbean> orderquery(int memberid){
		return save.orderquery(memberid);
	}
	public clinicBean clinicquery(int clinicid) {
		return save.clinicquery(clinicid);
	}
	public memberBean insertmember(int memberid, String memberemail, String memberPwd, int memberHeight,
			int memberWeight, String memberAddress, String memberPhone) {
		return save.insertmember(memberid,memberemail,memberPwd,memberHeight,memberWeight,memberAddress,memberPhone);		
	}
	public memberBean photoupload(int memberId, String savepath) throws IOException {
		return save.photoupload(memberId,savepath);
	}
}


