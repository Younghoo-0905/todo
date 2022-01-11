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
		//	날짜 정보
		String y = request.getParameter("y");
		String m = request.getParameter("m");
		String d = request.getParameter("d");
		
			//	계절 정보
		//	계절 정보를 담을 변수
		String season = "";
		if(m.equals("4") || m.equals("5")) {
			//	4, 5월 -> 봄
			season = "spring";
		} else if(m.equals("6") || m.equals("7") || m.equals("8") || m.equals("9")) {
			//	6, 7, 8, 9월 -> 여름
			season = "summer";
		} else if(m.equals("10") || m.equals("11")) {
			//	10, 11월 -> 가을
			season = "autumn";
		} else {
			//	12, 1, 2, 3월 -> 겨울
			season = "winter";
		}		

		//	가공 전 날짜 데이터 전달
		request.setAttribute("y", y);
		request.setAttribute("m", m);
		
		//	변수 가공 -> xxxx-xx-xx 형태 날짜데이터 생성
		if(m.length() < 2) {
			m = "0" + m;
		}
		if(d.length() < 2) {
			d = "0" + d;
		}
		String todoDate = y + "-" + m + "-" + d;
		String memberId = ((Member)(request.getSession().getAttribute("loginMember"))).getMemberId();
		Todo todo = new Todo();
		todo.setTodoDate(todoDate);
		todo.setMemberId(memberId);
		
		//	todoList 출력 메소드 실행
		todoService = new TodoService();
		List<Todo> todoList = todoService.getTodoListByDate(todo);
		
		//	값 전달
		request.setAttribute("todoList", todoList);
		request.setAttribute("todoDate", todoDate);
		request.setAttribute("season", season);
		
		request.getRequestDispatcher("/WEB-INF/view/todoList.jsp").forward(request, response);
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		request.getSession();
		
		String memberId = ((Member)(request.getSession().getAttribute("loginMember"))).getMemberId();
		String todoDate = request.getParameter("todoDate");
		String todoContent = request.getParameter("todoContent");
		String fontColor = request.getParameter("fontColor");
		String season = request.getParameter("season");
		
		System.out.println(memberId);
		System.out.println(todoDate);
		System.out.println(todoContent);
		System.out.println(fontColor);
		
		Todo todo = new Todo();
		todoService = new TodoService();
		
		todo.setMemberId(memberId);
		todo.setTodoDate(todoDate);
		todo.setTodoContent(todoContent);
		todo.setFontColor(fontColor);
		
		//	todoService 호출 -> todo 데이터 DB에 추가
		todoService.addTodo(todo);
		
		//	수정된 todoList 재요청
		List<Todo> todoList = todoService.getTodoListByDate(todo);
		
		request.setAttribute("todoList", todoList);
		request.setAttribute("todoDate", todoDate);
		request.setAttribute("season", season);

		request.getRequestDispatcher("/WEB-INF/view/todoList.jsp").forward(request, response);
	}

}
