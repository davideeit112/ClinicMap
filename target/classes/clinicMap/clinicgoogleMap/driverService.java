package clinicMap.clinicgoogleMap;

import java.util.List;

import org.hibernate.SessionFactory;
import org.springframework.stereotype.Service;

@Service
public class driverService implements IdriverService{
	private driverDAO hdriverDao;
	private SessionFactory sessionFacotry;
	
	public driverService(SessionFactory sessionFacotry) {
		this.sessionFacotry = sessionFacotry;
		hdriverDao = new driverDAO(sessionFacotry);
	}
	
	@Override
	public List<driver> showdriver(){
		List<driver> list = hdriverDao.showdriver();
		return list;
	}
	
	public boolean DLogin(driver driver) {
		return hdriverDao.DLogin(driver);
	}
	
}
