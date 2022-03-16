package member.controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import member.service.MemberService;
import member.service.MemberServiceImpl;
import member.vo.MemberVo;

@WebServlet("/member/register")		
public class Register extends HttpServlet{
	private MemberService memberService = MemberServiceImpl.GetInstance(); 
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		//회원등록화면
		req.getRequestDispatcher("/WEB-INF/jsp/member/register.jsp").forward(req, resp);
		
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		//회원등록로직
		req.setCharacterEncoding("utf-8");
		
		String id = req.getParameter("id");
		String pwd = req.getParameter("pwd");
		String name = req.getParameter("name");
		String email = req.getParameter("email");
															  //sysdate 라서 null
		MemberVo memberVo = new MemberVo(id, pwd, name, email, null);
		
		memberService.register(memberVo);
		
		
		//마지막문장
		resp.sendRedirect("list"); 
	}
}
