package clinicMap.clinicgoogleMap;

import java.util.List;

public interface IClinicService {
	
	public List<Clinic> selectmesData(String mes);
	
	public List<Clinic> selectData(String clinicType);
	
	public Clinic queryData(String clinicID);

	public List<Clinic> queryAllData() ;

	public Clinic updateData(String clinicID,String clinicName,String clinicAddress,String clinicLng,String clinicLat);

}

