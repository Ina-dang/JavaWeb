package controller.board;

import java.io.File;
import java.io.IOException;
import java.net.URLEncoder;
import java.nio.file.Files;
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

import domain.Attach;
import domain.Board;
import domain.Criteria;
import domain.Member;
import net.coobird.thumbnailator.Thumbnails;
import net.coobird.thumbnailator.geometry.Positions;
import oracle.net.aso.f;
import service.BoardService;
import service.MemberService;

@WebServlet("/board/register")
public class Register extends HttpServlet{
	private BoardService boardService = BoardService.getInstance();
	private MemberService memberService = MemberService.getInstance();
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		//파일첨부 만드는동안 세션 고정값 지정 (로그인 안풀리게) 테스트코드 작성 >> 원래는 분리해야하는데 스프링아니니까 ㄱㄱ
//		req.getSession().setAttribute("member", memberService.login(new Member("inadang", "1234", null)));
		
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
		
		
		req.getRequestDispatcher("/WEB-INF/jsp/board/register.jsp").forward(req, resp);
		
	}
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		Criteria cri = new Criteria();
		Board board = upload(req, cri);
		
		boardService.register(board);
		resp.sendRedirect("list" + cri.getParams2());
	}
	
	//보드타입을 리턴한는 첨부파일 메서드
	private Board upload(HttpServletRequest req, Criteria cri) {
		Board board = new Board();
		String saveDir = "C:\\upload";
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
					if (fi.getFieldName().equals("title")) {
						board.setTitle(fi.getString("utf-8"));
					}
					if (fi.getFieldName().equals("content")) {
						board.setContent(fi.getString("utf-8"));
					}
					if (fi.getFieldName().equals("writer")) {
						board.setWriter(fi.getString("utf-8"));
					}
					if (fi.getFieldName().equals("amount")) {
						cri.setAmount(Integer.parseInt(fi.getString("utf-8")));
					}
					if (fi.getFieldName().equals("category")) {
						cri.setCategory(Integer.parseInt(fi.getString("utf-8")));
						board.setCategory(cri.getCategory());
					}
					//return 할 board인스턴스 생성
				}
				else { //attach객체 만들기
					//업로드 하지않은 파일에 대한 방지
					if (fi.getSize() == 0) { //파일길이 0이면 멈춤
						continue;
					};
					
					String origin = fi.getName(); //원본파일 이름 가져오기 (FileItem에서)

					//이 오리진에서 ex)free.html 이면 free만 가져오니까 확장자를 넣어줘야함
					int idxDot = origin.lastIndexOf("."); //해당파일명중에서 .마지막위치 찾음
					String ext = " ";
					
					if (idxDot != -1) {
						ext = origin.substring(idxDot);
					}
					UUID uuid = UUID.randomUUID();
					String name = uuid + ext;
					
															//날짜별폴더파일
					File upPath = new File(currentDir + "\\" + getTodayStr());
					if(!upPath.exists()) {
						upPath.mkdirs();
					}
					//upPath위치에 이름(uuid+ext)씀
					fi.write(new File(upPath, name));
					
					//첨부파일 하나
//					Attach attach = new Attach(name, origin, getTodayStr(), 
//							fi.getContentType().contains("image"), 1, null);
					
					//이미지유효성 + 썸네일 + 첨부파일
					Attach attach = new Attach(name, origin, getTodayStr());
					procImageType(attach, upPath, name);
					
					board.getAttachs().add(attach); //리스트에 첨부파일 넣음
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return board;
	}
	
	//오늘 날짜에 대한 연,월,일 형태의 정보를 가져옴
	private String getTodayStr() {
		return new SimpleDateFormat("yyyy/MM/dd").format(System.currentTimeMillis());
	}
	
	private void procImageType(Attach attach, File upPath, String name) {
		//빌더형식으로 작업
		File file = new File(upPath, name);
		try {
			Thumbnails
				.of(file)
				.sourceRegion(Positions.TOP_CENTER,200,200)
				.size(200, 200)
				.toFile(new File(upPath, "s_"+name));	
			attach.setImage(true);
		} catch (Exception ignore) {
//			e.printStackTrace();
//			이미지 아니면 예외 나올거라 프린트 안함
		}
	}
}
