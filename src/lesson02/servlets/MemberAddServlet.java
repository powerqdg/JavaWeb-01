package lesson02.servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/member/add")
public class MemberAddServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html;charset=UTF-8");
		PrintWriter out = response.getWriter();
		out.println("<html><head><title>회원등록</title>");
		out.println("<body>");
		out.println("<h1>회원등록</h1>");
		out.println("<form action='add' method='post'>");
		out.println("이름: " + "<input type='text' name='mname'>" + "<br>");
		out.println("이메일: " + "<input type='text' name='email'>" + "<br>");
		out.println("비밀번호: " + "<input type='text' name='password'>" + "<br>");
		out.println("<input type='submit' value='등록'><input type='reset' value='취소'>");
		out.println("</form>");
		out.println("</body></html>");
	}
	
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		
		try {
			DriverManager.registerDriver(new oracle.jdbc.driver.OracleDriver());
			conn = DriverManager.getConnection(
					"jdbc:oracle:thin:@localhost:1521:orcl", 
					"scott", 
					"tiger");
			stmt = conn.prepareStatement("INSERT INTO MEMBERS VALUES(MNO_SEQ.nextVal, ?, ?, ?, SYSDATE, SYSDATE)");
			stmt.setString(1, request.getParameter("email"));
			stmt.setString(2, request.getParameter("password"));
			stmt.setString(3, request.getParameter("mname"));
			stmt.executeUpdate();
			
			response.setContentType("text/html;charset=UTF-8");
			PrintWriter out = response.getWriter();
			out.println("<html><head><title>회원결과</title>");
			out.println("<body>");
			out.println("<h1>회원결과</h1>");
			out.println("<p>등록에 성공하였습니다.</p>");
			out.println("</body></html>");
			
			response.setHeader("Refresh", "1;url=list");
		} catch (Exception e) {
			new ServletException(e);
		} finally {
			try { if (rs != null) rs.close(); } catch (SQLException e) {}
			try { if (stmt != null) stmt.close(); } catch (SQLException e) {}
			try { if (conn != null) conn.close(); } catch (SQLException e) {}
		}
	}
}
