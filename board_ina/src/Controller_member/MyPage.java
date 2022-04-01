package Controller_member;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import domain.Member;
import service.MemberService;
import utils.Const;

@WebServlet("/member/join")
public class MyPage extends HttpServlet{
	private MemberService memberService = MemberService.getInstance();

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		//JSP바라볼곳  
		req.getRequestDispatcher(Const.member("join")).forward(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String id = req.getParameter("id");
		String pw = req.getParameter("pw");
		String name = req.getParameter("name");

		String si = req.getParameter("si");
		String sgg = req.getParameter("sgg");
		String emd = req.getParameter("emd");
		String roadAddr = req.getParameter("roadAddr");
		
		String addrDetail = req.getParameter("addrDetail");
		String zipNo = req.getParameter("zipNo");
		String roadFullAddr = req.getParameter("roadFullAddr");
		String jibunAddr = req.getParameter("jibunAddr");
				
		String email = req.getParameter("email");
		
		Member member = new Member(id, pw, name, si, sgg, emd, roadAddr, addrDetail, zipNo, roadFullAddr, jibunAddr, email, null);
		memberService.register(member);
		
		System.out.println(member);
		
		resp.sendRedirect(req.getContextPath()+ "/common/index");
	}
}
