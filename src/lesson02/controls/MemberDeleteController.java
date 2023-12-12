package lesson02.controls;

import java.util.HashMap;

import lesson02.dao.MemberDao;

public class MemberDeleteController implements Controller {
	@Override
	public String excute(HashMap<String, Object> model) throws Exception {
		MemberDao memberDao = (MemberDao)model.get("memberDao");
		
		int mno = (int)model.get("mno");
		memberDao.delete(mno);
		
		return "redirect:list.do";
	}
}
