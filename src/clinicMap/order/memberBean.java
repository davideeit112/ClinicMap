package clinicMap.order;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.springframework.stereotype.Component;
@Component
@Entity
@Table(name="member")
public class memberBean {
	@Id
	@Column(name="MemberID")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	public int getMemberID() {
		return memberID;
	}

	public void setMemberID(int memberID) {
		this.memberID = memberID;
	}
	@Column(name="MemberAccount")
	public String getMemberAccount() {
		return memberAccount;
	}

	public void setMemberAccount(String memberAccount) {
		this.memberAccount = memberAccount;
	}
	@Column(name="memberName")
	public String getmemberName() {
		return memberName;
	}

	public void setmemberName(String memberName) {
		this.memberName = memberName;
	}
	@Column(name="memberPwd")
	public String getMemberPwd() {
		return memberPwd;
	}

	public void setMemberPwd(String memberPwd) {
		this.memberPwd = memberPwd;
	}
	@Column(name="memberBirth")
	public Date getMemberBirth() {
		return memberBirth;
	}

	public void setMemberBirth(Date memberBirth) {
		this.memberBirth = memberBirth;
	}
	@Column(name="memberAddress")
	public String getMemberAddress() {
		return memberAddress;
	}

	public void setMemberAddress(String memberAddress) {
		this.memberAddress = memberAddress;
	}
	@Column(name="memberHeight")
	public int getMemberHeight() {
		return memberHeight;
	}

	public void setMemberHeight(int memberHeight) {
		this.memberHeight = memberHeight;
	}
	@Column(name="memberWeight")
	public int getMemberWeight() {
		return memberWeight;
	}

	public void setMemberWeight(int memberWeight) {
		this.memberWeight = memberWeight;
	}
	@Column(name="memberPhone")
	public String getMemberPhone() {
		return memberPhone;
	}

	public void setMemberPhone(String memberPhone) {
		this.memberPhone = memberPhone;
	}
	@Column(name="memberIdNumber")
	public String getMemberIdNumber() {
		return memberIdNumber;
	}

	public void setMemberIdNumber(String memberIdNumber) {
		this.memberIdNumber = memberIdNumber;
	}
	@Column(name="memberGender")
	public String getMemberGender() {
		return memberGender;
	}

	public void setMemberGender(String memberGender) {
		this.memberGender = memberGender;
	}
	@Column(name="memberStatus")
	public String getMemberStatus() {
		return memberStatus;
	}

	public void setMemberStatus(String memberStatus) {
		this.memberStatus = memberStatus;
	}
	@Column(name = "memberEmail")
	public String getMemberemail() {
		return memberemail;
	}

	public void setMemberemail(String memberemail) {
		this.memberemail = memberemail;
	}

	private int memberID;
	private String memberAccount;
	private String memberName;
	private String memberPwd;
	private Date memberBirth;
	private String memberAddress;
	private int memberHeight;

	private int memberWeight;

	private String memberPhone;
	private String memberemail;
	private String memberIdNumber;

	private String memberGender;

	private String memberStatus;

}
