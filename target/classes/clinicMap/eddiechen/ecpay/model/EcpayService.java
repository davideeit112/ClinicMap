package clinicMap.eddiechen.ecpay.model;

import org.springframework.stereotype.Service;

@Service
public class EcpayService {
	
	public static void initial() {
		EcpayDao.initial();
	}
	

	public static String genAdPayment(String id) {
		System.out.println("00");
		return EcpayDao.genAdPayment(id);
	}
	
	public static String genTexiPayment(String id, String pricetotal) {
		return EcpayDao.genTexiPayment(id, pricetotal);
	}
	
	
	public static void changeStatus(int clinicID) {
		 EcpayDao.changeStatus(clinicID);
	}
	

}
