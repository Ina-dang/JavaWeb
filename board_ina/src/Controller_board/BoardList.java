package Controller_board;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import service.BoardService;
import utils.Const;

@WebServlet("/board/list")
public class BoardList extends HttpServlet{
	private BoardService boardService = BoardService.getInstance();
	
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		//JSP바라볼곳  
		req.setAttribute("boards", boardService.list());
		req.getRequestDispatcher(Const.board("list")).forward(req, resp);
	}
}
