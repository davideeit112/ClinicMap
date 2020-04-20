package tw.tingyen.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import org.springframework.stereotype.Component;

@Component
@Entity
@Table(name = "ClinicTime")
public class ClinicTime {
	@Id
	@Column(name = "clinicTimeID")
	private int clinicTimeID;
	@Column(name = "clinicTime")
	private String clinicTime;

	public int getClinicTimeID() {
		return clinicTimeID;
	}

	public void setClinicTimeID(int clinicTimeID) {
		this.clinicTimeID = clinicTimeID;
	}

	public String getClinicTime() {
		return clinicTime;
	}

	public void setClinicTime(String clinicTime) {
		this.clinicTime = clinicTime;
	}

}
