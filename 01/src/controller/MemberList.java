package controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import service.MemberService;
import service.MemberServiceImpl;

//어노테이션의 목적 >> 현재 서블릿에 대한 URL 매핑작업 >> 얘가 컴파일 돼서 톰캣이 인식해서 저 주소의 인스턴스 객체를
//겟 방식할때는 포스트할 때는 두포스트
@WebServlet("/member/list")
//이 이름으로 매핑되어있던 서블릿을 찾음
/*
 * 이 이름에 일치하는 매핑 정보를 찾음
 * 찾은다음에 그 안에 있던것을
 * 어떤 메서드가 나한테 인식했는지(겟 포스트 서비스 등)
 * 그에따른 적절한 두겟 두포스트 등을 반환
 * 
 * 기본요청은 겟방식
 * 
 * 리퀘스트객체의 "members"이름을 가진 값이 memberService.list() 
 * 
 * 리퀘스트가 살아있는동안 유지
 * 
 * 요청주소는 member/list
 * 화면은 포워딩 된 list.jsp
 * list.jsp 멤버리스트 서블릿은 같은 리퀘스트를 가지고있다
 * 
 */
public class MemberList extends HttpServlet{
	private MemberService memberService = MemberServiceImpl.GetInstance();
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.setAttribute("members", memberService.list());
		req.getRequestDispatcher("/WEB-INF/jsp/member/list.jsp").forward(req, resp);
	}
}
