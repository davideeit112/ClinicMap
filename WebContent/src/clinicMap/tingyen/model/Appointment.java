package clinicMap.tingyen.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import org.springframework.stereotype.Component;

@Component
@Entity
@Table(name = "Appointment")
public class Appointment {
	@Id
	@Column(name = "appointmentID")
	private String appointmentID;
	@Column(name = "memberID")
	private int memberID;
	@Column(name = "clinicID")
	private String clinicID;
	@Column(name = "appointmentType")
	private String appointmentType;
	@Column(name = "sickDescription")
	private String sickDescription;
	@Column(name = "appointmentTime")
	private Date appointmentTime;
	@Column(name = "appointmentNumber")
	private int appointmentNumber;
	@Column(name = "appointmentStatus")
	private String appointmentStatus;

	public String getAppointmentID() {
		return appointmentID;
	}

	public void setAppointmentID(String appointmentID) {
		this.appointmentID = appointmentID;
	}

	public int getMemberID() {
		return memberID;
	}

	public void setMemberID(int memberID) {
		this.memberID = memberID;
	}

	public String getClinicID() {
		return clinicID;
	}

	public void setClinicID(String clinicID) {
		this.clinicID = clinicID;
	}

	public String getAppointmentType() {
		return appointmentType;
	}

	public void setAppointmentType(String appointmentType) {
		this.appointmentType = appointmentType;
	}

	public String getSickDescription() {
		return sickDescription;
	}

	public void setSickDescription(String sickDescription) {
		this.sickDescription = sickDescription;
	}

	public Date getAppointmentTime() {
		return appointmentTime;
	}

	public void setAppointmentTime(Date appointmentTime) {
		this.appointmentTime = appointmentTime;
	}

	public int getAppointmentNumber() {
		return appointmentNumber;
	}

	public void setAppointmentNumber(int appointmentNumber) {
		this.appointmentNumber = appointmentNumber;
	}

	public String getAppointmentStatus() {
		return appointmentStatus;
	}

	public void setAppointmentStatus(String appointmentStatus) {
		this.appointmentStatus = appointmentStatus;
	}

}
