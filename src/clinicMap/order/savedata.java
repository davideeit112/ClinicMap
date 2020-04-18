package clinicMap.order;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;

import org.apache.commons.mail.DefaultAuthenticator;
import org.apache.commons.mail.HtmlEmail;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;

@Repository
public class savedata {
	private SessionFactory sessionfactory;

	@Autowired
	public savedata(@Qualifier("sessionFactory") SessionFactory sessionfactory) {
		this.sessionfactory = sessionfactory;
	}

	public clinicBean savedate() throws IOException {
		File file = new File("/Users/apple/Documents/clinic.csv");
		InputStreamReader read = new InputStreamReader(new FileInputStream(file), "UTF-8");
		BufferedReader brs = new BufferedReader(read);
		List<String[]> list = new LinkedList<String[]>();

		String s;
		brs.readLine();
		
		while ((s = brs.readLine()) != null) {
			String[] j = s.split("\"");
			if (j.length < 2) {
				list.add(j[0].split(","));
			} else if (j.length > 3) {
				continue;

			}

			else {
				String sr = j[1].replaceAll(",", " ");
				String ss = j[0] + sr;
				list.add(ss.split(","));
			}

		}
		Session session = sessionfactory.getCurrentSession();
		int j = 0;
		clinicBean bcbean = new clinicBean();
		for (String[] i : list) {
			if(i[6].indexOf("臺北市大安")!=-1 ||i[6].indexOf("臺北市中正")!=-1) {
			clinicBean cbean = new clinicBean();

			cbean.setClinicAccount(i[0]);
			cbean.setClinicName(i[1]);
			cbean.setClinicClass(i[3]);
			cbean.setClinicphone(i[5]);
			cbean.setClinicAddress(i[6]);
			cbean.setClinicPwd(i[0] + "" + (int) (Math.random() * 9));
			cbean.setClinicStatus("CS0");
			cbean.setClinicType(i[7]);
			
			System.out.println(i[0]+" "+i[1]+" "+i[3]+" "+i[5]+" "+i[6]+" "+i[7]);
			session.save(cbean);
			System.out.println(j++);
			}

		}

		read.close();
		brs.close();
		return bcbean;

	}

	public List<clinicBean> query() {
		Session session = sessionfactory.getCurrentSession();
		System.out.println("hi3");
		Query<clinicBean> query = session.createQuery("from clinicBean", clinicBean.class);
		System.out.println("h4");
		List<clinicBean> list = query.list();
		System.out.println(list.size());
		return list;
	}

