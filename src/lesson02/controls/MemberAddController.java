package lesson02.controls;

import java.util.HashMap;

import lesson02.annotation.Component;
import lesson02.bind.DataBinding;
import lesson02.dao.MemberDao;
import lesson02.vo.Member;

@Component("/member/add.do")
public class MemberAddController implements Controller, DataBinding {
	MemberDao memberDao;
	
	public MemberAddController setMemberDao(MemberDao memberDao) {
		this.memberDao = memberDao;
		return this;
	}
	
	@Override
	public Object[] getDataBinders() {
		return new Object[] {
				"member", lesson02.vo.Member.class
		};
	}
	
	@Override
	public String excute(HashMap<String, Object> model) throws Exception {
		Member member = (Member)model.get("member");
		
		if (member.getEmail() == null) {
			return "/member/MemberAdd.jsp";
		} else {
			memberDao.insert(member);
			return "redirect:list.do";
		}
	}
}
