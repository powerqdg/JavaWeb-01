package lesson02.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import javax.sql.DataSource;

import lesson02.vo.Member;

public class OracleMemberDao implements MemberDao {
	DataSource ds = null;
	
	public void setDataSource(DataSource ds) {
		this.ds = ds;
	}
	
	@Override
	public int insert(Member member) throws Exception {
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		
		try {
			conn = ds.getConnection();
			stmt = conn.prepareStatement("INSERT INTO MEMBERS VALUES(MNO_SEQ.nextVal, ?, ?, ?, SYSDATE, SYSDATE)");
			stmt.setString(1, member.getEmail());
			stmt.setString(2, member.getPassword());
			stmt.setString(3, member.getMname());
			
			return stmt.executeUpdate();
		} catch (Exception e) {
			throw e;
		} finally {
			try { if (rs != null) rs.close(); } catch (SQLException e) {}
			try { if (stmt != null) stmt.close(); } catch (SQLException e) {}
			try { if (conn != null) conn.close(); } catch (SQLException e) {}
		}
	}
	
	@Override
	public ArrayList<Member> selectList() throws Exception {
		Connection conn = null;
		Statement stmt = null;
		ResultSet rs = null;
		
		try {
			conn = ds.getConnection();
			stmt = conn.createStatement();
			rs = stmt.executeQuery("SELECT MNO, EMAIL, MNAME, CRE_DATE, MOD_DATE FROM MEMBERS ORDER BY MNO");
			
			ArrayList<Member> members = new ArrayList<Member>();
			
			while (rs.next()) {
				members.add(new Member()
						.setMno(rs.getInt("MNO"))
						.setEmail(rs.getString("EMAIL"))
						.setMname(rs.getString("MNAME"))
						.setCreDate(rs.getDate("CRE_DATE"))
						.setModDate(rs.getDate("MOD_DATE")));
			}
			return members;
		} catch (Exception e) {
			throw e;
		} finally {
			try { if (rs != null) rs.close(); } catch (SQLException e) {}
			try { if (stmt != null) stmt.close(); } catch (SQLException e) {}
			try { if (conn != null) conn.close(); } catch (SQLException e) {}
		}
	}
	
	@Override
	public Member selectOne(int mno) throws Exception {
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		
		try {
			conn = ds.getConnection();
			stmt = conn.prepareStatement("SELECT MNO, MNAME, EMAIL, CRE_DATE, MOD_DATE FROM MEMBERS WHERE MNO = ?");
			stmt.setInt(1, mno);
			rs = stmt.executeQuery();
			rs.next();
			
			return new Member()
					.setMno(rs.getInt("MNO"))
					.setMname(rs.getString("MNAME"))
					.setEmail(rs.getString("EMAIL"))
					.setCreDate(rs.getDate("CRE_DATE"))
					.setModDate(rs.getDate("MOD_DATE"));
		} catch (Exception e) {
			throw e;
		} finally {
			try { if (rs != null) rs.close(); } catch (SQLException e) {}
			try { if (stmt != null) stmt.close(); } catch (SQLException e) {}
			try { if (conn != null) conn.close(); } catch (SQLException e) {}
		}
	}
	
	@Override
	public int update(Member member) throws Exception {
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		
		try {
			conn = ds.getConnection();
			stmt = conn.prepareStatement("UPDATE MEMBERS SET MNAME = ?, EMAIL = ? WHERE MNO = ?");
			stmt.setString(1, member.getMname());
			stmt.setString(2, member.getEmail());
			stmt.setInt(3, member.getMno());
			
			return stmt.executeUpdate();
		} catch (Exception e) {
			throw e;
		} finally {
			try { if (rs != null) rs.close(); } catch (SQLException e) {}
			try { if (stmt != null) stmt.close(); } catch (SQLException e) {}
			try { if (conn != null) conn.close(); } catch (SQLException e) {}
		}
	}
	
	@Override
	public int delete(int mno) throws Exception {
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		
		try {
			conn = ds.getConnection();
			stmt = conn.prepareStatement("DELETE FROM MEMBERS WHERE MNO = ?");
			stmt.setInt(1, mno);
			
			return stmt.executeUpdate();
		} catch (Exception e) {
			throw e;
		} finally {
			try { if (rs != null) rs.close(); } catch (SQLException e) {}
			try { if (stmt != null) stmt.close(); } catch (SQLException e) {}
			try { if (conn != null) conn.close(); } catch (SQLException e) {}
		}
	}
	
	@Override
	public Member exist(Member member) throws Exception {
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		
		try {
			conn = ds.getConnection();
			stmt = conn.prepareStatement("SELECT MNO, MNAME, EMAIL FROM MEMBERS WHERE EMAIL = ? AND PWD = ?");
			stmt.setString(1, member.getEmail());
			stmt.setString(2, member.getPassword());
			rs = stmt.executeQuery();
			
			if (rs.next()) {
				return new Member()
						.setMname(rs.getString("MNAME"))
						.setEmail(rs.getString("EMAIL"));
			} else {
				return null;
			}
		} catch (Exception e) {
			throw e;
		} finally {
			try { if (rs != null) rs.close(); } catch (SQLException e) {}
			try { if (stmt != null) stmt.close(); } catch (SQLException e) {}
			try { if (conn != null) conn.close(); } catch (SQLException e) {}
		}
	}
}