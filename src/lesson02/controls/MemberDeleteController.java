package lesson02.controls;

import java.util.HashMap;

import lesson02.dao.MemberDao;

public class MemberDeleteController implements Controller {
	MemberDao memberDao;
	
	public MemberDeleteController setMemberDao(MemberDao memberDao) {
		this.memberDao = memberDao;
		return this;
	}
	
	@Override
	public String excute(HashMap<String, Object> model) throws Exception {
		int mno = (int)model.get("mno");
		memberDao.delete(mno);
		
		return "redirect:list.do";
	}
}
