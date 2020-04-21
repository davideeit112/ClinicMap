package tw.eddiechen.backendsystem.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.springframework.stereotype.Component;


@Entity
@Table(name="SysManagement")
@Component
public class SysManagement {
	


	private int ID;
	private String sysAccount;
	private String sysPwd;
	
	public SysManagement() {
		
	}
	
	public SysManagement(String sysAccount, String sysPwd) {
		this.sysAccount = sysAccount;
		this.sysPwd = sysPwd;
	}
	
	@Id@Column(name="ID")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	public int getID() {
		return ID;
	}

	public void setID(int iD) {
		ID = iD;
	}
	
	@Column(name = "sysAccount")
	public String getSysAccount() {
		return sysAccount;
	}

	public void setSysAccount(String sysAccount) {
		this.sysAccount = sysAccount;
	}
	
	@Column(name = "sysPwd")
	public String getSysPwd() {
		return sysPwd;
	}

	public void setSysPwd(String sysPwd) {
		this.sysPwd = sysPwd;
	}

}
