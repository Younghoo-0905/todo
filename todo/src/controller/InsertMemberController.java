package controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import service.MemberService;
import vo.Member;

@WebServlet("/insertMember")
public class InsertMemberController extends HttpServlet {
	
	private MemberService memberService;
	
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		//	get --> insertMemberForm 페이지
		
		//	로그인 멤버 접근제한
		HttpSession session = request.getSession();
		if(session.getAttribute("loginMember") != null) {
			response.sendRedirect(request.getContextPath() + "/member/calendar");
			return;
		}				
		
		request.getRequestDispatcher("/WEB-INF/view/insertMemberForm.jsp").forward(request, response);
	}
	
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		//	post --> 회원가입
		Member member = new Member();
		member.setMemberId(request.getParameter("memberId"));
		member.setMemberPw(request.getParameter("memberPw"));
		
		memberService = new MemberService();
		memberService.insertMember(member);

		response.sendRedirect(request.getContextPath()+"/login");
	}
}
