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
@Table(name = "ClinicType")
public class ClinicType {
	@Id
	@Column(name = "clinicTypeID")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int clinicTypeID;
	@Column(name = "clinicType")
	private String clinicType;

	public int getClinicTypeID() {
		return clinicTypeID;
	}

	public void setClinicTypeID(int clinicTypeID) {
		this.clinicTypeID = clinicTypeID;
	}

	public String getClinicType() {
		return clinicType;
	}

	public void setClinicType(String clinicType) {
		this.clinicType = clinicType;
	}

}
