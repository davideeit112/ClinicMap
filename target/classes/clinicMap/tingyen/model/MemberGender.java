package clinicMap.tingyen.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import org.springframework.stereotype.Component;

@Component
@Entity
@Table(name = "MemberGender")
public class MemberGender {
	@Id
	@Column(name = "genderID")
	private String genderID;
	@Column(name = "memberGender")
	private String memberGender;

	public String getGenderID() {
		return genderID;
	}

	public void setGenderID(String genderID) {
		this.genderID = genderID;
	}

	public String getMemberGender() {
		return memberGender;
	}

	public void setMemberGender(String memberGender) {
		this.memberGender = memberGender;
	}

}
