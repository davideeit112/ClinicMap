package clinicMap.clinicgoogleMap;

import java.util.List;

public interface IpositionService {
	//客人位置  更新
	public void updateguestData(int positionID, String guestlat, String guestlng);
	//司機位置  更新
	public position updatedriverData(int positionID,String driverlat, String driverlng);
	//目的地位置  更新
	public position updatedestinationData(int positionID,String destinationlat, String destinationlng);
	//利用id搜尋資料
	public position selectpositionData(int positionID);
	//司機尋找訂單
	public List<position> selectAllOrderData(String drivername);
	//新增訂單資料
	public position neworderinData(position Pposition);
}



