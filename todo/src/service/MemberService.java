package service;

import java.sql.Connection;
import java.sql.SQLException;

import commons.DBUtil;
import dao.MemberDao;
import dao.TodoDao;
import vo.Member;

public class MemberService {
	private MemberDao memberDao;
	private TodoDao todoDao;
	
	public Member login(Member member) {
		Member loginMember = null;
		Connection conn = null;
		try {
			conn = DBUtil.getConnection("jdbc:mariadb://13.125.182.110/todo", "root", "java1004");
			memberDao = new MemberDao();
			loginMember = memberDao.login(conn, member);
		} catch(Exception e) {
			e.printStackTrace();
		} finally {
			try {
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return loginMember;
	}
	
	public boolean removeMember(String memberId, String memberPw) {
		boolean result = false;
		Connection conn = DBUtil.getConnection("jdbc:mariadb://127.0.01:3306/todo", "root", "java1004");
		try {
			conn.setAutoCommit(false);
			
			memberDao = new MemberDao();
			todoDao = new TodoDao();
			
			if(memberDao.deleteMember(conn, memberId, memberPw) == 1) {
				todoDao.deleteTodo(conn, memberId);
				result = true;			
				//	System.out.println(result);
			}
			
			conn.commit();
		} catch(Exception e) {
			e.printStackTrace();
			try {
				conn.rollback();
			} catch(SQLException e1) {
				e1.printStackTrace();
			}
		} finally {
			try {
				conn.close();
			} catch(SQLException e) {
				e.printStackTrace();
			}
		}
		return result;
	}
}
