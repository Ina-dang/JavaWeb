package member.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import member.service.MemberService;
import member.service.MemberServiceImpl;

@WebServlet("/member/remove")
public class Remove extends HttpServlet{
	private MemberService memberService = MemberServiceImpl.GetInstance();
			
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//		req.setAttribute("id", req.getParameter("id"));
//		req.getRequestDispatcher("/WEB-INF/jsp/member/list.jsp").forward(req, resp);
		doPost(req, resp);
	}



	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.setCharacterEncoding("utf-8");
		
		String id = req.getParameter("id"); //프라이머리키를 기반으로 지우기
		System.out.println(id);
		
		resp.sendRedirect("list");
	}
}
