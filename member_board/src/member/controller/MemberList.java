package member.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import member.service.MemberService;
import member.service.MemberServiceImpl;

@WebServlet("/member/list") //요청정보 멤버의 리스트가 받게됨
public class MemberList extends HttpServlet{
	private MemberService memberService = MemberServiceImpl.GetInstance();

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		//같은 위치를 바라보는 애한테 (리퀘스트한테) 멤버스의 이름으로 멤버서비스.리스트 결과를 담음
//		req.setAttribute("members", memberService.list());
		req.getSession().setAttribute("members", memberService.list());
		System.out.println(req.getAttribute("members"));
		req.getRequestDispatcher("/WEB-INF/jsp/member/list.jsp").forward(req, resp);
	}
}
