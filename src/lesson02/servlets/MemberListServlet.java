package lesson02.servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.servlet.GenericServlet;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebServlet;

@WebServlet("/member/list")
public class MemberListServlet extends GenericServlet {
	private static final long serialVersionUID = 1L;

	@Override
	public void service(ServletRequest request, ServletResponse response) throws ServletException, IOException {
		Connection conn = null;
		Statement stmt = null;
		ResultSet rs = null;
		
		try {
			DriverManager.registerDriver(new oracle.jdbc.driver.OracleDriver());
			conn = DriverManager.getConnection(
					"jdbc:oracle:thin:@localhost:1521:orcl", 
					"scott", 
					"tiger");
			stmt = conn.createStatement();
			rs = stmt.executeQuery("SELECT MNO, EMAIL, MNAME, CRE_DATE, MOD_DATE FROM MEMBERS ORDER BY MNO");
			
			response.setContentType("text/html;charset=UTF-8");
			PrintWriter out = response.getWriter();
			out.println("<html><head><title>회원목록</title>");
			out.println("<body>");
			out.println("<h1>회원목록</h1>");
			out.println("<a href='add'>신규추가</a>" + "<br>");
			while (rs.next()) {
				out.println(rs.getInt("MNO") + ", " + 
							rs.getString("EMAIL") + ", " + 
							rs.getString("MNAME") + ", " +
							rs.getDate("CRE_DATE") + ", " +
							rs.getDate("MOD_DATE") + "<br>"); 
			}
			out.println("</body></html>");
		} catch (Exception e) {
			new ServletException(e);
		} finally {
			try { if (rs != null) rs.close(); } catch (SQLException e) {}
			try { if (stmt != null) stmt.close(); } catch (SQLException e) {}
			try { if (conn != null) conn.close(); } catch (SQLException e) {}
		}
	}

}
