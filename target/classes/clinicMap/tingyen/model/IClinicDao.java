package clinicMap.tingyen.model;


public interface IClinicDao {
	public Clinic updateClinicProfile(int clinicID,String clinicName,String clinicAccount,
			String clinicPwd,String clinicAddress,String clinicDescription,byte[] clinicPhoto,
			byte[] clinicLicense, String clinicEmail,String clinicPhone,String clinicClass,
			String clinicType,String clinicStatus);
	public Clinic queryClinicProfile(int clinicID);
	public boolean deleteClinicProfile(int clinicID);
}
