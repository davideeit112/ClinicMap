package clinicMap.clinicgoogleMap;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.springframework.stereotype.Component;

@Entity
@Table(name = "position")
@Component
public class position {
	private int positionID;
	private String drivername;
	private String clinicName;
	private String guestlat;
	private String guestlng;
	private String driverlat;
	private String driverlng;
	private String destinationlat;
	private String destinationlng;
	private String thispricetotal;  

	public position() {

	}

	public position(String drivername,String clinicName,String guestlat, String guestlng, String driverlat, String driverlng,
			String destinationlat, String destinationlng,String thispricetotal) {
		this.drivername=drivername;
		this.clinicName =clinicName;
		this.guestlat = guestlat;
		this.guestlng = guestlng;
		this.driverlat = driverlat;
		this.driverlng = driverlng;
		this.destinationlat = destinationlat;
		this.destinationlng = destinationlng;
		this.thispricetotal = thispricetotal;
	}

	@Id
	@Column(name = "positionID")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	public int getpositionID() {
		return positionID;
	}

	public void setpositionID(int positionID) {
		this.positionID = positionID;
	}
	
	@Column(name = "drivername")
	public String getdrivername() {
		return drivername;
	}

	public void setdrivername(String drivername) {
		this.drivername = drivername;
	}
	
	@Column(name = "clinicName")
	public String getclinicName() {
		return clinicName;
	}

	public void setclinicName(String clinicName) {
		this.clinicName = clinicName;
	}

	@Column(name = "guestlat")
	public String getguestlat() {
		return guestlat;
	}

	public void setguestlat(String guestlat) {
		this.guestlat = guestlat;
	}

	@Column(name = "guestlng")
	public String getguestlng() {
		return guestlng;
	}

	public void setguestlng(String guestlng) {
		this.guestlng = guestlng;
	}

	@Column(name = "driverlat")
	public String getdriverlat() {
		return driverlat;
	}

	public void setdriverlat(String driverlat) {
		this.driverlat = driverlat;
	}

	@Column(name = "driverlng")
	public String getdriverlng() {
		return driverlng;
	}

	public void setdriverlng(String driverlng) {
		this.driverlng = driverlng;
	}

	@Column(name = "destinationlat")
	public String getdestinationlat() {
		return destinationlat;
	}

	public void setdestinationlat(String destinationlat) {
		this.destinationlat = destinationlat;
	}

	@Column(name = "destinationlng")
	public String getdestinationlng() {
		return destinationlng;
	}

	public void setdestinationlng(String destinationlng) {
		this.destinationlng = destinationlng;
	}
		
	@Column(name = "thispricetotal")
	public String getthispricetotal() {
		return thispricetotal;
	}

	public void setthispricetotal(String thispricetotal) {
		this.thispricetotal = thispricetotal;
	}
}
