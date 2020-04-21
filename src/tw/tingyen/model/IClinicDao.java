package tw.tingyen.model;

import java.sql.Blob;

import javax.servlet.http.HttpServletResponse;

public interface IClinicDao {
	public Clinic updateClinicProfile(String clinicID,String clinicName,String clinicAccount,
			String clinicPwd,String clinicAddress,String clinicDescription,byte[] clinicPhoto,
			String clinicPhone,int clinicClass,int clinicType,int clinicTime,String clinicStatus);
	public Clinic queryClinicProfile(String clinicID);
	public boolean deleteClinicProfile(String clinicID);
}
