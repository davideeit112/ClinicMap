package tw.tingyen.model;

import java.sql.Blob;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
@Service
public class ClinicService implements IClinicService {
	@Autowired
	private ClinicDao cDao;
	
	@Override
	public Clinic updateClinicProfile(String clinicID, String clinicName, String clinicAccount, String clinicPwd,
			String clinicAddress, String clinicDescription, Blob clinicPhoto, String clinicPhone, int clinicClass,
			int clinicType, int clinicTime, String clinicStatus) {
		return cDao.updateClinicProfile(clinicID, clinicName, clinicAccount, clinicPwd, clinicAddress, clinicDescription, clinicPhoto, clinicPhone, clinicClass, clinicType, clinicTime, clinicStatus);
	}

	@Override
	public Clinic queryClinicProfile(String clinicID) {
		return cDao.queryClinicProfile(clinicID);
	}

	@Override
	public boolean deleteClinicProfile(String clinicID) {
		return cDao.deleteClinicProfile(clinicID);
	}

}
