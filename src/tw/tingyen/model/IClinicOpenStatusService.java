package tw.tingyen.model;

public interface IClinicOpenStatusService {
	public ClinicOpenStatus updateStatus(String clinicID,boolean openStatus,int currentNum);
	public ClinicOpenStatus getCurrentNumber(String clinicID);
}
