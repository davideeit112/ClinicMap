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
	
	public void savePersonWithPhoto(String photoFilePath, int count) throws IOException {
		 sysManagementDao.savePersonWithPhoto(photoFilePath, count);
	}
	
	public void readPhotoOfPerson(int clinicID, String photoFilePath) throws IOException {
		sysManagementDao.readPhotoOfPerson(clinicID, photoFilePath);
	}
	
	public void emailActivate(int clinicID) {
		sysManagementDao.emailActivate(clinicID);
	}
	
	public void changeStatus2(int clinicID) {
		sysManagementDao.changeStatus2(clinicID);
	}
	public void changeStatus(int clinicID) {
		sysManagementDao.changeStatus(clinicID);
	}
	
	public void updateData(int clinicid, String clinicname,String clinicaccount, String clinicpwd,String clinicaddress,  String clinicdescription, String clinicphone, String clinicemail, String clinicclass,  String clinictype, String clinictime, String cliniclat, String cliniclng, String clinicstatus) {
		sysManagementDao.updateData(clinicid, clinicname, clinicaccount, clinicpwd, clinicaddress, clinicdescription, clinicphone, clinicemail, clinicclass, clinictype, clinictime, cliniclat, cliniclng, clinicstatus);
	}
	
	public void updateAnnouncement(String id, String type, String text) {
		sysManagementDao.updateAnnouncement(id, type, text);

	}
	
}
