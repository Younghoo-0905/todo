package controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import service.MemberService;
import service.NoticeService;
import vo.Member;
import vo.Notice;

@WebServlet("/login")
public class LoginController extends HttpServlet {
	private MemberService memberService;
	private NoticeService noticeService;
	
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//	get --> login 홈페이지 표시			
		HttpSession session = request.getSession();
		if(session.getAttribute("loginMember") != null) {
			response.sendRedirect(request.getContextPath() + "/member/calendar");
			return;
		}
		
		noticeService = new NoticeService();
		List<Notice> noticeList = noticeService.getNoticeList5();
		request.setAttribute("noticeList", noticeList);
		
		//	로그인 화면
		request.getRequestDispatcher("/WEB-INF/view/login.jsp").forward(request, response);
	}
	
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//	post --> login 액션 실행
		//	request.setCharacterEncoding("UTF-8");	
		//	doPost() 메소드 상단에 항상 기재
		//	-> filter 사용
				
		String memberId = request.getParameter("memberId");
		String memberPw = request.getParameter("memberPw");
		Member member = new Member();
		member.setMemberId(memberId);
		member.setMemberPw(memberPw);		
		
		memberService = new MemberService();
		Member loginMember = memberService.login(member);	//	null이면 로그인 실패
		if(loginMember == null) {
			response.sendRedirect(request.getContextPath()+"/login");
			return;
		}
		HttpSession session = request.getSession();
		session.setAttribute("loginMember", loginMember);
		response.sendRedirect(request.getContextPath()+"/member/calendar");
	}	
}
