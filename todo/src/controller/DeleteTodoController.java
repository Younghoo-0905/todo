package controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import service.TodoService;
import vo.Member;
import vo.Todo;

@WebServlet("/member/deleteTodo")
public class DeleteTodoController extends HttpServlet {
	
	private TodoService todoService;
	
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
				
		//	값 전달
		int todoNo = Integer.parseInt(request.getParameter("todoNo"));

		//	삭제 메소드 실행
		todoService = new TodoService();
		todoService.deleteTodoOne(todoNo);
		
		request.getSession();
		
		String memberId = ((Member)(request.getSession().getAttribute("loginMember"))).getMemberId();
		String todoDate = request.getParameter("todoDate");		
		
		Todo todo = new Todo();		
		todo.setMemberId(memberId);
		todo.setTodoDate(todoDate);		

		//	수정된 todoList 재요청
		List<Todo> todoList = todoService.getTodoListByDate(todo);
		
		request.setAttribute("todoList", todoList);
		request.setAttribute("todoDate", todoDate);

		request.getRequestDispatcher("/WEB-INF/view/todoList.jsp").forward(request, response);
	}
}
