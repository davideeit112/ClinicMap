package tw.tingyen.model;

import java.sql.Blob;


import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class ClinicDao implements IClinicDao {
	@Autowired
	private SessionFactory sessionFactory;
	
	@Override
	public Clinic updateClinicProfile(String clinicID, String clinicName, String clinicAccount, String clinicPwd,
			String clinicAddress, String clinicDescription, byte[] clinicPhoto, String clinicPhone, int clinicClass,
			int clinicType, int clinicTime, String clinicStatus) {
		Session session = sessionFactory.getCurrentSession();
		Clinic cBean = session.get(Clinic.class, clinicID);
		cBean.setClinicName(clinicName);
		cBean.setClinicAccount(clinicAccount);
		cBean.setClinicPwd(clinicPwd);
		cBean.setClinicAddress(clinicAddress);
		cBean.setClinicDescription(clinicDescription);
		cBean.setClinicPhoto(clinicPhoto);
		cBean.setClinicPhone(clinicPhone);
		cBean.setClinicClass(clinicClass);
		cBean.setClinicType(clinicType);
		cBean.setClinicTime(clinicTime);
		cBean.setClinicStatus(clinicStatus);
		return cBean;
	}

	@Override
	public Clinic queryClinicProfile(String clinicID) {
		Session session = sessionFactory.getCurrentSession();
		Clinic cBean = session.get(Clinic.class, clinicID);
		return cBean;
	}

	@Override
	public boolean deleteClinicProfile(String clinicID) {
		Session session = sessionFactory.getCurrentSession();
		Clinic cBean = session.get(Clinic.class, clinicID);
		if(cBean != null) {
			session.delete(cBean);
			return true;
		}
		return false;
	}

}
