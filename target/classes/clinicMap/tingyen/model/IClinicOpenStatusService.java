package clinicMap.tingyen.model;

public interface IClinicOpenStatusService {
	public ClinicOpenStatus updateStatus(String clinicID,boolean openStatus,int currentNum);
	public ClinicOpenStatus getCurrentNumber(String clinicID);
	ClinicOpenStatus updateStatus(String clinicID, boolean openStatus, int currentNum, String openDescription);
	ClinicOpenStatus saveCurrentNum(String clinicID, int currentNum);
}
