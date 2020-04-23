package tw.tingyen.model;

import java.sql.Blob;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import org.springframework.stereotype.Component;

@Component
@Entity
@Table(name = "Clinic")
public class Clinic {
	@Id
	@Column(name = "clinicID")
	private int clinicID;
	@Column(name = "clinicName")
	private String clinicName;
	@Column(name = "clinicAccount")
	private String clinicAccount;
	@Column(name = "clinicPwd")
	private String clinicPwd;
	@Column(name = "clinicAddress")
	private String clinicAddress;
	@Column(name = "clinicDescription")
	private String clinicDescription;
	@Column(name = "clinicPhoto")
	private byte[] clinicPhoto;
	@Column(name = "clinicLicense")
	private byte[] clinicLicense;
	@Column(name = "clinicPhone")
	private String clinicPhone;
	@Column(name = "clinicEmail")
	private String clinicEmail;
	@Column(name = "clinicClass")
	private String clinicClass;
	@Column(name = "clinicType")
	private String clinicType;
	@Column(name = "clinicStatus")
	private String clinicStatus;

	public int getClinicID() {
		return clinicID;
	}

	public void setClinicID(int clinicID) {
		this.clinicID = clinicID;
	}

	public String getClinicName() {
		return clinicName;
	}

	public void setClinicName(String clinicName) {
		this.clinicName = clinicName;
	}

	public String getClinicAccount() {
		return clinicAccount;
	}

	public void setClinicAccount(String clinicAccount) {
		this.clinicAccount = clinicAccount;
	}

	public String getClinicPwd() {
		return clinicPwd;
	}

	public void setClinicPwd(String clinicPwd) {
		this.clinicPwd = clinicPwd;
	}

	public String getClinicAddress() {
		return clinicAddress;
	}

	public void setClinicAddress(String clinicAddress) {
		this.clinicAddress = clinicAddress;
	}

	public String getClinicDescription() {
		return clinicDescription;
	}

	public void setClinicDescription(String clinicDescription) {
		this.clinicDescription = clinicDescription;
	}

	public byte[] getClinicPhoto() {
		return clinicPhoto;
	}

	public void setClinicPhoto(byte[] clinicPhoto) {
		this.clinicPhoto = clinicPhoto;
	}

	public String getClinicPhone() {
		return clinicPhone;
	}

	public void setClinicPhone(String clinicPhone) {
		this.clinicPhone = clinicPhone;
	}

	public String getClinicClass() {
		return clinicClass;
	}

	public void setClinicClass(String clinicClass) {
		this.clinicClass = clinicClass;
	}

	public String getClinicType() {
		return clinicType;
	}

	public void setClinicType(String clinicType) {
		this.clinicType = clinicType;
	}

	public byte[] getClinicLicense() {
		return clinicLicense;
	}

	public void setClinicLicense(byte[] clinicLicense) {
		this.clinicLicense = clinicLicense;
	}

	public String getClinicEmail() {
		return clinicEmail;
	}

	public void setClinicEmail(String clinicEmail) {
		this.clinicEmail = clinicEmail;
	}

	public String getClinicStatus() {
		return clinicStatus;
	}

	public void setClinicStatus(String clinicStatus) {
		this.clinicStatus = clinicStatus;
	}

}
