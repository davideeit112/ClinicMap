package clinicMap.order;

import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import org.springframework.stereotype.Component;

@Component
@Entity
@Table(name = "Appointment")
public class orderbean {
	
	private String appointmentID;
	private int memberID;
	private int clinicID;
	private String appointmentType;
	private String appointmentSickDescription;
	private Timestamp appointmentTime;
	private int appointmentNumber;
	private String appointmentStatus;
	@Id @Column(name = "appointmentID")
	public String getAppointmentID() {
		return appointmentID;
	}
	
	public void setAppointmentID(String appointmentID) {
		this.appointmentID = appointmentID;
	}
	@Column(name = "memberID")
	public int getMemberID() {
		return memberID;
	}
	public void setMemberID(int memberID) {
		this.memberID = memberID;
	}
	@Column(name = "clinicID")
	public int getClinicID() {
		return clinicID;
	}
	public void setClinicID(int clinicID) {
		this.clinicID = clinicID;
	}
	@Column(name = "AppointmentType")
	public String getAppointmentType() {
		return appointmentType;
	}
	public void setAppointmentType(String appointmentType) {
		this.appointmentType = appointmentType;
	}
	@Column(name = "AppointmentSickDescription")
	public String getAppointmentSickDescription() {
		return appointmentSickDescription;
	}
	public void setAppointmentSickDescription(String appointmentSickDescription) {
		this.appointmentSickDescription = appointmentSickDescription;
	}
	@Column(name = "APPOINTMENTTIME")
	public Timestamp getAppointmentTime() {
		return appointmentTime;
	}
	public void setAppointmentTime(Timestamp appointmentTime) {
		this.appointmentTime = appointmentTime;
	}
	@Column(name = "AppointmentNumber")
	public int getAppointmentNumber() {
		return appointmentNumber;
	}
	public void setAppointmentNumber(int appointmentNumber) {
		this.appointmentNumber = appointmentNumber;
	}
	@Column(name = "AppointmentStatus")
	public String getAppointmentStatus() {
		return appointmentStatus;
	}
	public void setAppointmentStatus(String appointmentStatus) {
		this.appointmentStatus = appointmentStatus;
	}

	}
