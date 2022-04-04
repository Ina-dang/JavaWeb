package Controller_board;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.BoardDao;
import domain.Criteria;
import domain.PageDto;
import service.BoardService;
import utils.Const;

@WebServlet("/board/list")
public class BoardList extends HttpServlet{
	private BoardService boardService = BoardService.getInstance();
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
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
											//총 페이지 개수, Criteria DTO
		req.setAttribute("page", new PageDto(boardService.count(criteria), criteria));
		req.setAttribute("boards", boardService.list(criteria));
		req.getRequestDispatcher(Const.board("list")).forward(req, resp);
	}

	public static void main(String[] args) {
		BoardDao boardDao = BoardDao.getInstance();
		boardDao.list(new Criteria(1, 10, 1)).forEach(System.out :: println);
	}
}
