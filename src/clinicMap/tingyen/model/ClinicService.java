package clinicMap.tingyen.model;

import java.sql.Blob;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
@Service
public class ClinicService implements IClinicService {
	@Autowired
	private ClinicDao cDao;
	
	@Override
	public Clinic updateClinicProfile(int clinicID,String clinicName,String clinicAccount,
			String clinicPwd,String clinicAddress,String clinicDescription,byte[] clinicPhoto,
			byte[] clinicLicense, String clinicEmail,String clinicPhone,String clinicClass,
			String clinicType,String clinicStatus) {
		return cDao.updateClinicProfile(clinicID, clinicName, clinicAccount, clinicPwd, clinicAddress, clinicDescription, clinicPhoto, clinicLicense, clinicEmail, clinicPhone, clinicClass, clinicType, clinicStatus);
	}

	@Override
	public Clinic queryClinicProfile(int clinicID) {
		return cDao.queryClinicProfile(clinicID);
	}

	@Override
	public boolean deleteClinicProfile(int clinicID) {
		return cDao.deleteClinicProfile(clinicID);
	}

}
