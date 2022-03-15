package ex01;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/input")
public class InputServlet extends HttpServlet{

	@Override //doGet 화면담당
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.getRequestDispatcher("/WEB-INF/jsp/member/input.jsp").forward(req,resp);
	}

	@Override //doPost 로직담당
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		req.setCharacterEncoding("utf-8"); //utf-8방식으로 옵니다 설정해줘야함
		
			String id = req.getParameter("user_id");
			String pw = req.getParameter("user_pw"); 
			
			String[] subjects = req.getParameterValues("subject");
			
		if(subjects != null) {
			for (String subject : subjects) {
				System.out.println("선택한과목 : " + subject);
			}
			System.out.println(id); 
			System.out.println(pw); 
			
			req.getRequestDispatcher("/WEB-INF/jsp/member/input_test.jsp").forward(req,resp);
		}
	}
}
