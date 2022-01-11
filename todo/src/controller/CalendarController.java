package controller;

import java.io.IOException;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import service.CalendarService;
import service.TodoService;
import vo.Member;

				//	/member/로 시작하는 서블릿은 filter를 먼저 실행
@WebServlet("/member/calendar")
public class CalendarController extends HttpServlet {
	private CalendarService calendarService;
	private TodoService todoService;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String currentYear = request.getParameter("currentYear");
		String currentMonth = request.getParameter("currentMonth");
		String option = request.getParameter("option");
		
			//	디버깅
		//	System.out.println("현재 년도 -> " + currentYear);
		//	System.out.println("현재 월  -> " + currentMonth);
		//	System.out.println("선택 옵션 -> " + option);
		
		calendarService = new CalendarService();
		String memberId = ((Member)request.getSession().getAttribute("loginMember")).getMemberId();
		//	System.out.println("[디버깅]" + memberId);
		Map<String, Object> map = calendarService.getTargetCalendar(memberId, currentYear, currentMonth, option);
			
			//	계절 정보
		//	계절 정보를 담을 변수
		String season = "";
		if(map.get("targetMonth").equals(4) || map.get("targetMonth").equals(5)) {
			//	4, 5월 -> 봄
			season = "spring";
		} else if(map.get("targetMonth").equals(6) || map.get("targetMonth").equals(7) || map.get("targetMonth").equals(8) || map.get("targetMonth").equals(9)) {
			//	6, 7, 8, 9월 -> 여름
			season = "summer";
		} else if(map.get("targetMonth").equals(10) || map.get("targetMonth").equals(11)) {
			//	10, 11월 -> 가을
			season = "autumn";
		} else {
			//	12, 1, 2, 3월 -> 겨울
			season = "winter";
		}
		
		//	모델 값
		request.setAttribute("targetYear", map.get("targetYear"));
		request.setAttribute("targetMonth", map.get("targetMonth"));
		request.setAttribute("endDay", map.get("endDay"));
		request.setAttribute("season", season);
		
		//	공백 개수
		request.setAttribute("startBlank", map.get("startBlank"));
		request.setAttribute("endBlank", map.get("endBlank"));
		
		//	이달의 todoList
		request.setAttribute("todoList", map.get("todoList"));
		
		request.getRequestDispatcher("/WEB-INF/view/calendar.jsp").forward(request, response);
	}

}
