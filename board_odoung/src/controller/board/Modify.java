package controller.board;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import domain.Board;
import domain.Member;
import service.BoardService;

@WebServlet("/board/modify")
public class Modify extends HttpServlet{
	private BoardService boardService = BoardService.getInstance();
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		Long bno = Long.parseLong(req.getParameter("bno"));
		Member member = (Member) req.getSession().getAttribute("member");
		if(member == null || !member.getId().equals(boardService.get(bno).getWriter())) {
			resp.sendRedirect(req.getContextPath() + "/board/list");
		}
		else {
			req.setAttribute("board", boardService.get(bno));
			req.getRequestDispatcher("/WEB-INF/jsp/board/modify.jsp").forward(req, resp);
		}
	}
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.setCharacterEncoding("utf-8");
		String title = req.getParameter("title");
		String content = req.getParameter("content");
		String bno = req.getParameter("bno");
		
		Board board = new Board(Long.parseLong(bno), title, content, null);
		boardService.modify(board);
		resp.sendRedirect("list");
	}
	
	
	
}
