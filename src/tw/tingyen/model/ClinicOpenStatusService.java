package tw.tingyen.model;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ClinicOpenStatusService implements IClinicOpenStatusService {
	@Autowired
	private ClinicOpenStatusDao cosDao;
	
	
	@Override
	public ClinicOpenStatus updateStatus(String clinicID, boolean openStatus, int currentNum) {
		return cosDao.updateStatus(clinicID, openStatus, currentNum);
	}


	@Override
	public ClinicOpenStatus getCurrentNumber(String clinicID) {
		return cosDao.getCurrentNumber(clinicID);
	}

}