	public int order(String clinicid, String memberid, String description) {
		orderbean obean = new orderbean();
		java.util.Date date = new Date();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddhhmmss");
		Session session = sessionfactory.getCurrentSession();
		System.out.println(clinicid + " " + memberid);
		obean.setAppointmentID(clinicid + "" + sdf.format(date));
		System.out.println(clinicid + "" + sdf.format(date));
		obean.setClinicID(Integer.parseInt(clinicid));
		obean.setMemberID(Integer.parseInt(memberid));
		obean.setAppointmentType("OT2");
		obean.setAppointmentSickDescription(description);
		System.out.println(description);
		SimpleDateFormat sdf2 = new SimpleDateFormat("yyyy/MM/dd-hh:mm:ss");
		String st = sdf2.format(date);

		java.util.Date date2;
		try {
			date2 = sdf2.parse(st);
			Timestamp tion = new Timestamp(date2.getTime());

			obean.setAppointmentTime(tion);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		obean.setAppointmentStatus("OS1");
		int number = querynumber(clinicid, memberid);
		obean.setAppointmentNumber(number + 1);
		session.save(obean);

		return number + 1;
	}

	@SuppressWarnings("null")
	public int querynumber(String clinicid, String memberid) {
		java.util.Date date = new Date();
		Session session = sessionfactory.getCurrentSession();
		List<orderbean> list = null;
		try {
			Query<orderbean> query = session.createQuery(
					"from orderbean where clinicid=:clinicid and appointmentTime>:appointmentTime", orderbean.class);
			// where clinicid=:clinicid and appointmentTime >:time appointmentTime <:time2
			query.setParameter("clinicid", clinicid);
			SimpleDateFormat sdf3 = new SimpleDateFormat("yyyyMMdd");
			String today = sdf3.format(date.getTime());
			Date todate = sdf3.parse(today);
			System.out.println("today:" + today);
			query.setParameter("appointmentTime", todate);
//		query.setParameter(3,tomorrow);
			
			list = query.list();
			sendemail(clinicid, memberid,list.size());
		} catch (Exception e) {
			e.printStackTrace();
		}

		return list.size();
	}

	public int peoplewait(String clinicid) {
		java.util.Date date = new Date();
		Session session = sessionfactory.getCurrentSession();
		Query<orderbean> query = session.createQuery(
				"from orderbean where clinicid=:clinicid and appointmentTime>:appointmentTime and appointmentStatus= 'OS1'",
				orderbean.class);
		query.setParameter("clinicid", clinicid);
		SimpleDateFormat sdf3 = new SimpleDateFormat("yyyyMMdd");
		String today = sdf3.format(date.getTime());
		List<orderbean> list = null;
		try {
			Date todate = sdf3.parse(today);
			query.setParameter("appointmentTime", todate);
			list = query.list();
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return list.size();

	}

	public String sendemail(String clinicid, String memberid, int size) {
		HtmlEmail email = new HtmlEmail();
		int clinicidint=Integer.parseInt(clinicid);
		int memberidint=Integer.parseInt(memberid);
		System.out.println("會員ＩＤ："+memberidint);
		Session session = sessionfactory.getCurrentSession();
		Query<memberBean> query = session.createQuery("from memberBean where memberID=:memberid ", memberBean.class);
		query.setParameter("memberid", memberidint);
		List<memberBean> list = query.list();
		
		Query<clinicBean> query1 = session.createQuery("from clinicBean where clinicid=:clinicid", clinicBean.class);
		query1.setParameter("clinicid", clinicidint);
		List<clinicBean> list1 = query1.list();
		
		
		String membername = "";
		String memberemail="";

		for (memberBean member : list) {
			membername = ("親愛的會員" + member.getmemberName() + "你好:<br>");
			memberemail=member.getMemberemail();
		}
		for (clinicBean clinic : list1) {
			membername += ("你已成功預約" + clinic.getClinicName() + "你的預約號碼為:");
		}

	
			membername += size+1 + "";
		try {
//		for (memberBean i:list) {
			email.setTLS(true);
//			email.setSSL(true);
			email.setHostName("smtp.gmail.com");
			email.setAuthenticator(new DefaultAuthenticator("clinicofmap", "clinicmapemail"));
			email.setFrom("clinicofmap@gmail.com", "系統自動發送");
			email.setSmtpPort(587);
			email.setCharset("UTF-8");
			email.addTo(memberemail);
			email.setSubject("預約成功通知");
			email.setHtmlMsg(membername);

			email.send();

//		}
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		return "finish";
	}

	public int insertlnglat(String[] list) {
		Session session = sessionfactory.getCurrentSession();
		for (int i=0 ;i<list.length;i+=3) {
			clinicBean cbean = session.get(clinicBean.class,Integer.parseInt(list[i]));
			cbean.setCliniclat(list[i+1]);
			cbean.setCliniclan(list[i+2]);
			session.save(cbean);
		}
		
		return 0;
	}

	public int deleteorder(String AppointmentID) {
		Session session = sessionfactory.getCurrentSession();
		System.out.println("hello");
		Query<orderbean> query = session.createQuery("from orderbean where AppointmentID like :AppointmentID",orderbean.class);
		query.setParameter("AppointmentID", AppointmentID+"%");
		List<orderbean> list = query.list();

		for(orderbean i:list) {
			i.setAppointmentStatus("OS2");
			session.save(i);
		}
		
		
		System.out.println("finished");
		return 0;
	}

	public memberBean memberquery(int memberid) {
		Session session = sessionfactory.getCurrentSession();
		memberBean member = session.get(memberBean.class, memberid);
		return member;
	}
	public List<orderbean> orderquery(int memberid) {
		Session session = sessionfactory.getCurrentSession();
		Query<orderbean> query = session.createQuery("from orderbean where memberid=:memberid",orderbean.class);
		query.setParameter("memberid", memberid);
		List<orderbean> list = query.list();
		return list;
	}

	public clinicBean clinicquery(int clinicid) {
		Session session = sessionfactory.getCurrentSession();
		clinicBean cbean = session.get(clinicBean.class, clinicid);
		return cbean;
	}

	public memberBean insertmember(int memberid, String memberemail, String memberPwd, int memberHeight,
			int memberWeight, String memberAddress, String memberPhone) {
		Session session = sessionfactory.getCurrentSession();
		memberBean mbean = session.get(memberBean.class, memberid);
		mbean.setMemberemail(memberemail);
		mbean.setMemberPwd(memberPwd);
		mbean.setMemberHeight(memberHeight);
		mbean.setMemberWeight(memberWeight);
		mbean.setMemberAddress(memberAddress);
		mbean.setMemberPhone(memberPhone);
		session.save(mbean);
		return mbean;
	}

	public memberBean photoupload(int memberId, String savepath) throws IOException {
		Session session = sessionfactory.getCurrentSession();
		memberBean mbean = session.get(memberBean.class, memberId);
		InputStream is1 = new FileInputStream(savepath);
		byte[] photo=new byte[is1.available()];
		is1.read(photo);
		is1.close();
		mbean.setmemberPhoto(photo);
		session.save(mbean);
		return mbean;
	}
	

}
