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
	private String clinicID;
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
	private Blob clinicPhoto;
	@Column(name = "clinicPhone")
	private String clinicPhone;
	@Column(name = "clinicClass")
	private int clinicClass;
	@Column(name = "clinicType")
	private int clinicType;
	@Column(name = "clinicTime")
	private int clinicTime;
	@Column(name = "clinicStatus")
	private String clinicStatus;

	public String getClinicID() {
		return clinicID;
	}

	public void setClinicID(String clinicID) {
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

	public Blob getClinicPhoto() {
		return clinicPhoto;
	}

	public void setClinicPhoto(Blob clinicPhoto) {
		this.clinicPhoto = clinicPhoto;
	}

	public String getClinicPhone() {
		return clinicPhone;
	}

	public void setClinicPhone(String clinicPhone) {
		this.clinicPhone = clinicPhone;
	}

	public int getClinicClass() {
		return clinicClass;
	}

	public void setClinicClass(int clinicClass) {
		this.clinicClass = clinicClass;
	}

	public int getClinicType() {
		return clinicType;
	}

	public void setClinicType(int clinicType) {
		this.clinicType = clinicType;
	}

	public int getClinicTime() {
		return clinicTime;
	}

	public void setClinicTime(int clinicTime) {
		this.clinicTime = clinicTime;
	}

	public String getClinicStatus() {
		return clinicStatus;
	}

	public void setClinicStatus(String clinicStatus) {
		this.clinicStatus = clinicStatus;
	}

}
