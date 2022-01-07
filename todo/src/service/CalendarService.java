package service;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import commons.DBUtil;
import dao.TodoDao;
import vo.Todo;

public class CalendarService {
	
	private TodoDao todoDao;
	
	public Map<String, Object> getTargetCalendar(String memberId, String currentYear, String currentMonth, String option) {
		Map<String, Object> map = new HashMap<>();
		
		Calendar c = Calendar.getInstance();	//	오늘 날짜의 연도와 월
		
		if(currentYear != null && currentMonth != null) {
			
			int y = 0;
			int m = 0;
			
			y = Integer.parseInt(currentYear);		
			m = Integer.parseInt(currentMonth);
			if(option != null && option.equals("pre")) {	//	이전 버튼 클릭시
				m -= 1;		
				if(m == 0) {	//	월이 0이면
					y--;		//	연도 -1
					m = 12;
				}
			} else if(option.equals("next")) {				//	다음 버튼 클릭시
				m += 1;		
				if(m == 13) {	//	월이 13이면
					y++;		//	연도 +1
					m = 1;
				}
			}			
			c.set(Calendar.YEAR, y);
			c.set(Calendar.MONTH, m-1);		
		}

		c.set(Calendar.DATE, 1);	//	c객체의 오늘의 정보를 이 달 1일의 정보로 변경
		
		//	달력에 필요한 데이터
		int targetYear = c.get(Calendar.YEAR);
		int targetMonth = c.get(Calendar.MONTH) + 1;
		int endDay = c.getActualMaximum(Calendar.DATE);
		
		//	달력 앞, 뒤 공백의 개수
		int startBlank = 0; //	일요일 0, 월요일 1 ....	토요일 6
		startBlank = c.get(Calendar.DAY_OF_WEEK) - 1;
		
		int endBlank = 0;	//	전체 <td>개수 = startBlank + endDay + endBlank
		endBlank = 7 - (startBlank + endDay) % 7;
		if(endBlank == 7) {
			endBlank = 0;
		}
		
		map.put("targetYear", targetYear);
		map.put("targetMonth", targetMonth);
		map.put("endDay", endDay);
		map.put("startBlank", startBlank);
		map.put("endBlank", endBlank);
		
		//	월별 TodoList 출력 
		List<Todo> list = null;
		Connection conn = null;
		try {
			conn = DBUtil.getConnection("jdbc:mariadb://13.125.182.110:3306/todo", "root", "java1004");
			todoDao = new TodoDao();
			Todo todo = new Todo();
			//	memberId -> 매개변수
			todo.setMemberId(memberId);
			
			String strYear = "" + targetYear;
			String strMonth = "" + targetMonth;
			if(targetMonth < 10) {
				strMonth = "0" + targetMonth;
			}
			
			todo.setTodoDate(strYear + "-" + strMonth);
			
			//	System.out.println("todo -> " + todo);
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
		map.put("todoList", list);
		
		return map;
	}
}
