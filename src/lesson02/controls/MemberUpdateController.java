package lesson02.controls;

import java.util.HashMap;

import lesson02.dao.MemberDao;
import lesson02.vo.Member;

public class MemberUpdateController implements Controller {
	MemberDao memberDao;
	
	public MemberUpdateController setMemberDao(MemberDao memberDao) {
		this.memberDao = memberDao;
		return this;
	}
	
	@Override
	public String excute(HashMap<String, Object> model) throws Exception {
		Member member = (Member)model.get("member");
		
		if (member == null) {
			int mno = (int)model.get("mno");
			model.put("memberDetail", memberDao.selectOne(mno));
			return "/member/MemberUpdate.jsp";
		} else {
			memberDao.update(member);
			return "redirect:list.do";
		}
	}
}
