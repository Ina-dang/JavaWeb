package controller.board;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import domain.Criteria;
import service.BoardService;

@WebServlet("/board/remove")
public class Remove extends HttpServlet{
	private BoardService boardService = BoardService.getInstance();
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		Long bno = Long.parseLong(req.getParameter("bno"));
		boardService.remove(bno);
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
		
		resp.sendRedirect("list" + criteria.getParams2());
	}
}
