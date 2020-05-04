package clinicMap.eddiechen.backendsystem.model;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.apache.commons.mail.DefaultAuthenticator;
import org.apache.commons.mail.HtmlEmail;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.RequestParam;


@Repository
public class SysManagementDao {
	private SessionFactory sessionFactory;
	
	@Autowired
	public SysManagementDao(@Qualifier(value = "sessionFactory") SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

	public boolean checkLogin(SysManagement sysManagement) {
		try {
			Session session = sessionFactory.getCurrentSession();
			String hqlstr = "from SysManagement where sysAccount=:user and sysPwd=:pwd";
			Query<SysManagement> query = session.createQuery(hqlstr, SysManagement.class);
			query.setParameter("user", sysManagement.getSysAccount());
			query.setParameter("pwd", sysManagement.getSysPwd());

			SysManagement sysAcc = query.uniqueResult();

			if (sysAcc != null) {
				return true;
			}

			return false;
		} catch (Exception e) {
			System.out.println("e:" + e);
			return false;
		}
	}

	public List<Clinicchen> clinicData() {
		Session session = sessionFactory.getCurrentSession();
		String hqlstr = "from Clinicchen";
		Query<Clinicchen> query = session.createQuery(hqlstr, Clinicchen.class);
		List<Clinicchen> list = (List<Clinicchen>) query.list();
		return list;
	}

	public void savePersonWithPhoto(String photoFilePath, int count) throws IOException {
		Session session = sessionFactory.getCurrentSession();
		Clinicchen Clinicchen = session.get(Clinicchen.class, count);
		byte[] photoBytes = readBytesFromFile(photoFilePath);
		Clinicchen.setClinicPhoto(photoBytes);
		System.out.println("123");
		session.update(Clinicchen);
		

	}

	public byte[] readBytesFromFile(String filePath) throws IOException {
		File inputFile = new File(filePath);
		FileInputStream inputStream = new FileInputStream(inputFile);

		byte[] fileBytes = new byte[(int) inputFile.length()];
		inputStream.read(fileBytes);
		inputStream.close();

		return fileBytes;
	}

	public void readPhotoOfPerson(int personId, String photoFilePath) throws IOException {
		Session session = sessionFactory.getCurrentSession();
		Clinicchen Clinicchen = (Clinicchen) session.get(Clinicchen.class, personId);
		byte[] photoBytes = Clinicchen.getClinicPhoto();
		saveBytesToFile(photoFilePath, photoBytes);
	}

	public void saveBytesToFile(String photoFilePath, byte[] photoBytes) throws IOException {
		FileOutputStream outputStream = new FileOutputStream(photoFilePath);
		outputStream.write(photoBytes);
		outputStream.close();
	}

	public void emailActivate(int clinicID) {
		
		System.out.println("emailinside");
		HtmlEmail em = new HtmlEmail();

		em.setHostName("smtp.gmail.com");
		em.setSmtpPort(587);
		em.setAuthenticator(new DefaultAuthenticator("clinicofmap", "clinicmapemail"));
		em.setSSLOnConnect(true);
		em.setCharset("UTF-8");

//    	em.setStartTLSEnabled(true);
//    	em.setHostName("smtp.gmail.com");
//    	em.setSmtpPort(465);
//    	em.setAuthenticator(new DefaultAuthenticator("gmail: clinicofmap", "Eddie0320."));
//    	em.setSSLOnConnect(true);

		try {
			em.setFrom("clinicofmap@gmail.com");
			em.setSubject("ClinicMapEmailAuthenticate");
			String activeUrl = "http://localhost:8080/clinicMap/changeStatus?clinicID=" + clinicID;
			em.setHtmlMsg("點擊鏈接激活信箱:" + activeUrl);
			em.setTextMsg("測試");
			em.addTo("warcraft0320@gmail.com");
			em.send();
			System.out.println("Email send successfully");

		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void changeStatus(int clinicID) {
		Session session = sessionFactory.getCurrentSession();
		Clinicchen Clinicchen = session.get(Clinicchen.class, clinicID);
		Clinicchen.setClinicStatus("CS1");

		session.update(Clinicchen);
	}
	
	public void changeStatus2(int clinicID) {
		Session session = sessionFactory.getCurrentSession();
		Clinicchen Clinicchen = session.get(Clinicchen.class, clinicID);
		Clinicchen.setClinicStatus("CS2");

		session.update(Clinicchen);
	}
	
	public void updateData(int clinicid, String clinicname,String clinicaccount, String clinicpwd,String clinicaddress,  String clinicdescription, String clinicphone, String clinicemail, String clinicclass,  String clinictype, String clinictime, String cliniclat, String cliniclng, String clinicstatus) {
		Session session = sessionFactory.getCurrentSession();
		Clinicchen Clinicchen = session.get(Clinicchen.class, clinicid);
		Clinicchen.setClinicName(clinicname);
		Clinicchen.setClinicAccount(clinicaccount);
		Clinicchen.setClinicPwd(clinicpwd);
		Clinicchen.setClinicAddress(clinicaddress);
		Clinicchen.setClinicDescription(clinicdescription);
		Clinicchen.setClinicPhone(clinicphone);
		Clinicchen.setClinicEmail(clinicemail);
		Clinicchen.setClinicClass(clinicclass);
		Clinicchen.setClinicType(clinictype);
		Clinicchen.setClinicTime(clinictime);
		Clinicchen.setCliniclat(cliniclat);
		Clinicchen.setCliniclng(cliniclng);
		Clinicchen.setClinicStatus(clinicstatus);

		session.update(Clinicchen);
	}
	
	public void updateAnnouncement(String id, String type, String text) {
		Session session = sessionFactory.getCurrentSession();
		Announcement ann = new Announcement();
		ann.setId(id);
		ann.setType(type);
		ann.setText(text);
		session.saveOrUpdate(ann);
	}
	
	
	public String UpdateAnnouncementText() {
		Session session = sessionFactory.getCurrentSession();
		Date date = Calendar.getInstance().getTime();  
		DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");  
		String strDate = dateFormat.format(date);
		Announcement announce = session.get(Announcement.class, strDate);
		String text = announce.getText();
		return text;
	}
}
