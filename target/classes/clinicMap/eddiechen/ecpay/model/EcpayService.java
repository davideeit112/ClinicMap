package clinicMap.eddiechen.ecpay.model;

import org.springframework.stereotype.Service;

@Service
public class EcpayService {
	
	public static void initial() {
		EcpayDao.initial();
	}
	

	public static String genAdPayment(String uuid/*, String clinicID*/) {
		return EcpayDao.genAdPayment(uuid);
	}
	
	public static String genTexiPayment(String uuid, String pricetotal/*, String clinicID*/) {
		return EcpayDao.genTexiPayment(uuid, pricetotal/*, clinicID*/);
	}
	
	
	public static void changeStatus(int clinicID) {
		 EcpayDao.changeStatus(clinicID);
	}
	

}
