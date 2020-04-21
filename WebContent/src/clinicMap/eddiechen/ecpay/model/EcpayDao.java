package clinicMap.eddiechen.ecpay.model;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.UUID;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;

import clinicMap.eddiechen.ecpay.payment.integration.AllInOne;
import clinicMap.eddiechen.ecpay.payment.integration.domain.AioCheckOutALL;
import clinicMap.eddiechen.ecpay.payment.integration.domain.AioCheckOutCVS;
import clinicMap.eddiechen.ecpay.payment.integration.domain.InvoiceObj;

@Repository
public class EcpayDao {
	public static AllInOne all;
	private static SessionFactory sessionFactory;
	
	@Autowired
	public EcpayDao(@Qualifier(value = "sessionFactory") SessionFactory sessionFactory) {
		EcpayDao.sessionFactory = sessionFactory;
	}
	

	
	
	public static void initial(){
		all = new AllInOne("");
	}
	
	public static String genAioCheckOutALL(String clinicAccount){
		Session session  = EcpayDao.sessionFactory.getCurrentSession();
		AioCheckOutALL obj = new AioCheckOutALL();
		UUID uid = UUID.randomUUID();
		String id = uid.toString().replaceAll("-", "").substring(0, 20);
		Date date = Calendar.getInstance().getTime();  
		DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd hh:mm:ss");  
		String strDate = dateFormat.format(date);  
		obj.setMerchantTradeNo(id);
		obj.setMerchantTradeDate(strDate);
		obj.setTotalAmount("100");
		obj.setTradeDesc("test Description");
		obj.setItemName("ClinicMap廣告贊助");
		obj.setReturnURL("http://211.23.128.214:5000");
		obj.setNeedExtraPaidInfo("N");
		String form = all.aioCheckOut(obj, null);
		Sponsor s = new Sponsor();
		s.setClinicAccount(clinicAccount);
		s.setTradeID(id);
		s.setSponsorTime(strDate);
		session.save(s);
		return form;
	}
}
