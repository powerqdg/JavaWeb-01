package lesson02.servlets;

import java.io.IOException;
import java.util.HashMap;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import lesson02.bind.DataBinding;
import lesson02.bind.ServletRequestDataBinder;
import lesson02.context.ApplicationContext;
import lesson02.controls.Controller;
import lesson02.listener.ContextLoaderListener;

@WebServlet("*.do")
public class DispatcherServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	@Override
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html;charset=UTF-8");
		String servletPath = request.getServletPath();
		
		try {
			ApplicationContext ctx = ContextLoaderListener.getApplicationContext();
			
			HashMap<String, Object> model = new HashMap<String, Object>();
			model.put("session", request.getSession());
			
			Controller pageController = (Controller)ctx.getBean(servletPath);
			if (pageController instanceof DataBinding) {
				prepareRequestData(request, model, (DataBinding)pageController);
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
	
	private void prepareRequestData(ServletRequest request, HashMap<String, Object> model, DataBinding dataBinding) throws Exception {
		Object[] dataBinders = dataBinding.getDataBinders();
		String dataName = null;
		Class<?> dataType = null;
		Object dataObj = null;
		for (int i = 0; i < dataBinders.length; i += 2) {
			dataName = (String)dataBinders[i];
			dataType = (Class<?>)dataBinders[i + 1];
			dataObj = ServletRequestDataBinder.bind(request, dataType, dataName);
			model.put(dataName, dataObj);
		}
	}
}
