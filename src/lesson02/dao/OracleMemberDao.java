package lesson02.dao;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;

import lesson02.annotation.Component;
import lesson02.vo.Member;

@Component("memberDao")
public class OracleMemberDao implements MemberDao {
	SqlSessionFactory sqlSessionFactory = null;
	 
	public void setSqlSessionFactory(SqlSessionFactory sqlSessionFactory) {
		this.sqlSessionFactory = sqlSessionFactory;
	}
	
	@Override
	public int insert(Member member) throws Exception {
		SqlSession sqlSession = sqlSessionFactory.openSession();
		try {
			int count = sqlSession.insert("lesson02.dao.MemberDao.insert", member);
			sqlSession.commit();
			return count;
		} finally {
			sqlSession.close();
		}
	}
	
	@Override
	public List<Member> selectList() throws Exception {
		SqlSession sqlSession = sqlSessionFactory.openSession();
		try {
			return sqlSession.selectList("lesson02.dao.MemberDao.selectList");
		} finally {
			sqlSession.close();
		}
	}
	
	@Override
	public Member selectOne(int mno) throws Exception {
		SqlSession sqlSession = sqlSessionFactory.openSession();
		try {
			return sqlSession.selectOne("lesson02.dao.MemberDao.selectOne", mno);
		} finally {
			sqlSession.close();
		}
	}
	
	@Override
	public int update(Member member) throws Exception {
		SqlSession sqlSession = sqlSessionFactory.openSession();
		try {
			int count = sqlSession.update("lesson02.dao.MemberDao.update", member);
			sqlSession.commit();
			return count;
		} finally {
			sqlSession.close();
		}
	}
	
	@Override
	public int delete(int mno) throws Exception {
		SqlSession sqlSession = sqlSessionFactory.openSession();
		try {
			int count = sqlSession.delete("lesson02.dao.MemberDao.delete", mno);
			sqlSession.commit();
			return count;
		} finally {
			sqlSession.close();
		}
	}
	
	@Override
	public Member exist(Member loginInfo) throws Exception {
		SqlSession sqlSession = sqlSessionFactory.openSession();
		try {
			return  sqlSession.selectOne("lesson02.dao.MemberDao.exist", loginInfo);
		} finally {
			sqlSession.close();
		}
	}
}
