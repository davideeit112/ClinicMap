package clinicMap.message;


import java.sql.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.springframework.stereotype.Component;

@Entity @Component
@Table(name = "Evaluation_Message")
public class EMessage {

	private int messageID;
	private int clinicID;
	private String appointmentID;
	private int memberID;
	private float Evaluation;
	private String Message;
	private Date evaluationTime;

	public EMessage(int clinicID, String appointmentID, float Evaluation, String Message,int memberID,Date evaluationTime) {
		this.memberID = memberID;
		this.clinicID = clinicID;
		this.appointmentID = appointmentID;
		this.Evaluation = Evaluation;
		this.Message = Message;
		this.evaluationTime = evaluationTime;
	}
	
	public EMessage() {
	}
	
	public EMessage(int messageID) {
		this.messageID = messageID;
	}

	@Id @Column(name = "MESSAGEID")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	public int getMessageID() {
		return messageID;
	}

	public void setMessageID(int messageID) {
		this.messageID = messageID;
	}
	
	@Column(name = "CLINICID")
	public int getClinicID() {
		return clinicID;
	}

	public void setClinicID(int clinicID) {
		this.clinicID = clinicID;
	}

	@Column(name = "APPOINTMENTID")
	public String getAppointmentID() {
		return appointmentID;
	}

	public void setAppointmentID(String appointmentID) {
		this.appointmentID = appointmentID;
	}
	
	@Column(name = "MEMBERID")
	public int getMemberID() {
		return memberID;
	}

	public void setMemberID(int memberID) {
		this.memberID = memberID;
	}

	@Column(name = "EVALUATION")
	public float getEvaluation() {
		return Evaluation;
	}

	public void setEvaluation(float evaluation) {
		Evaluation = evaluation;
	}

	@Column(name = "MESSAGE")
	public String getMessage() {
		return Message;
	}

	public void setMessage(String message) {
		Message = message;
	}

	@Column(name = "EVALUATIONTIME")
	public Date getEvaluationTime() {
		return evaluationTime;
	}

	public void setEvaluationTime(Date evaluationTime) {
		this.evaluationTime = evaluationTime;
	}	
}
