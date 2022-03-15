package controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import service.StudentService;


//서블릿매핑(web.xml같은거) 자바 가 웹서비스 하기위해 등록
//그런데 아래 @WebServlet하면 서블릿매핑안해도됨
@WebServlet("/db")
public class DB extends HttpServlet{

	//여기서 서비스 만듦
	private StudentService StudentService = new StudentService(); //원래 얘도 싱글터해야함
	
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//		System.out.println("테스트!");
		//포워딩 student이름으로 리스트가 들어감
		req.setAttribute("students", StudentService.list());
		req.getRequestDispatcher("/db.jsp").forward(req, resp);
	}
	
}

