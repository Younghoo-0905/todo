package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import vo.Todo;

public class TodoDao {
	
	//	일별 Todo List 출력
	public List<Todo> selectTodoListByDate(Connection conn, Todo todo) throws SQLException {
		List<Todo> list = new ArrayList<Todo>();
		String sql = TodoQuery.SELECT_TODOLIST_BY_DATE;
		PreparedStatement stmt = conn.prepareStatement(sql);
		stmt.setString(1, todo.getMemberId());
		stmt.setString(2, todo.getTodoDate());
		ResultSet rs = stmt.executeQuery();
		while(rs.next()) {
			Todo t = new Todo();
			t.setTodoNo(rs.getInt("todoNo"));
			t.setTodoDate(rs.getString("todoDate"));
			t.setTodoContent(rs.getString("todoContent"));
			t.setTodoComplete(rs.getString("todoComplete"));
			t.setCreateDate(rs.getString("createDate"));
			t.setUpdateDate(rs.getString("updateDate"));
			t.setFontColor(rs.getString("fontColor"));
			list.add(t);
		}
		return list;
	}
	
	//	월별 Todo List 출력
	public List<Todo> selectTodoListByMonth(Connection conn, Todo todo) throws SQLException {
		List<Todo> list = new ArrayList<Todo>();
		String sql = TodoQuery.SELECT_TODOLIST_BY_MONTH;
		PreparedStatement stmt = conn.prepareStatement(sql);
		stmt.setString(1, todo.getMemberId());
		stmt.setString(2, todo.getTodoDate());
		ResultSet rs = stmt.executeQuery();
		while(rs.next()) {
			Todo t = new Todo();
			t.setTodoDate(rs.getString("todoDate"));
			t.setTodoContent(rs.getString("todoContent5"));
			t.setTodoComplete(rs.getString("todoComplete"));
			t.setFontColor(rs.getString("fontColor"));
			list.add(t);
		}
		return list;
	}
	
	//	Todo 완료 상태변경
	public void updateTodoComplete(Connection conn, int todoNo) throws SQLException {
		String sql = TodoQuery.UPDATE_TODO_COMPLETE;
		PreparedStatement stmt = conn.prepareStatement(sql);
		stmt.setInt(1, todoNo);
		stmt.executeUpdate();
		stmt.close();		
	}
	
	//	Todo 삭제
	public void deleteTodoOne(Connection conn, int todoNo) throws SQLException {
		String sql = TodoQuery.DELETE_TODO_ONE;
		PreparedStatement stmt = conn.prepareStatement(sql);
		stmt.setInt(1, todoNo);
		stmt.executeUpdate();
		stmt.close();
	}
	
	//	Todo 추가
	public void addTodo(Connection conn, Todo todo) throws SQLException {
		String sql = TodoQuery.INSERT_TODO;
		PreparedStatement stmt = conn.prepareStatement(sql);
		stmt.setString(1, todo.getMemberId());
		stmt.setString(2, todo.getTodoContent());
		stmt.setString(3, todo.getTodoDate());
		stmt.setString(4, todo.getFontColor());
		stmt.executeQuery();
		stmt.close();
	}
	
	//	회원 탈퇴시 해당 회원의 To do 삭제
	public void deleteTodo(Connection conn, String memberId) throws SQLException {
	    String sql = TodoQuery.DELETE_TODO;
	    PreparedStatement stmt = conn.prepareStatement(sql);
	    stmt.setString(1, memberId);
	    stmt.executeUpdate();
	    stmt.close();
	}
}