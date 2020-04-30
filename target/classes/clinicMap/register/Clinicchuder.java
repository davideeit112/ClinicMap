package clinicMap.register;

import java.sql.Blob;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.springframework.stereotype.Component;

@Entity
@Component
@Table(name = "clinic")
public class Clinicchuder {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "clinicID")
	private int clinicID;

	@Column(name = "clinicAccount")
	private String clinicAccount;

	@Column(name = "clinicPwd")
	private String clinicPwd;

	@Column(name = "clinicEmail")
	private String clinicEmail;

	@Column(name = "clinicName")
	private String clinicName;

	@Column(name = "clinicAddress")
	private String clinicAddress;

	@Column(name = "clinicClass")
	private String clinicClass;

	@Column(name = "clinicType")
	private String clinicType;

	@Column(name = "clinicphone")
	private String clinicphone;

	@Column(name = "clinicPhoto")
	private Blob clinicPhoto;

	@Column(name = "clinicStatus")
	private String clinicStatus;

	@Column(name = "clinicTime")
	private String clinicTime;

	@Column(name = "ClinicDescription")
	private String clinicDescription;

	@Column(name = "cliniclat")
	private String cliniclat;

	@Column(name = "cliniclng")
	private String cliniclag;
	// =========================================

	public int getClinicID() {
		return clinicID;
	}

	public void setClinicID(int clinicID) {
		this.clinicID = clinicID;
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

	public String getClinicEmail() {
		return clinicEmail;
	}

	public void setClinicEmail(String clinicEmail) {
		this.clinicEmail = clinicEmail;
	}

	public String getClinicName() {
		return clinicName;
	}

	public void setClinicName(String clinicName) {
		this.clinicName = clinicName;
	}

	public String getClinicAddress() {
		return clinicAddress;
	}

	public void setClinicAddress(String clinicAddress) {
		this.clinicAddress = clinicAddress;
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

	public String getClinicphone() {
		return clinicphone;
	}

	public void setClinicphone(String clinicphone) {
		this.clinicphone = clinicphone;
	}

	public Blob getClinicPhoto() {
		return clinicPhoto;
	}

	public void setClinicPhoto(Blob clinicPhoto) {
		this.clinicPhoto = clinicPhoto;
	}

	public String getClinicStatus() {
		return clinicStatus;
	}

	public void setClinicStatus(String clinicStatus) {
		this.clinicStatus = clinicStatus;
	}

	public String getClinicTime() {
		return clinicTime;
	}

	public void setClinicTime(String clinicTime) {
		this.clinicTime = clinicTime;
	}

	public String getClinicDescription() {
		return clinicDescription;
	}

	public void setClinicDescription(String clinicDescription) {
		this.clinicDescription = clinicDescription;
	}

	public String getCliniclat() {
		return cliniclat;
	}

	public void setCliniclat(String cliniclat) {
		this.cliniclat = cliniclat;
	}

	public String getCliniclag() {
		return cliniclag;
	}

	public void setCliniclag(String cliniclag) {
		this.cliniclag = cliniclag;
	}

}
