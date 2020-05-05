package clinicMap.alarm;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import org.springframework.stereotype.Component;

@Component
@Entity
@Table(name="Announcement")
public class Announcement1 {

	private String ID;
	private String announcementType;
	private String announcementText;
	
	@Id
	@Column(name="ID")
	public String getID() {
		return ID;
	}
	public void setID(String iD) {
		ID = iD;
	}
	
	@Column(name="announcementType")
	public String getAnnouncementType() {
		return announcementType;
	}
	public void setAnnouncementType(String announcementType) {
		this.announcementType = announcementType;
	}
	
	@Column(name="announcementText")
	public String getAnnouncementText() {
		return announcementText;
	}
	public void setAnnouncementText(String announcementText) {
		this.announcementText = announcementText;
	}

}
