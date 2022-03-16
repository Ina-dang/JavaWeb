package member.controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import member.service.MemberService;
import member.service.MemberServiceImpl;
import member.vo.MemberVo;

@WebServlet("/member/login")		
public class Login extends HttpServlet{
	private MemberService memberService = MemberServiceImpl.GetInstance(); 
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		//로그인화면
		req.getRequestDispatcher("/WEB-INF/jsp/member/login.jsp").forward(req, resp);
		
		RequestDispatcher rd = req.getRequestDispatcher("/WEB-INF/jsp/member/login.jsp");
		rd.forward(req, resp);
		
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		//로그인로직
		req.setCharacterEncoding("utf-8");
		
		String id = req.getParameter("id");
		String pwd = req.getParameter("pwd");
		
		System.out.println(id + " " + pwd);
		

		req.getSession().setAttribute("member", memberService.login(id, pwd)); //멤버속성의 멤버사비스로그인객체를 세션에 담음
		req.getSession().setMaxInactiveInterval(600);
		resp.sendRedirect("list");  //맨밑에서 수행 주석 밑인데 일단 위로가져옴
	}
}
