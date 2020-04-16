package clinicMap.order;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import org.springframework.stereotype.Component;

@Component
@Entity
@Table(name = "AppointmentStatus" )
public class AppointmentStatus {
	private String appointmentStatusID;
	private String appointmentStatus;
	
	@Id
	@Column(name="appointmentStatusID")
	public String getAppointmentStatusID() {
		return appointmentStatusID;
	}
	public void setAppointmentStatusID(String appointmentStatusID) {
		this.appointmentStatusID = appointmentStatusID;
	}
	
	@Column(name="AppointmentStatus")
	public String getAppointmentStatus() {
		return appointmentStatus;
	}
	public void setAppointmentStatus(String appointmentStatus) {
		this.appointmentStatus = appointmentStatus;
	}

}
