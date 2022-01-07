package service;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import commons.DBUtil;
import dao.NoticeDao;
import vo.Notice;

public class NoticeService {
	private NoticeDao noticeDao;
	public List<Notice> getNoticeList5() {
		List<Notice> list = null;
		Connection conn = null;
		
		try {
			conn = DBUtil.getConnection("jdbc:mariadb://13.125.182.110:3306/todo", "root", "java1004");
			noticeDao = new NoticeDao();
			list = noticeDao.selectNoticeList5(conn);
		} catch(Exception e) {
			e.printStackTrace();
		} finally {
			try {
				conn.close();
			} catch(SQLException e) {
				e.printStackTrace();
			}
		}
		return list;
	}
}
