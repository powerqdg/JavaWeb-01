package lesson02.controls;

import java.util.HashMap;

import javax.servlet.http.HttpSession;

import lesson02.dao.MemberDao;
import lesson02.vo.Member;

public class LoginController implements Controller {
	MemberDao memberDao;
	
	public LoginController setMemberDao(MemberDao memberDao) {
		this.memberDao = memberDao;
		return this;
	}
	
	@Override
	public String excute(HashMap<String, Object> model) throws Exception {
		Member loginInfo = (Member)model.get("loginInfo");
		if (loginInfo == null) {
			return "/auth/LoginForm.jsp";
		} else {
			Member member = memberDao.exist(loginInfo);
			if (member == null) {
				return "/auth/LoginFail.jsp";
			} else {
				HttpSession session = (HttpSession)model.get("session");
				session.setAttribute("loginInfo", member);
				return "redirect:../member/list.do";
			}
		}
	}
}
