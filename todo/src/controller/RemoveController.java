package controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import service.MemberService;

@WebServlet("/member/removeMember")
public class RemoveController extends HttpServlet {
	private MemberService memberService;
	
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//	get -> 멤버삭제 폼
		
		request.getRequestDispatcher("/WEB-INF/view/removeMember.jsp").forward(request, response);
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//	post -> 멤버삭제 액션
		
		String memberId = request.getParameter("memberId");
		String memberPw = request.getParameter("memberPw");

			//	값 디버깅
		System.out.println(memberId);
		System.out.println(memberPw);
		
		memberService = new MemberService();
		boolean result = memberService.removeMember(memberId, memberPw);
		if(result == true) {
			response.sendRedirect(request.getContextPath()+"/member/logout");			
			System.out.println("회원탈퇴 성공");
			return;
		} else {
			response.sendRedirect(request.getContextPath()+"/member/removeMember");
			System.out.println("비밀번호 불일치");
			return;
		}
	}

}
