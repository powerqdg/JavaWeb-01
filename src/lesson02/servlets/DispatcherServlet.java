package lesson02.servlets;

import java.io.IOException;
import java.util.HashMap;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import lesson02.controls.Controller;
import lesson02.controls.LoginController;
import lesson02.controls.LogoutController;
import lesson02.controls.MemberAddController;
import lesson02.controls.MemberDeleteController;
import lesson02.controls.MemberListController;
import lesson02.controls.MemberUpdateController;
import lesson02.dao.MemberDao;
import lesson02.vo.Member;

@WebServlet("*.do")
public class DispatcherServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	@Override
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html;charset=UTF-8");
		String servletPath = request.getServletPath();
		
		try {
			ServletContext sc = this.getServletContext();
			MemberDao memberDao = (MemberDao)sc.getAttribute("memberDao");
			
			Controller pageController = null;
			
			HashMap<String, Object> model = new HashMap<String, Object>();
			model.put("memberDao", memberDao);
			model.put("session", request.getSession());
			
			if ("/member/add.do".equals(servletPath)) {
				pageController = new MemberAddController();
				if (request.getParameter("email") != null) {
					model.put("member", new Member()
							.setEmail(request.getParameter("email"))
							.setMname(request.getParameter("mname"))
							.setPassword(request.getParameter("password")));
				}
			} else if ("/member/list.do".equals(servletPath)) {
				pageController = new MemberListController();
			} else if ("/member/delete.do".equals(servletPath)) {
				pageController = new MemberDeleteController();
				model.put("mno", Integer.parseInt(request.getParameter("mno")));
			} else if ("/member/update.do".equals(servletPath)) {
				pageController = new MemberUpdateController();
				if (request.getParameter("email") == null) {
					model.put("mno", Integer.parseInt(request.getParameter("mno")));
				} else {
					model.put("member", new Member()
							.setMno(Integer.parseInt(request.getParameter("mno")))
							.setEmail(request.getParameter("email"))
							.setMname(request.getParameter("mname")));
				}
			} else if ("/auth/login.do".equals(servletPath)) {
				pageController = new LoginController();
				if (request.getParameter("email") != null) {
					model.put("loginInfo", new Member()
							.setEmail(request.getParameter("email"))
							.setPassword(request.getParameter("password")));
				}
			} else if ("/auth/logout.do".equals(servletPath)) {
				pageController = new LogoutController();
			}
			
			String viewUrl = pageController.excute(model);
			
			for (String key : model.keySet()) {
				request.setAttribute(key, model.get(key));
			}
			
			if (viewUrl.startsWith("redirect:")) {
				response.sendRedirect(viewUrl.substring(9));
			} else {
				RequestDispatcher rd = request.getRequestDispatcher(viewUrl);
				rd.include(request, response);
			}
		} catch (Exception e) {
			e.printStackTrace();
			RequestDispatcher rd = request.getRequestDispatcher("/Error.jsp");
			rd.forward(request, response);
		}
		
	}
}
