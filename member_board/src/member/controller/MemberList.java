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

		String keyword = req.getParameter("keyword"); //키워드받아서 검색으로 넣는다
		req.getSession().setAttribute("members", memberService.list(keyword));
//		System.out.println(req.getAttribute("members"));
		req.getRequestDispatcher("/WEB-INF/jsp/member/list.jsp").forward(req, resp);
		
	}
}
