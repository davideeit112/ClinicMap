package clinicMap.tingyen.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import org.springframework.stereotype.Component;

@Component
@Entity
@Table(name = "ClinicStatus")
public class ClinicStatus {
	@Id
	@Column(name = "clinicStatusID")
	private String clinicStatusID;
	@Column(name = "clinicStatus")
	private String clinicStatus;

	public String getClinicStatusID() {
		return clinicStatusID;
	}

	public void setClinicStatusID(String clinicStatusID) {
		this.clinicStatusID = clinicStatusID;
	}

	public String getClinicStatus() {
		return clinicStatus;
	}

	public void setClinicStatus(String clinicStatus) {
		this.clinicStatus = clinicStatus;
	}

}
