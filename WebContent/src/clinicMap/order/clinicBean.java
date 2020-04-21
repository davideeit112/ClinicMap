package clinicMap.order;

import java.sql.Blob;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.springframework.stereotype.Component;
@Component
@Entity
@Table(name = "clinic")
public class clinicBean {
	private int clinicId;
	private String clinicAccount;
	private String clinicName;
	private String clinicPwd;
	private String clinicAddress;
	private String clinicClass;
	private String clinicType;
	private String clinicphone;
	private Blob clinicPhoto;
	private String clinicStatus;
	private String clinicTime;
	private String clinicDescription;
	private String cliniclat;
	private String cliniclag;
	private String clinicemail;
	@Column(name="CLINICTIME")
	public String getClinicTime() {
		return clinicTime;
	}
	public void setClinicTime(String clinicTime) {
		this.clinicTime = clinicTime;
	}
	@Column(name="cliniclat")
	public String getCliniclat() {
		return cliniclat;
	}
	public void setCliniclat(String cliniclat) {
		this.cliniclat = cliniclat;
	}
	@Column(name="cliniclng")
	public String getCliniclan() {
		return cliniclag;
	}
	public void setCliniclan(String cliniclag) {
		this.cliniclag = cliniclag;
	}

	@Column(name="ClinicDescription")
	public String getClinicDescription() {
		return clinicDescription;
	}
	public void setClinicDescription(String clinicDescription) {
		this.clinicDescription = clinicDescription;
	}
	@Id
	@Column(name = "clinicId")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	public int getClinicId() {
		return clinicId;
	}
	public void setClinicId(int clinicId) {
		this.clinicId = clinicId;
	}
	@Column(name = "clinicAccount")
	public String getClinicAccount() {
		return clinicAccount;
	}
	public void setClinicAccount(String clinicAccount) {
		this.clinicAccount = clinicAccount;
	}
	@Column(name = "ClinicName")
	public String getClinicName() {
		return clinicName;
	}
	public void setClinicName(String clinicName) {
		this.clinicName = clinicName;
	}
	@Column(name = "clinicPwd")
	public String getClinicPwd() {
		return clinicPwd;
	}
	public void setClinicPwd(String clinicPwd) {
		this.clinicPwd = clinicPwd;
	}
	@Column(name = "clinicAddress")
	public String getClinicAddress() {
		return clinicAddress;
	}
	public void setClinicAddress(String clinicAddress) {
		this.clinicAddress = clinicAddress;
	}
	@Column(name = "clinicClass")
	public String getClinicClass() {
		return clinicClass;
	}
	
	public void setClinicClass(String clinicClass) {
		this.clinicClass = clinicClass;
	}
	@Column(name = "clinicPhoto")
	public Blob getClinicPhoto() {
		return clinicPhoto;
	}
	public void setClinicPhoto(Blob clinicPhoto) {
		this.clinicPhoto = clinicPhoto;
	}
	@Column(name = "clinicStatus")
	public String getClinicStatus() {
		return clinicStatus;
	}
	public void setClinicStatus(String clinicStatus) {
		this.clinicStatus = clinicStatus;
	}
	@Column(name="clinicType")
	public String getClinicType() {
		return clinicType;
	}
	public void setClinicType(String clinicType) {
		this.clinicType = clinicType;
	}
	@Column(name = "clinicphone")
	public String getClinicphone() {
		return clinicphone;
	}
	public void setClinicphone(String clinicphone) {
		this.clinicphone = clinicphone;
	}
	@Column(name = "Clinicemail")
	public String getClinicemail() {
		return clinicemail;
	}
	public void setClinicemail(String clinicemail) {
		this.clinicemail = clinicemail;
	}
}
