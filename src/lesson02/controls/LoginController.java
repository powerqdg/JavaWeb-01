package lesson02.controls;

import java.util.HashMap;

import javax.servlet.http.HttpSession;

import lesson02.annotation.Component;
import lesson02.bind.DataBinding;
import lesson02.dao.MemberDao;
import lesson02.vo.Member;

@Component("/auth/login")
public class LoginController implements Controller, DataBinding {
	MemberDao memberDao;
	
	public LoginController setMemberDao(MemberDao memberDao) {
		this.memberDao = memberDao;
		return this;
	}
	
	@Override
	public Object[] getDataBinders() {
		return new Object[] {
				"loginInfo", lesson02.vo.Member.class
		};
	}
	
	@Override
	public String excute(HashMap<String, Object> model) throws Exception {
		Member loginInfo = (Member)model.get("loginInfo");
		if (loginInfo.getEmail() == null) {
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
