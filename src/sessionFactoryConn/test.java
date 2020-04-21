package clinicMap.sessionFactoryConn;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import clinicMap.order.clinicBean;

@Controller
public class test {

		private SessionFactory sessionfactory;

		@Autowired
		public test(@Qualifier("sessionFactory") SessionFactory sessionfactory) {
			this.sessionfactory = sessionfactory;}
		
	@RequestMapping(path = "/txtorder",method = RequestMethod.GET)
	public void txt() throws IOException {
	Session session = sessionfactory.getCurrentSession();
	Query<clinicBean> query = session.createQuery("from clinicBean",clinicBean.class);
	List<clinicBean> list = query.list();
	File file= new File("C:\\DataSource\\springWorkSpace\\sql.txt");
	StringBuilder sbr=new StringBuilder();
	for (clinicBean cbean:list) {
		sbr.append("insert clinic(clinicName,clinicAccount,clinicPwd,clinicAddress,clinicPhone,"
				+"clinicClass,clinicType,cliniclat,cliniclng,clinicStatus)values"+
				"('"+cbean.getClinicName()+"','"+cbean.getClinicAccount()+"','"+cbean.getClinicPwd()
				+"','"+cbean.getClinicAddress()+"','"+cbean.getClinicphone()+"','"+cbean.getClinicClass()+"','"+cbean.getClinicType()
				+"','"+cbean.getCliniclat()+"','"+cbean.getCliniclan()+"','"+cbean.getClinicStatus()+"')\n");
	
	}
	
	BufferedWriter bfw=new BufferedWriter(new FileWriter(file));
	bfw.write(sbr.toString());
		bfw.close();
}
}
