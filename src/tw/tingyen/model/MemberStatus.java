package tw.tingyen.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import org.springframework.stereotype.Component;

@Component
@Entity
@Table(name = "MemberStatus")
public class MemberStatus {
	@Id @Column(name="memberStatusID")
	private String memberStatusID;
	@Column(name="status")
	private String status;

	public String getMemberStatusID() {
		return memberStatusID;
	}

	public void setMemberStatusID(String memberStatusID) {
		this.memberStatusID = memberStatusID;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

}
