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

import clinicMap.eddiechen.backendsystem.model.Clinicchen;
import clinicMap.eddiechen.ecpay.payment.integration.AllInOne;
import clinicMap.eddiechen.ecpay.payment.integration.domain.AioCheckOutALL;
import clinicMap.eddiechen.ecpay.payment.integration.domain.AioCheckOutCVS;
import clinicMap.eddiechen.ecpay.payment.integration.domain.InvoiceObj;
import clinicMap.tingyen.model.Clinic;

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
	
	public static String genAdPayment(String id){
//		Session session  = EcpayDao.sessionFactory.getCurrentSession();
		AioCheckOutALL obj = new AioCheckOutALL();
		
		Date date = Calendar.getInstance().getTime();  
		DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd hh:mm:ss");  
		String strDate = dateFormat.format(date);  
		obj.setMerchantTradeNo(id);
		obj.setMerchantTradeDate(strDate);
		obj.setTotalAmount("100");
		obj.setTradeDesc("test Description");
		obj.setItemName("ClinicMap廣告贊助");
		obj.setReturnURL("http://211.23.128.214:5000");
		obj.setClientBackURL("http://localhost:8080/SpringAllForOne/changeStatusAfterPay?id=" + id);
//		obj.setOrderResultURL("http://localhost:8080/SpringAllForOne/Test.jsp");
		System.out.println(id);
		obj.setNeedExtraPaidInfo("N");
		String form = all.aioCheckOut(obj, null);
//		Sponsor s = new Sponsor();
//		s.setClinicAccount(clinicAccount);
//		s.setTradeID(id);
//		s.setSponsorTime(strDate);
//		session.save(s);
		return form;
	}
	
	public static void changeStatus(int clinicID) {
		Session session = sessionFactory.getCurrentSession();
		Clinicchen clinic = session.get(Clinicchen.class, clinicID);
		clinic.setClinicStatus("CS3");

		session.update(clinic);
	}
	
	public static String genTexiPayment(String id, String pricetotal) {
//		Session session  = EcpayDao.sessionFactory.getCurrentSession();
		AioCheckOutALL obj = new AioCheckOutALL();
		
		Date date = Calendar.getInstance().getTime();  
		DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd hh:mm:ss");  
		String strDate = dateFormat.format(date);  
		obj.setMerchantTradeNo(id);
		obj.setMerchantTradeDate(strDate);
		obj.setTotalAmount(pricetotal);
		obj.setTradeDesc("DriverPayment");
		obj.setItemName("司機接送");
		obj.setReturnURL("http://211.23.128.214:5000");
		obj.setClientBackURL("http://localhost:8080/SpringAllForOne/Test.jsp");
//		obj.setOrderResultURL("http://localhost:8080/SpringAllForOne/Test.jsp");
		System.out.println(id);
		obj.setNeedExtraPaidInfo("N");
		String form = all.aioCheckOut(obj, null);
//		Sponsor s = new Sponsor();
//		s.setClinicAccount(clinicAccount);
//		s.setTradeID(id);
//		s.setSponsorTime(strDate);
//		session.save(s);
		return form;
		
	}
}
