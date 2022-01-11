package service;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import commons.DBUtil;
import dao.TodoDao;
import vo.Todo;

public class TodoService {
	private TodoDao todoDao;
	
	//	월별 todo List 출력
	public List<Todo> getTodoListByMonth(Todo todo) {
		
		List<Todo> list = null;
		Connection conn = null;
		try {
			conn = DBUtil.getConnection("jdbc:mariadb://13.125.182.110/todo", "root", "java1004");
			todoDao = new TodoDao();
			list = todoDao.selectTodoListByMonth(conn, todo);
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
	
	//	일별 todo List 출력
	public List<Todo> getTodoListByDate(Todo todo) {
		
		List<Todo> list = null;		
		Connection conn = null;
		try {
			conn = DBUtil.getConnection("jdbc:mariadb://13.125.182.110/todo", "root", "java1004");
			todoDao = new TodoDao();
			list = todoDao.selectTodoListByDate(conn, todo);
		} catch(Exception e) {
			e.printStackTrace();
		} finally {
			try {
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return list;
	}
	
	//	todo 완료 상태변경
	public void updateTodoComplete(int todoNo) {
		Connection conn = null;
		try {
			conn = DBUtil.getConnection("jdbc:mariadb://13.125.182.110/todo", "root", "java1004");
			todoDao = new TodoDao();
			todoDao.updateTodoComplete(conn, todoNo);
		} catch(Exception e) {
			e.printStackTrace();
		} finally {
			try {
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}		
	}
	
	//	todo 삭제
	public void deleteTodoOne(int todoNo) {
		Connection conn = null;
		try {
			conn = DBUtil.getConnection("jdbc:mariadb://13.125.182.110/todo", "root", "java1004");
			todoDao = new TodoDao();
			todoDao.deleteTodoOne(conn, todoNo);
		} catch(Exception e) {
			e.printStackTrace();
		} finally {
			try {
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}
	
	//	todo 추가
	public void addTodo(Todo todo) {
		Connection conn = null;
		try {
			conn = DBUtil.getConnection("jdbc:mariadb://13.125.182.110/todo", "root", "java1004");
			todoDao = new TodoDao();
			todoDao.addTodo(conn, todo);
		} catch(Exception e) {
			e.printStackTrace();
		} finally {
			try {
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}
}
