package controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import service.TodoService;
import vo.Member;
import vo.Todo;

@WebServlet("/member/todoList")
public class TodoListController extends HttpServlet {
	
	private TodoService todoService;
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String y = request.getParameter("y");
		String m = request.getParameter("m");
		String d = request.getParameter("d");
		if(d.length() < 2) {
			d = "0" + d;
		}
		String todoDate = y + "-" + m + "-" + d;
		String memberId = ((Member)(request.getSession().getAttribute("loginMember"))).getMemberId();
		Todo todo = new Todo();
		todo.setTodoDate(todoDate);
		todo.setMemberId(memberId);
		
		todoService = new TodoService();
		List<Todo> todoList = todoService.getTodoListByDate(todo);
		
		request.setAttribute("todoList", todoList);
		request.setAttribute("todoDate", todoDate);
		
		request.getRequestDispatcher("/WEB-INF/view/todoList.jsp").forward(request, response);
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		request.getSession();
		
		String memberId = ((Member)(request.getSession().getAttribute("loginMember"))).getMemberId();
		String todoDate = request.getParameter("todoDate");
		String todoContent = request.getParameter("todoContent");
		
		Todo todo = new Todo();
		todoService = new TodoService();
		
		todo.setMemberId(memberId);
		todo.setTodoDate(todoDate);
		todo.setTodoContent(todoContent);
		
		//	todoService 호출 -> todo 데이터 DB에 추가
		todoService.addTodo(todo);
		
		//	수정된 todoList 재요청
		List<Todo> todoList = todoService.getTodoListByDate(todo);
		
		request.setAttribute("todoList", todoList);
		request.setAttribute("todoDate", todoDate);
		
		request.getRequestDispatcher("/WEB-INF/view/todoList.jsp").forward(request, response);
	}

}
