package controller.board;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import domain.Board;
import domain.Criteria;
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
			return;
		}
		Criteria criteria = new Criteria();
		//페이지넘버링
		if(req.getParameter("pageNum") != null) {
			criteria.setPageNum(Integer.parseInt(req.getParameter("pageNum")));
		}
		//amount
		if(req.getParameter("amount") != null) {
			criteria.setAmount(Integer.parseInt(req.getParameter("amount")));
		}
		//category
		if(req.getParameter("category") != null) {
			criteria.setCategory(Integer.parseInt(req.getParameter("category")));
		}
		req.setAttribute("cri", criteria);
		req.setAttribute("board", boardService.get(bno));
		req.getRequestDispatcher("/WEB-INF/jsp/board/modify.jsp").forward(req, resp);
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String title = req.getParameter("title");
		String content = req.getParameter("content");
		String bno = req.getParameter("bno");
		
		Criteria criteria = new Criteria();
		//pageNum
		criteria.setPageNum(Integer.parseInt(req.getParameter("pageNum")));
		//amount
		criteria.setAmount(Integer.parseInt(req.getParameter("amount")));
		//category
		criteria.setCategory(Integer.parseInt(req.getParameter("category")));
		
		Board board = new Board(Long.parseLong(bno), title, content, criteria.getCategory());
		boardService.modify(board);
		resp.sendRedirect("list" + criteria.getParams2());
	}
}
