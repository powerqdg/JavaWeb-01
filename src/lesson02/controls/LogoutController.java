package lesson02.controls;

import java.util.HashMap;

import javax.servlet.http.HttpSession;

public class LogoutController implements Controller {
	@Override
	public String excute(HashMap<String, Object> model) throws Exception {
		HttpSession session = (HttpSession)model.get("session");
		session.invalidate();
		return "redirect:login.do";
	}
}
