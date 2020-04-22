package clinicMap.tingyen.model;

public interface IClinicDao {
	public Clinic updateClinicProfile(int clinicID,String clinicName,String clinicAccount,
			String clinicPwd,String clinicAddress,String clinicDescription,byte[] clinicPhoto,
			String clinicPhone,String clinicClass,String clinicType,String clinicTime,String clinicStatus);
	public Clinic queryClinicProfile(int clinicID);
	public boolean deleteClinicProfile(String clinicID);
}
