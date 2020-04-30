package clinicMap.tingyen.model;

public interface IClinicOpenStatusDao {
	public ClinicOpenStatus updateStatus(String clinicID,boolean openStatus,int currentNum);
	public ClinicOpenStatus getCurrentNumber(String clinicID);
	ClinicOpenStatus saveCurrentNum(String clinicID, int currentNum);
	ClinicOpenStatus updateStatus(String clinicID, boolean openStatus, int currentNum, String openDescription);
}
