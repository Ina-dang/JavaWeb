package controller.board;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import domain.Criteria;
import domain.PageDto;
import service.BoardService;

@WebServlet(value = "/board/list") //게시판첫화면
public class BoardList extends HttpServlet{
	private BoardService boardService = BoardService.getInstance();
	
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
		
		//갤러리게시판 위한 조건식 => 원래는 따로안씀, but우린느 별도처리
		if (criteria.getCategory() == 3) {
			req.getRequestDispatcher("/WEB-INF/jsp/board/gallery.jsp").forward(req, resp);
			return;			
		}
		
		req.getRequestDispatcher("/WEB-INF/jsp/board/list.jsp").forward(req, resp);
	}
	public static void main(String[] args) {
		Criteria criteria = new Criteria();
		System.out.println(criteria);
	}
}
