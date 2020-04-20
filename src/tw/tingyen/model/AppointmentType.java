package tw.tingyen.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import org.springframework.stereotype.Component;

@Component
@Entity
@Table(name = "AppointmentType")
public class AppointmentType {
	@Id
	@Column(name = "appointmentTypeID")
	private String appointmentTypeID;
	@Column(name = "appointmentType")
	private String appointmentType;

	public String getAppointmentTypeID() {
		return appointmentTypeID;
	}

	public void setAppointmentTypeID(String appointmentTypeID) {
		this.appointmentTypeID = appointmentTypeID;
	}

	public String getAppointmentType() {
		return appointmentType;
	}

	public void setAppointmentType(String appointmentType) {
		this.appointmentType = appointmentType;
	}

}
