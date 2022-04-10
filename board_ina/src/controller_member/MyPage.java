package controller_member;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import domain.Member;
import service.MemberService;
import utils.Const;
import utils.Reflection;

@WebServlet("/member/myPage")
public class MyPage extends HttpServlet{
	private MemberService memberService = MemberService.getInstance();

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		//세션 여부 확인
		if (req.getSession().getAttribute("member") == null) {
			resp.sendRedirect(req.getContextPath() + "/member/login?link=" 
						+ req.getRequestURI() + "?");
			return;
		}
		
		//로그인 상태면 이름을 받아서 마이페이지로 보냄
		Member member = (Member)req.getSession().getAttribute("member");
		member = memberService.idFind(member.getId());
		req.setAttribute("memberInfo", member);
		
		//JSP바라볼곳  
		req.getRequestDispatcher(Const.member("myPage")).forward(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		Member member = Reflection.getParam(req, Member.class);
		memberService.modify(member);
		
		req.setAttribute("msg", "회원정보가 수정되었습니다");

		req.getRequestDispatcher(Const.common("msg")).forward(req, resp);
	}
}
