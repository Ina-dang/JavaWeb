package member.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import member.service.MemberService;
import member.service.MemberServiceImpl;

@WebServlet("/member/list")
public class MemberList extends HttpServlet{
	private MemberService memberService = MemberServiceImpl.GetInstance();

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.setAttribute("members", memberService.list());
		req.getRequestDispatcher("/WEB-INF/jsp/member/list.jsp").forward(req, resp);
	}
}
