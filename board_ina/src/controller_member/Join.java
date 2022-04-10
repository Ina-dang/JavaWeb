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

@WebServlet("/member/join")
public class Join extends HttpServlet{
	private MemberService memberService = MemberService.getInstance();

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		//JSP바라볼곳  
		req.getRequestDispatcher(Const.member("join")).forward(req, resp);
		
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		//변수 하나하나 선언해서 겟파라미터안해도 얘가 롬복처럼 가져와줌
		Member member = Reflection.getParam(req, Member.class);
		
		
		memberService.join(member);
		
		System.out.println(member);
		
		resp.sendRedirect(req.getContextPath()+ "/common/index");
	}
}
