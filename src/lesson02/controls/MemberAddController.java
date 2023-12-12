package lesson02.controls;

import java.util.HashMap;

import lesson02.dao.MemberDao;
import lesson02.vo.Member;

public class MemberAddController implements Controller {
	MemberDao memberDao;
	
	public MemberAddController setMemberDao(MemberDao memberDao) {
		this.memberDao = memberDao;
		return this;
	}
	
	@Override
	public String excute(HashMap<String, Object> model) throws Exception {
		Member member = (Member)model.get("member");
		
		if (member == null) {
			return "/member/MemberAdd.jsp";
		} else {
			memberDao.insert(member);
			return "redirect:list.do";
		}
	}
}
