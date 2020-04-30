package clinicMap.clinicgoogleMap;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.stereotype.Service;

@Service
public class positionService implements IpositionService{
	private positionDAO hpositionDao;
	private SessionFactory sessionFacotry;
	
	public positionService(SessionFactory sessionFacotry) {
		this.sessionFacotry = sessionFacotry;
		hpositionDao = new positionDAO(sessionFacotry);
	}
	
	@Override
	public void updateguestData(int positionID, String guestlat, String guestlng) {
		
		 hpositionDao.updateguestData(positionID,guestlat,guestlng);
	}
	
	@Override
	public position updatedriverData(int positionID,String driverlat, String driverlng){
		position hb1 = hpositionDao.updatedriverData(positionID,driverlat,driverlng);
		return hb1;
	}
	
	@Override
	public position updatedestinationData(int positionID,String destinationlat, String destinationlng){
		position hb1 = hpositionDao.updatedestinationData(positionID,destinationlat,destinationlng);
		return hb1;
	}
	
	@Override
	public position selectpositionData(int positionID) {
		position hb1 = hpositionDao.selectpositionData(positionID);
		return hb1;
	}

	@Override
	public List<position> selectAllOrderData(String drivername){
		 List<position> list = hpositionDao.selectAllOrderData(drivername);
		return list;
	}
	
	@Override
	public position neworderinData(position Pposition) {
		position hb1 = hpositionDao.neworderinData(Pposition);
		return hb1;
	}
	
	@Override
	public List<position> selectthisorderid(String drivername,String clinicName,String thispricetotal) {
		List<position> hb1 = hpositionDao.selectthisorderid(drivername,clinicName,thispricetotal);
		return hb1;
		
	}
	
}
