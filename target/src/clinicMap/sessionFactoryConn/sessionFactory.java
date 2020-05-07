package clinicMap.sessionFactoryConn;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

public class sessionFactory implements Filter {
		private SessionFactory sessionFactory;
		private WebApplicationContext context;
	@Override
	public void doFilter(ServletRequest arg0, ServletResponse arg1, FilterChain arg2)
			throws IOException, ServletException {
		Session session = sessionFactory.getCurrentSession();
		try {
			
			session.beginTransaction();
			System.out.println("transaction start");
			arg2.doFilter(arg0, arg1);
			session.getTransaction().commit();
		}catch(Exception e) {
			System.out.println(e);
			session.getTransaction().rollback();
		}finally {
			System.out.println("transaction closed");
		}
	}
	@Override
	public void destroy() {
		((ConfigurableApplicationContext)context).close();

	}
	@Override
	public void init(FilterConfig config) throws ServletException {
		String sessionFactoryBeanName = config.getInitParameter("sessionFactoryBeanName");
		ServletContext application = config.getServletContext();
		context=WebApplicationContextUtils.getWebApplicationContext(application);
		sessionFactory=(SessionFactory)context.getBean(sessionFactoryBeanName);
	}

}
