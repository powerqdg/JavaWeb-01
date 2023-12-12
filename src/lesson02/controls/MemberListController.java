package lesson02.controls;

import java.util.HashMap;

import lesson02.dao.MemberDao;

public class MemberListController implements Controller {
	MemberDao memberDao;
	
	public MemberListController setMemberDao(MemberDao memberDao) {
		this.memberDao = memberDao;
		return this;
	}
	
	@Override
	public String excute(HashMap<String, Object> model) throws Exception {
		model.put("members", memberDao.selectList());
		return "/member/MemberList.jsp";
	}
}
