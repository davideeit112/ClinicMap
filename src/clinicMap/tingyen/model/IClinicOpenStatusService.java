package tw.tingyen.model;

public interface IClinicOpenStatusService {
	public ClinicOpenStatus updateStatus(String clinicID,boolean openStatus,int currentNum,String openDescription);
	public ClinicOpenStatus getCurrentNumber(String clinicID);
	public ClinicOpenStatus saveCurrentNum(String clinicID,int currentNum);
}
