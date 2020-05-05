package clinicMap.clinicgoogleMap;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.springframework.stereotype.Component;

@Entity
@Table(name="Clinic")
@Component
public class Clinicdavid {
	private String clinicID;
	private String clinicName;
	private String clinicAddress;
	private String clinicLng;
	private String clinicLat;
	private String clinicType;

	public Clinicdavid() {
	}
	
	public Clinicdavid(String clinicID, String clinicName, String clinicAddress,String clinicLng,String clinicLat,String clinicType) {
		
		this.clinicID = clinicID;
		this.clinicName = clinicName;
		this.clinicAddress = clinicAddress;
		this.clinicLng = clinicLng;
		this.clinicLat = clinicLat;
		this.clinicType = clinicType;
	}
	@Id
	@Column(name="clinicID")
	//@GeneratedValue(strategy = GenerationType.IDENTITY)
	public String getClinicID() {
		return clinicID;
	}


	public void setClinicID(String clinicID) {
		this.clinicID = clinicID;
	}

	@Column(name="clinicName")
	public String getClinicName() {
		return clinicName;
	}


	public void setClinicName(String clinicName) {
		this.clinicName = clinicName;
	}

	@Column(name="clinicAddress")
	public String getClinicAddress() {
		return clinicAddress;
	}


	public void setClinicAddress(String clinicAddress) {
		this.clinicAddress = clinicAddress;
	}

	@Column(name="clinicLng")
	public String getClinicLng() {
		return clinicLng;
	}


	public void setClinicLng(String clinicLng) {
		this.clinicLng = clinicLng;
	}

	@Column(name="clinicLat")
	public String getClinicLat() {
		return clinicLat;
	}


	public void setClinicLat(String clinicLat) {
		this.clinicLat = clinicLat;
	}
	
	@Column(name="clinicType")
	public String getClinicType() {
		return clinicType;
	}


	public void setClinicType(String clinicType) {
		this.clinicType = clinicType;
	}


}

