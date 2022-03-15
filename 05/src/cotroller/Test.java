package cotroller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

//어떤 url로 이 서블리승ㄹ 할지 매핑. 꼭/ 해야함 
/*
 * 선생님 @WebServlet() 이거 주석 적을때 >> 어떤 url로 이 서블릿을 수행할지 매핑한다 라고 이해해가면 될까요..? >뭐 맞는말이긴하다고하심
 */
//annotation
@WebServlet("/test")
public class Test extends HttpServlet{

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		System.out.println("doGet()");
	}

	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		System.out.println("service()");
	} //선호출됨
	
}
