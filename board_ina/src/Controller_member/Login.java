package Controller_member;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import domain.Member;
import service.MemberService;
import utils.Const;

@WebServlet("/member/login")
public class Login extends HttpServlet{
	private MemberService memberService = MemberService.getInstance();
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		//JSP바라볼곳  
		req.getRequestDispatcher(Const.member("login")).forward(req, resp);
		
//		if(req.getSession().getAttribute("member") == null ) {
//			resp.sendRedirect(req.getContextPath() + "/member/login?link=" + req.getRequestURI());
//			return;
//		}
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String id = req.getParameter("id");
		String pw = req.getParameter("pw");
		String link = req.getParameter("link");
		
		Member member = memberService.login(new Member(id, pw, null));
		
		if (member == null) {
			//로그인 실패
			req.setAttribute("msg", "로그인 실패");
			req.setAttribute("href", req.getRequestURI() + (link == null ? "" : "?link=" + link));
			req.getRequestDispatcher("/WEB-INF/jsp/common/msg.jsp").forward(req, resp);
		}
		else {
		req.getSession().setAttribute("member", member);
		link = link == null ? req.getContextPath() + "/common/index" : link ;
		req.setAttribute("msg", id + "님 로그인 성공");
		req.setAttribute("href", link);
		req.getRequestDispatcher("/WEB-INF/jsp/common/msg.jsp").forward(req, resp);
		}
	}
}
