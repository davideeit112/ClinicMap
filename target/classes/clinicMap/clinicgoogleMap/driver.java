package clinicMap.clinicgoogleMap;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import org.springframework.stereotype.Component;

@Entity
@Table(name = "driver")
@Component
public class driver {
	private String driverID;
	private String drivername;
	private String driveraccount;
	private String driverpassword;
	private String drivernowlat;
	private String drivernowlng;
	
	
	public driver() {

	}
	
	public driver(String driverID,String drivername,String driveraccount,String driverpassword,String drivernowlat,String drivernowlng) {
		this.driverID = driverID;
		this.drivername =drivername;
		this.driveraccount = driveraccount;
		this.driverpassword = driverpassword;
		this.drivernowlat = drivernowlat;
		this.drivernowlng = drivernowlng;

	}
		
	@Id
	@Column(name = "driverID")	
	public String getdriverID() {
		return driverID;
	}
	public void setdriverID(String driverID) {
		this.driverID = driverID;
	}
	
	@Column(name = "drivername")
	public String getdrivername() {
		return drivername;
	}
	public void setdrivername(String drivername) {
		this.drivername = drivername;
	}
	
	@Column(name = "driveraccount")
	public String getdriveraccount() {
		return driveraccount;
	}
	public void setdriveraccount(String driveraccount) {
		this.driveraccount = driveraccount;
	}
	
	@Column(name = "driverpassword")
	public String getdriverpassword() {
		return driverpassword;
	}
	public void setdriverpassword(String driverpassword) {
		this.driverpassword = driverpassword;
	}
	
	@Column(name = "drivernowlat")
	public String getdrivernowlat() {
		return drivernowlat;
	}
	public void setdrivernowlat(String drivernowlat) {
		this.drivernowlat = drivernowlat;
	}
	
	@Column(name = "drivernowlng")
	public String getdrivernowlng() {
		return drivernowlng;
	}
	public void setdrivernowlng(String drivernowlng) {
		this.drivernowlng = drivernowlng;
	}

	
	
	
	
}
