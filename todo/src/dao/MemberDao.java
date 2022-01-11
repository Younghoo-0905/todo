package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import vo.Member;

public class MemberDao {
	
	//	로그인
	public Member login(Connection conn, Member member) throws SQLException {
		Member loginMember = null;
		String sql = MemberQuery.LOGIN;
		PreparedStatement stmt = conn.prepareStatement(sql);
		stmt.setString(1, member.getMemberId());
		stmt.setString(2, member.getMemberPw());
		ResultSet rs = stmt.executeQuery();
		if(rs.next()) {
			loginMember = new Member();
			loginMember.setMemberId(rs.getString("memberId"));
		}
		rs.close();
		stmt.close();
		
		return loginMember;
	}
	
	//	회원가입
	public void insertMember(Connection conn, Member member) throws SQLException {
		String sql = MemberQuery.INSERT_MEMBER;
		PreparedStatement stmt = conn.prepareStatement(sql);
		stmt.setString(1, member.getMemberId());
		stmt.setString(2, member.getMemberPw());
		stmt.executeQuery();
		stmt.close();
	}	
	
	//	회원탈퇴
	public int deleteMember(Connection conn, String memberId, String memberPw) throws SQLException  {
		String sql = MemberQuery.DELETE_MEMBER;
		PreparedStatement stmt = conn.prepareStatement(sql);
		stmt.setString(1, memberId);
		stmt.setString(2, memberPw);
		int row = stmt.executeUpdate();
		stmt.close();		
		return row;
	}
}
