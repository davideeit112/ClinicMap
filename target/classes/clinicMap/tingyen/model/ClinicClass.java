package clinicMap.tingyen.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.springframework.stereotype.Component;

@Component
@Entity
@Table(name = "ClinicClass")
public class ClinicClass {
	@Id
	@Column(name = "clinicClassID")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int clinicClassID;
	@Column(name = "clinicClass")
	private String clinicClass;

	public int getClinicClassID() {
		return clinicClassID;
	}

	public void setClinicClassID(int clinicClassID) {
		this.clinicClassID = clinicClassID;
	}

	public String getClinicClass() {
		return clinicClass;
	}

	public void setClinicClass(String clinicClass) {
		this.clinicClass = clinicClass;
	}

}
