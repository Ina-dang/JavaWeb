package Controller_board;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import utils.Const;

@WebServlet("/board/get")
public class Get extends HttpServlet{

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		//JSP바라볼곳  
		req.getRequestDispatcher(Const.board("get")).forward(req, resp);
	}
}
