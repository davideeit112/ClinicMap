package tw.tingyen.model;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class ClinicDao implements IClinicDao {
	@Autowired
	private SessionFactory sessionFactory;
	
	@Override
	public Clinic updateClinicProfile(int clinicID,String clinicName,String clinicAccount,
			String clinicPwd,String clinicAddress,String clinicDescription,byte[] clinicPhoto,
			byte[] clinicLicense, String clinicEmail,String clinicPhone,String clinicClass,
			String clinicType,String clinicStatus) {
		Session session = sessionFactory.getCurrentSession();
		Clinic cBean = session.get(Clinic.class, clinicID);
		cBean.setClinicName(clinicName);
		cBean.setClinicAccount(clinicAccount);
		cBean.setClinicPwd(clinicPwd);
		cBean.setClinicAddress(clinicAddress);
		cBean.setClinicDescription(clinicDescription);
		cBean.setClinicPhoto(clinicPhoto);
		cBean.setClinicPhone(clinicPhone);
		cBean.setClinicLicense(clinicLicense);
		cBean.setClinicEmail(clinicEmail);
		cBean.setClinicClass(clinicClass);
		cBean.setClinicType(clinicType);
		cBean.setClinicStatus(clinicStatus);
		return cBean;
	}

	@Override
	public Clinic queryClinicProfile(int clinicID) {
		Session session = sessionFactory.getCurrentSession();
		Clinic cBean = session.get(Clinic.class, clinicID);
		return cBean;
	}

	@Override
	public boolean deleteClinicProfile(int clinicID) {
		Session session = sessionFactory.getCurrentSession();
		Clinic cBean = session.get(Clinic.class, clinicID);
		if(cBean != null) {
			session.delete(cBean);
			return true;
		}
		return false;
	}

}
