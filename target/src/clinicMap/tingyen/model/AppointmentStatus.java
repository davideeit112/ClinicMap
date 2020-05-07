package clinicMap.tingyen.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import org.springframework.stereotype.Component;

@Component
@Entity
@Table(name = "AppointmentStatus")
public class AppointmentStatus {
	@Id
	@Column(name = "appointmentStatusID")
	private String appointmentStatusID;
	@Column(name = "appointmentStatus")
	private String appointmentStatus;

	public String getAppointmentStatusID() {
		return appointmentStatusID;
	}

	public void setAppointmentStatusID(String appointmentStatusID) {
		this.appointmentStatusID = appointmentStatusID;
	}

	public String getAppointmentStatus() {
		return appointmentStatus;
	}

	public void setAppointmentStatus(String appointmentStatus) {
		this.appointmentStatus = appointmentStatus;
	}

}
