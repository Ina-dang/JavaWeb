package Controller_board;

import java.io.IOException;
import java.net.URLEncoder;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import domain.Board;
import domain.Criteria;
import domain.Member;
import service.BoardService;
import utils.Const;

@WebServlet("/board/register")
public class Register extends HttpServlet{
	BoardService boardService = BoardService.getInstance(); 
	
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		//비로그인 상태에는 로그인 화면으로간다
//		if(req.getSession().getAttribute("member") == null) {
//			resp.sendRedirect(req.getContextPath() + "/member/login?link="+req.getRequestURI() + "?" + URLEncoder.encode(req.getQueryString(), "utf-8"));
//			return; //리턴키워드 만나면 밑에 수행안함 (else있는거랑 같음쓰)
//		}
		
		
	 //로그인되었을때
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
		
		//JSP바라볼곳  
		req.getRequestDispatcher(Const.board("register")).forward(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		Criteria criteria = new Criteria();
		String title = req.getParameter("title");
		String content = req.getParameter("content");
		String writer = req.getParameter("writer");
		System.out.println(title);
		
		//category
		criteria.setCategory(Integer.parseInt(req.getParameter("category")));

		
		Board board = new Board(title, content, writer, criteria.getCategory());
//		Board board = new Board();
		
		boardService.register(board);
		System.out.println(board);
		resp.sendRedirect("list" + criteria.getParams2());
	}
	
	
}
