package clinicMap.clinicgoogleMap;

import java.util.List;

import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

@Service
public class ClinicServiceDavid implements IClinicService{
	
	private ClinicDAO hClinicDao;
	private SessionFactory sessionFacotry;

	public ClinicServiceDavid(SessionFactory sessionFacotry) {
		this.sessionFacotry = sessionFacotry;
		hClinicDao = new ClinicDAO(sessionFacotry);
	}

	
	@Override
	public List<Clinicdavid> selectmesData(String mes) {
		List<Clinicdavid> hb1 = hClinicDao.selectmesData(mes);
		return hb1;
	}
	
	@Override
	public List<Clinicdavid> selectData(String clinicType) {
		List<Clinicdavid> hb1 = hClinicDao.selectData(clinicType);
		return hb1;
	}
	
	
	@Override
	public Clinicdavid queryData(String clinicID) {
		Clinicdavid hb1 = hClinicDao.queryData(clinicID);
		return hb1;
	}

	@Override
	public List<Clinicdavid> queryAllData() {
		List<Clinicdavid> list = hClinicDao.queryAllData();
		return list;
	}

	@Override
	public Clinicdavid updateData(String clinicID,String clinicName,String clinicAddress,String clinicLng,String clinicLat) {
		Clinicdavid hb1 = hClinicDao.updateData(clinicID,clinicName,clinicAddress,clinicLng,clinicLat);
		return hb1;
	}



}
