package controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import service.MemberService;
import service.MemberServiceImpl;
import vo.MemberVo;

@WebServlet("/member/register")
public class Register extends HttpServlet{
	private MemberService memberService = MemberServiceImpl.GetInstance();

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// 회원등록화면
		req.getRequestDispatcher("/WEB-INF/jsp/member/register.jsp").forward(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// 회원등록로직
		String id = req.getParameter("id");
		String pw = req.getParameter("pw");
		String name = req.getParameter("name");
		String nick = req.getParameter("nick");
		String email = req.getParameter("email");
		
		MemberVo memberVo = new MemberVo(id, pw, name, nick, email, null);
		
		memberService.register(memberVo);
		
		resp.sendRedirect("list"); 
	}
	
	
}
