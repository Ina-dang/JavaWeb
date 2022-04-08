package Controller_board;

import java.io.File;
import java.io.IOException;
import java.net.URLEncoder;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.UUID;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

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
		if(req.getSession().getAttribute("member") == null) {
			resp.sendRedirect(req.getContextPath() + "/member/login?link="+req.getRequestURI() + "?" + URLEncoder.encode(req.getQueryString(), "utf-8"));
			return; //리턴키워드 만나면 밑에 수행안함 (else있는거랑 같음쓰)
		}
		
		
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
//		String title = req.getParameter("title");
//		String content = req.getParameter("content");
//		String writer = req.getParameter("writer");
//		System.out.println(title);
//		
//		//category
//		Criteria criteria = new Criteria();
//		criteria.setAmount(Integer.parseInt(req.getParameter("amount")));
//		criteria.setCategory(Integer.parseInt(req.getParameter("category")));
//
//		
//		Board board = new Board(title, content, writer, criteria.getCategory());
//		boardService.register(board);
//		System.out.println(board);
//		resp.sendRedirect("list" + criteria.getParams2());
		
		String saveDir = "D:\\upload";
		int size = 10 * 1024 * 1024;
		
		File currentDir = new File(saveDir);
		DiskFileItemFactory factory = new DiskFileItemFactory();
		factory.setRepository(currentDir);
		factory.setSizeThreshold(size);
		
		ServletFileUpload upload = new ServletFileUpload(factory);
		try {
			List<FileItem> items = upload.parseRequest(req);
			for(FileItem fi : items) {
				if (fi.isFormField()) {
					System.out.println(fi.getFieldName() + " = " + fi.getString("utf-8"));
				}
				else {
					System.out.println(fi.getFieldName());
					
					String origin = fi.getName();
					System.out.println(origin);
					String ext = origin.substring(origin.lastIndexOf("."));
					
					UUID uuid = UUID.randomUUID();
					String name = uuid + ext;
					
					System.out.println(fi.getSize());
					
					File upPath = new File(currentDir + "\\" + getTodayStr());
					if (!upPath.exists()) {
						
					}
				
				}
			}
		} catch (Exception e) {
			// TODO: handle exception
		}
	}
	
	//파일업로드 년월일 시간정보 가져올 때 사용할 거
	private String getTodayStr() {
		return new SimpleDateFormat("yyyy/MM/dd").format(System.currentTimeMillis());
	}
	
}
