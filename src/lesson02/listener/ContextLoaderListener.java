package lesson02.listener;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import lesson02.context.ApplicationContext;

public class ContextLoaderListener implements ServletContextListener {
	static ApplicationContext applicationContext;
	
	public static ApplicationContext getApplicationContext() {
		return applicationContext;
	}
	
	@Override
	public void contextInitialized(ServletContextEvent sce) {
		try {
			ServletContext sc = sce.getServletContext();
			
			String propertiesPath = sc.getRealPath(sc.getInitParameter("contextConfigLocation"));
			applicationContext = new ApplicationContext(propertiesPath);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	@Override
	public void contextDestroyed(ServletContextEvent sce) {}
}
