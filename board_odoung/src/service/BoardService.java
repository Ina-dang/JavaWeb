package service;

import java.io.File;
import java.util.List;

import dao.AttachDao;
import dao.BoardDao;
import domain.Attach;
import domain.Board;
import domain.Criteria;

public class BoardService {
	private static BoardService boardService = new BoardService();
	public static BoardService getInstance() {
		return boardService;
	}
	
	private BoardDao boardDao = BoardDao.getInstance(); 
	private AttachDao attachDao = AttachDao.getInstance();
	
	private BoardService() {}
	
	// 글 목록
	public List<Board> list(Criteria criteria) {
		List<Board> list = boardDao.list(criteria);
		return list;
	}
	
	// 글 상세
	public Board get(Long bno) {
		Board board = boardDao.get(bno);
		board.setAttachs(attachDao.list(board.getBno()));
		return board;
	}
	
	// 글 작성
	public void register(Board board) {
		System.out.println("boardService.register board.bno :: " + board.getBno()); //null
		boardDao.register(board);
		System.out.println("boardService.register board.bno :: " + board.getBno()); //bno
//		board.getAttachs().forEach(attach -> {
//			attach.setBno(board.getBno());
//			attachDao.insert(attach);
//		});
		
		for (Attach attach : board.getAttachs()) {
			attach.setBno(board.getBno());
			attachDao.insert(attach);
		};
		
	}
	// 글 수정
	public void modify(Board board) {
		boardDao.modify(board);
	}
	// 글 삭제
	public void remove(Long bno) {
		//첨부파일조회			
		List<Attach> attachs = attachDao.list(bno);
		//물리적삭제 >> 서블릿으로 별도처리 해줘야함
		String saveDir = "D:\\upload";
//		d:\\upload\2022\03\22
		for (Attach attach : attachs) {
			File file = new File(saveDir, attach.getPath());
			file = new File(file, attach.getUuid());
			System.out.println(file);
			file.delete();
		}
		//DB attach테이블 내 첨부파일 목록삭제
		attachDao.remove(bno);
		//DB에서 글삭제
		boardDao.remove(bno); 
	}
	
	//게시글 갯수
	public int count(Criteria cri) {
		return boardDao.count(cri);
	}
	
	public static void main(String[] args) {
		BoardService.getInstance().remove(301L);
	}
}
