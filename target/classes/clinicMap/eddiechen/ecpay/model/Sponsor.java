package clinicMap.eddiechen.ecpay.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.springframework.stereotype.Component;

@Entity
@Table(name = "Sponsor")
@Component
public class Sponsor {
	public int sponsorID;
	public String clinicAccount;
	public String tradeID;
	public String sponsorTime;
	
	@Id@Column(name = "sponsorID")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	public int getSponsorID() {
		return sponsorID;
	}
	public void setSponsorID(int sponsorID) {
		this.sponsorID = sponsorID;
	}
	
	@Column(name = "clinicAccount")
	public String getClinicAccount() {
		return clinicAccount;
	}
	public void setClinicAccount(String clinicAccount) {
		this.clinicAccount = clinicAccount;
	}
	
	@Column(name = "tradeID")
	public String getTradeID() {
		return tradeID;
	}
	public void setTradeID(String tradeID) {
		this.tradeID = tradeID;
	}
	
	@Column(name = "sponsorTime")
	public String getSponsorTime() {
		return sponsorTime;
	}
	public void setSponsorTime(String sponsorTime) {
		this.sponsorTime = sponsorTime;
	}
	
	
}
