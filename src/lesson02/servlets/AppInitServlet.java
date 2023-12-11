package lesson02.servlets;

import java.sql.Connection;
import java.sql.DriverManager;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;

public class AppInitServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	Connection conn = null;
	
	@Override
	public void init() throws ServletException {
		super.init();
		
		try {
			ServletContext sc = this.getServletContext();
			Class.forName(sc.getInitParameter("driver"));
			conn = DriverManager.getConnection(
					sc.getInitParameter("url"), 
					sc.getInitParameter("username"), 
					sc.getInitParameter("password"));
			sc.setAttribute("conn", conn);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	@Override
	public void destroy() {
		super.destroy();
		
		try {
			if (conn != null) conn.close();
		} catch (Exception e) {}
	}
}
