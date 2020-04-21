package clinicMap.clinicgoogleMap;

import java.util.List;

import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

@Service
public class ClinicService implements IClinicService{
	
	private ClinicDAO hClinicDao;
	private SessionFactory sessionFacotry;

	public ClinicService(SessionFactory sessionFacotry) {
		this.sessionFacotry = sessionFacotry;
		hClinicDao = new ClinicDAO(sessionFacotry);
	}

	
	@Override
	public List<Clinic> selectmesData(String mes) {
		List<Clinic> hb1 = hClinicDao.selectmesData(mes);
		return hb1;
	}
	
	@Override
	public List<Clinic> selectData(String clinicType) {
		List<Clinic> hb1 = hClinicDao.selectData(clinicType);
		return hb1;
	}
	
	
	@Override
	public Clinic queryData(String clinicID) {
		Clinic hb1 = hClinicDao.queryData(clinicID);
		return hb1;
	}

	@Override
	public List<Clinic> queryAllData() {
		List<Clinic> list = hClinicDao.queryAllData();
		return list;
	}

	@Override
	public Clinic updateData(String clinicID,String clinicName,String clinicAddress,String clinicLng,String clinicLat) {
		Clinic hb1 = hClinicDao.updateData(clinicID,clinicName,clinicAddress,clinicLng,clinicLat);
		return hb1;
	}



}
