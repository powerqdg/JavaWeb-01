package lesson02.controls;

import java.util.HashMap;

import lesson02.annotation.Component;
import lesson02.bind.DataBinding;
import lesson02.dao.MemberDao;

@Component("/member/delete.do")
public class MemberDeleteController implements Controller, DataBinding {
	MemberDao memberDao;
	
	public MemberDeleteController setMemberDao(MemberDao memberDao) {
		this.memberDao = memberDao;
		return this;
	}
	
	@Override
	public Object[] getDataBinders() {
		return new Object[] {
				"mno", Integer.class
		};
	}
	
	@Override
	public String excute(HashMap<String, Object> model) throws Exception {
		int mno = (int)model.get("mno");
		memberDao.delete(mno);
		
		return "redirect:list.do";
	}
}
