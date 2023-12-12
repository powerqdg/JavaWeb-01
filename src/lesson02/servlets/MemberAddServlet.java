package lesson02.servlets;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
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
		RequestDispatcher rd = request.getRequestDispatcher("/member/MemberAdd.jsp");
		rd.forward(request, response);
	}
	
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		PreparedStatement stmt = null;
		ResultSet rs = null;
		
		try {
			ServletContext sc = this.getServletContext();
			Connection conn = (Connection)sc.getAttribute("conn");
			stmt = conn.prepareStatement("INSERT INTO MEMBERS VALUES(MNO_SEQ.nextVal, ?, ?, ?, SYSDATE, SYSDATE)");
			stmt.setString(1, request.getParameter("email"));
			stmt.setString(2, request.getParameter("password"));
			stmt.setString(3, request.getParameter("mname"));
			stmt.executeUpdate();
			
			response.sendRedirect("list");
		} catch (Exception e) {
			request.setAttribute("error", e);
			RequestDispatcher rd = request.getRequestDispatcher("../Error.jsp");
			rd.forward(request, response);
		} finally {
			try { if (rs != null) rs.close(); } catch (SQLException e) {}
			try { if (stmt != null) stmt.close(); } catch (SQLException e) {}
		}
	}
}
