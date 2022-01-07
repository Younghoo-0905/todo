package filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebFilter("/member/*")
public class LoginFilter implements Filter {

	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {

		HttpSession session = ((HttpServletRequest)request).getSession();
		System.out.println("doFilter() 실행");
		if(session.getAttribute("loginMember") == null) {
			((HttpServletResponse)response).sendRedirect(((HttpServletRequest)request).getContextPath()+"/login");
			return;
		}
		
		chain.doFilter(request, response);
	}



}
