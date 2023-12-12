package lesson02.servlets;

import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import javax.servlet.GenericServlet;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebServlet;

import lesson02.vo.Member;

@WebServlet("/member/list")
public class MemberListServlet extends GenericServlet {
	private static final long serialVersionUID = 1L;

	@Override
	public void service(ServletRequest request, ServletResponse response) throws ServletException, IOException {
		Statement stmt = null;
		ResultSet rs = null;
		
		try {
			ServletContext sc = this.getServletContext();
			Connection conn = (Connection)sc.getAttribute("conn");
			stmt = conn.createStatement();
			rs = stmt.executeQuery("SELECT MNO, EMAIL, MNAME, CRE_DATE, MOD_DATE FROM MEMBERS ORDER BY MNO");
			
			ArrayList<Member> members = new ArrayList<Member>();
			
			while (rs.next()) {
				members.add(new Member()
						.setMno(rs.getInt("MNO"))
						.setEmail(rs.getString("EMAIL"))
						.setMname(rs.getString("MNAME"))
						.setCreDate(rs.getDate("CRE_DATE"))
						.setModDate(rs.getDate("MOD_DATE")));
			}
			request.setAttribute("members", members);
			response.setContentType("text/html;charset=UTF-8");
			RequestDispatcher rd = request.getRequestDispatcher("/member/MemberList.jsp");
			rd.include(request, response);
		} catch (Exception e) {
			new ServletException(e);
		} finally {
			try { if (rs != null) rs.close(); } catch (SQLException e) {}
			try { if (stmt != null) stmt.close(); } catch (SQLException e) {}
		}
	}

}
