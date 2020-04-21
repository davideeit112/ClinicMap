package clinicMap.eddiechen.ecpay.model;

import org.springframework.stereotype.Service;

@Service
public class EcpayService {
	
	public static void initial() {
		EcpayDao.initial();
	}
	

	public static String genAioCheckOutALL(String clinicAccount) {
		return EcpayDao.genAioCheckOutALL(clinicAccount);
	}
}
