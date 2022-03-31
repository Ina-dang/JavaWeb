package controller.member;

import java.io.IOException;
import java.net.URLEncoder;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import domain.Member;
import service.MemberService;

@WebServlet("/member/myPage")
public class MyPage extends HttpServlet{
	private MemberService memberService = MemberService.getInstance();
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		//세션여부확인
		if(req.getSession().getAttribute("member") == null) {
			resp.sendRedirect(req.getContextPath() + "/member/login?link="+req.getRequestURI() + "?" + 
					URLEncoder.encode(req.getQueryString() == null ? "" : req.getQueryString(), "utf-8"));
			return; 
		}
		//로그인상태면 이름을 받아서 마이페이지로 보냄
		Member member = (Member)req.getSession().getAttribute("member");
		member = memberService.get(member.getId());
		req.setAttribute("memberInfo", member);		
		//아이디탐색 마이페이지
		req.getRequestDispatcher("/WEB-INF/jsp/member/myPage.jsp").forward(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
//		Member member = Util.getParam(req, Member.class);
//		System.out.println(member);
//		
//		memberService.register(member);
//		resp.sendRedirect(req.getContextPath()+"/board/list");
	}
}
