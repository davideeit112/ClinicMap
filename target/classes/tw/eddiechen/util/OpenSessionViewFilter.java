package tw.eddiechen.util;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

import org.hibernate.SessionFactory;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

public class OpenSessionViewFilter implements Filter {
	private SessionFactory sessionFactory = null;
	
	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
		String sessionFactoryBeanName = filterConfig.getInitParameter("sessionFactoryBeanName");
		ServletContext application = filterConfig.getServletContext();
		WebApplicationContext context = WebApplicationContextUtils.getWebApplicationContext(application);
		sessionFactory = (SessionFactory)context.getBean(sessionFactoryBeanName);
	
	}
	


	
	@Override
	public void doFilter(ServletRequest arg0, ServletResponse arg1, FilterChain arg2)
			throws IOException, ServletException {
		try {
			sessionFactory.getCurrentSession().beginTransaction();
			System.out.println("Begin Transaction");
			arg2.doFilter(arg0, arg1);
			sessionFactory.getCurrentSession().getTransaction().commit();
			System.out.println("Transaction Commit");
					
		}catch(Exception e) {
			sessionFactory.getCurrentSession().getTransaction().rollback();
			System.out.println("Transaction Rollback");
			e.printStackTrace();
			arg2.doFilter(arg0, arg1);
		}finally {
			System.out.println("Session closed");
		}
		
		
	}
	
	
	public void destroy() {
		
	}
}
