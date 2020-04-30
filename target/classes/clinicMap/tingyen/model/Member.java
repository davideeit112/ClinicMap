package clinicMap.tingyen.model;

import java.sql.Blob;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import org.springframework.stereotype.Component;

@Component
@Entity
@Table(name = "Member")
public class Member {
	@Id
	@Column(name = "memberID")
	private int memberID;
	@Column(name = "memberPhoto")
	private Blob memberPhoto;
	@Column(name = "memberAccount")
	private String memberAccount;
	@Column(name = "memberName")
	private String memberName;
	@Column(name = "memberPwd")
	private String memberPwd;
	@Column(name = "memberIdNumber")
	private String memberIdNumber;
	@Column(name = "memberBirth")
	private Date memberBirth;
	@Column(name = "memberAddress")
	private String memberAddress;
	@Column(name = "memberHeight")
	private Integer memberHeight;
	@Column(name = "memberWeight")
	private Integer memberWeight;
	@Column(name = "memberPhone")
	private String memberPhone;
	@Column(name = "memberEmail")
	private String memberEmail;
	@Column(name = "memberGender")
	private String memberGender;
	@Column(name = "memberStatus")
	private String memberStatus;

	public int getMemberID() {
		return memberID;
	}

	public void setMemberID(int memberID) {
		this.memberID = memberID;
	}

	public Blob getMemberPhoto() {
		return memberPhoto;
	}

	public void setMemberPhoto(Blob memberPhoto) {
		this.memberPhoto = memberPhoto;
	}

	public String getMemberAccount() {
		return memberAccount;
	}

	public void setMemberAccount(String memberAccount) {
		this.memberAccount = memberAccount;
	}

	public String getMemberName() {
		return memberName;
	}

	public void setMemberName(String memberName) {
		this.memberName = memberName;
	}

	public String getMemberPwd() {
		return memberPwd;
	}

	public void setMemberPwd(String memberPwd) {
		this.memberPwd = memberPwd;
	}

	public String getMemberIdNumber() {
		return memberIdNumber;
	}

	public void setMemberIdNumber(String memberIdNumber) {
		this.memberIdNumber = memberIdNumber;
	}

	public Date getMemberBirth() {
		return memberBirth;
	}

	public void setMemberBirth(Date memberBirth) {
		this.memberBirth = memberBirth;
	}

	public String getMemberAddress() {
		return memberAddress;
	}

	public void setMemberAddress(String memberAddress) {
		this.memberAddress = memberAddress;
	}

	public int getMemberHeight() {
		return memberHeight;
	}

	public void setMemberHeight(Integer memberHeight) {
		this.memberHeight = memberHeight;
	}

	public int getMemberWeight() {
		return memberWeight;
	}

	public void setMemberWeight(Integer memberWeight) {
		this.memberWeight = memberWeight;
	}

	public String getMemberPhone() {
		return memberPhone;
	}

	public void setMemberPhone(String memberPhone) {
		this.memberPhone = memberPhone;
	}

	public String getMemberEmail() {
		return memberEmail;
	}

	public void setMemberEmail(String memberEmail) {
		this.memberEmail = memberEmail;
	}

	public String getMemberGender() {
		return memberGender;
	}

	public void setMemberGender(String memberGender) {
		this.memberGender = memberGender;
	}

	public String getMemberStatus() {
		return memberStatus;
	}

	public void setMemberStatus(String memberStatus) {
		this.memberStatus = memberStatus;
	}

}
