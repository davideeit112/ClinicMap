package clinicMap.message;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.springframework.stereotype.Component;

@Entity
@Component
@Table(name = "Reply_Message")
public class ReplyBean {
	private int messageID;
	private int clinicID;
	private String replyMessage;
	
	public ReplyBean(int messageID, int clinicID, String replyMessage) {
		this.messageID = messageID;
		this.clinicID = clinicID;
		this.replyMessage = replyMessage;
	}

	public ReplyBean() {
	}
	
	@Id
	@Column(name="messageID")
	public int getMessageID() {
		return messageID;
	}

	public void setMessageID(int messageID) {
		this.messageID = messageID;
	}

	@Column(name="clinicID")
	public int getClinicID() {
		return clinicID;
	}

	public void setClinicID(int clinicID) {
		this.clinicID = clinicID;
	}

	@Column(name="replyMessage")
	public String getReplyMessage() {
		return replyMessage;
	}

	public void setReplyMessage(String replyMessage) {
		this.replyMessage = replyMessage;
	}
}

