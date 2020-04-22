package clinicMap.register;

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
@Table(name = "member")
public class Member {

	@Id
	@Column(name = "memberID")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int memberID;

	@Column(name = "memberAccount")
	private String memberAccount;

	@Column(name = "memberName")
	private String memberName;

	@Column(name = "memberPwd")
	private String memberPwd;

	@Column(name = "memberBirth")
	private Date memberBirth;

	@Column(name = "memberAddress")
	private String memberAddress;

	@Column(name = "memberHeight")
	private int memberHeight;

	@Column(name = "memberWeight")
	private int memberWeight;

	@Column(name = "memberEmail")
	private String memberEmail;

	@Column(name = "memberPhone")
	private String memberPhone;

	@Column(name = "memberIdNumber")
	private String memberIdNumber;

	// -------------------------------------------
	@Column(name = "memberVerifiedCode")
	private String memberVerifiedCode;

	@Column(name = "memberRegisterDeadline")
	private long memberRegisterDeadline;

	// ------------------------------------------
	@Column(name = "memberStatus")
	private String memberStatus;

	@Column(name = "memberGender")
	private String memberGender;

	@Column(name = "memberPhoto")
	private byte[] memberPhoto;
	// =================================================

	public int getMemberID() {
		return memberID;
	}

	public void setMemberID(int memberID) {
		this.memberID = memberID;
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

	public void setMemberHeight(int memberHeight) {
		this.memberHeight = memberHeight;
	}

	public int getMemberWeight() {
		return memberWeight;
	}

	public void setMemberWeight(int memberWeight) {
		this.memberWeight = memberWeight;
	}

	public String getMemberEmail() {
		return memberEmail;
	}

	public void setMemberEmail(String memberEmail) {
		this.memberEmail = memberEmail;
	}

	public String getMemberPhone() {
		return memberPhone;
	}

	public void setMemberPhone(String memberPhone) {
		this.memberPhone = memberPhone;
	}

	public String getMemberIdNumber() {
		return memberIdNumber;
	}

	public void setMemberIdNumber(String memberIdNumber) {
		this.memberIdNumber = memberIdNumber;
	}

	public String getMemberVerifiedCode() {
		return memberVerifiedCode;
	}

	public void setMemberVerifiedCode(String memberVerifiedCode) {
		this.memberVerifiedCode = memberVerifiedCode;
	}

	public long getMemberRegisterDeadline() {
		return memberRegisterDeadline;
	}

	public void setMemberRegisterDeadline(long memberRegisterDeadline) {
		this.memberRegisterDeadline = memberRegisterDeadline;
	}

	public String getMemberStatus() {
		return memberStatus;
	}

	public void setMemberStatus(String memberStatus) {
		this.memberStatus = memberStatus;
	}

	public String getMemberGender() {
		return memberGender;
	}

	public void setMemberGender(String memberGender) {
		this.memberGender = memberGender;
	}

	public byte[] getMemberPhoto() {
		return memberPhoto;
	}

	public void setMemberPhoto(byte[] memberPhoto) {
		this.memberPhoto = memberPhoto;
	}

}
