package clinicMap.tingyen.model;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ClinicOpenStatusService implements IClinicOpenStatusService {
	@Autowired
	private ClinicOpenStatusDao cosDao;
	
	
	@Override
	public ClinicOpenStatus updateStatus(String clinicID, boolean openStatus, int currentNum,String openDescription) {
		return cosDao.updateStatus(clinicID, openStatus, currentNum,openDescription);
	}


	@Override
	public ClinicOpenStatus getCurrentNumber(String clinicID) {
		return cosDao.getCurrentNumber(clinicID);
	}


	@Override
	public ClinicOpenStatus saveCurrentNum(String clinicID, int currentNum) {
		return cosDao.saveCurrentNum(clinicID, currentNum);
	}


	@Override
	public ClinicOpenStatus updateStatus(String clinicID, boolean openStatus, int currentNum) {
		// TODO Auto-generated method stub
		return null;
	}

}
