package controller.member;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;

import service.MemberService;

@WebServlet("/member/findMember")
//replyController이랑 모양 거의 같을거, 여기선 gson 일단 뺌
public class FindMember extends HttpServlet{
	private MemberService memberService = MemberService.getInstance();

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//		Reply reply = gson.fromJson(req.getReader().readLine(), Reply.class);
		String id = req.getParameter("id");
		String email = req.getParameter("email");
		System.out.println(id);
		System.out.println(email);
		
		resp.setContentType("text/plain; charset=utf-8");
		if (id!=null) {
			resp.getWriter().print(memberService.get(id) != null ? "1" : "");
		}
		
		if (email!=null) {
			resp.getWriter().print(memberService.findBy(email) != null ? "1" : "");
		}

	}
	
}
