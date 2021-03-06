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
import sun.reflect.ReflectionFactory.GetReflectionFactoryAction;
import utils.Util;

@WebServlet("/member/myPage")
public class MyPage extends HttpServlet{
	private MemberService memberService = MemberService.getInstance();
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		//세션여부확인v
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
		
		Member member = Util.getParam(req, Member.class);
		System.out.println(member);
		
		memberService.modify(member);
		req.setAttribute("msg", "회원정보가 정상적으로 수정되었습니다");
		
		
		//업데이트 성공시 멤버정보 가져옴 (로그인성공시 나오는결과) 일단보류 > 로그인세션이자꾸풀림
//		req.getSession().setAttribute("member", memberService.login(member));
		req.setAttribute("href", req.getContextPath() + "/board/list");
//		Member m = (Member) req.getSession().getAttribute("member");
//		m = memberService.login(member);
		
		req.getRequestDispatcher("/WEB-INF/jsp/common/msg.jsp").forward(req, resp);
	}
}
