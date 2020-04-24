package clinicMap.eddiechen.backendsystem.model;

import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SysManagementService {
	private SysManagementDao sysManagementDao;
	
	@Autowired
	public SysManagementService(SysManagementDao sysManagementDao) {
		this.sysManagementDao = sysManagementDao;
	}
	
	public boolean checkLogin(SysManagement sysManagement) {
		return sysManagementDao.checkLogin(sysManagement);
	}
	
	public List<Clinicchen> clinicData(){
		return sysManagementDao.clinicData();
	}
	
	public void savePersonWithPhoto(String photoFilePath) throws IOException {
		 sysManagementDao.savePersonWithPhoto(photoFilePath);
	}
	
	public void readPhotoOfPerson(int clinicID, String photoFilePath) throws IOException {
		sysManagementDao.readPhotoOfPerson(clinicID, photoFilePath);
	}
	
	public void emailActivate(int clinicID) {
		sysManagementDao.emailActivate(clinicID);
	}
	
	public void changeStatus(int clinicID) {
		sysManagementDao.changeStatus(clinicID);
	}
	public void changeStatus2(int clinicID) {
		  sysManagementDao.changeStatus2(clinicID);
		 }
}
