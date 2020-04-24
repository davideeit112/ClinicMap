package clinicMap.tingyen.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import org.springframework.stereotype.Component;

@Component
@Entity
@Table(name = "ClinicOpenStatus")
public class ClinicOpenStatus {
	@Id @Column(name="clinicID")
	private String clinicID;
	@Column(name="clinicOpenStatus")
	private boolean clinicOpenStatus;
	@Column(name="clinicCurrentNumber")
	private int clinicCurrentNumber;

	public String getClinicID() {
		return clinicID;
	}

	public void setClinicID(String clinicID) {
		this.clinicID = clinicID;
	}

	public boolean getClinicOpenStatus() {
		return clinicOpenStatus;
	}

	public void setClinicOpenStatus(boolean clinicOpenStatus) {
		this.clinicOpenStatus = clinicOpenStatus;
	}

	public int getClinicCurrentNumber() {
		return clinicCurrentNumber;
	}

	public void setClinicCurrentNumber(int clinicCurrentNumber) {
		this.clinicCurrentNumber = clinicCurrentNumber;
	}

	public void setOpenDescription(String openDescription) {
		// TODO Auto-generated method stub
		
	}

	public char[] getOpenDescription() {
		// TODO Auto-generated method stub
		return null;
	}

}
