package ex01;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/login")
public class Login extends HttpServlet{

	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		if(req.getMethod().equals("GET")) {
			req.getRequestDispatcher("/WEB-INF/jsp/member/login.jsp").forward(req,resp);
		} else {
			resp.setStatus(405);
		}
		System.out.println("service()");
	}

//	//get으로 어찌됐건 해결
//	@Override
//	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//		System.out.println("실제해야할 일");
//	}
//
//	@Override
//	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//		doGet(req, resp);
//	}
//	
	
	@Override //doGet 화면담당
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.getRequestDispatcher("/WEB-INF/jsp/member/login.jsp").forward(req,resp);
	}

	@Override //doPost 로직담당
	//post 방식으로 parameter 전송시 req의 header에 포함시켜 전송(1byte 분할 방식)
	// 한글 은 3바이트. 아무리 쪼개도 2바이트라서 이상하게뜬다
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		req.setCharacterEncoding("utf-8"); //utf-8방식으로 옵니다 설정해줘야함
		
		String id = req.getParameter("user_id");
		String pw = req.getParameter("user_pw");
		
		System.out.println(id);
		System.out.println(pw);
	}

	
	
	
	
	//controller에 의해서 제어 포워딩처리
	

}
