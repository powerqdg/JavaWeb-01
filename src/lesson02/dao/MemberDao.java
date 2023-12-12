package lesson02.dao;

import java.util.ArrayList;

import lesson02.vo.Member;

public interface MemberDao {
	int insert(Member member) throws Exception;
	ArrayList<Member> selectList() throws Exception;
	Member selectOne(int mno) throws Exception;
	int update(Member member) throws Exception;
	int delete(int mno) throws Exception;
	Member exist(Member member) throws Exception;
}
