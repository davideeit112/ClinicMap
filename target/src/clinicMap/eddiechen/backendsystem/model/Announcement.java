package clinicMap.eddiechen.backendsystem.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import org.springframework.stereotype.Component;

@Entity
@Table(name = "Announcement")
@Component
public class Announcement {
	public String id;
	public String type;
	public String text;
	
	@Id @Column(name = "id")
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	
	@Column(name = "announcementType")
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	
	@Column(name = "announcementText")
	public String getText() {
		return text;
	}
	public void setText(String text) {
		this.text = text;
	}
	
}
