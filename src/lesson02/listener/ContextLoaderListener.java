package lesson02.listener;

import javax.naming.InitialContext;
import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.sql.DataSource;

import lesson02.controls.LoginController;
import lesson02.controls.LogoutController;
import lesson02.controls.MemberAddController;
import lesson02.controls.MemberDeleteController;
import lesson02.controls.MemberListController;
import lesson02.controls.MemberUpdateController;
import lesson02.dao.MemberDao;

public class ContextLoaderListener implements ServletContextListener {
	@Override
	public void contextInitialized(ServletContextEvent sce) {
		try {
			ServletContext sc = sce.getServletContext();
			
			InitialContext initialContext = new InitialContext();
			DataSource ds = (DataSource)initialContext.lookup("java:comp/env/jdbc/orcl");
			
			MemberDao memberDao = new MemberDao();
			memberDao.setDataSource(ds);
			
			sc.setAttribute("memberDao", memberDao);
			
			sc.setAttribute("/auth/login.do", 
					new LoginController().setMemberDao(memberDao));
			sc.setAttribute("/auth/logout.do", 
					new LogoutController());
			sc.setAttribute("/member/add.do", 
					new MemberAddController().setMemberDao(memberDao));
			sc.setAttribute("/member/delete.do", 
					new MemberDeleteController().setMemberDao(memberDao));
			sc.setAttribute("/member/list.do", 
					new MemberListController().setMemberDao(memberDao));
			sc.setAttribute("/member/update.do", 
					new MemberUpdateController().setMemberDao(memberDao));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	@Override
	public void contextDestroyed(ServletContextEvent sce) {}
}
