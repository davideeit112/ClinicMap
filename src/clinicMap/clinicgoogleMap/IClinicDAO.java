package clinicMap.clinicgoogleMap;

import java.util.List;

public interface IClinicDAO {
	
	public List<Clinicdavid> selectmesData(String mes);
	
	public List<Clinicdavid> selectData(String clinicType);

	public Clinicdavid queryData(String clinicID);

	public List<Clinicdavid> queryAllData() ;

	public Clinicdavid updateData(String clinicID,String clinicName,String clinicAddress,String clinicLng,String clinicLat) ;

}
