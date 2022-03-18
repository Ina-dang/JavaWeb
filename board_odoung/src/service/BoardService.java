package service;

import java.util.List;

import dao.BoardDao;
import domain.Board;

public class BoardService {
	private static BoardService boardService = new BoardService();
	public static BoardService getInstance() {
		return boardService;
	}
	
	private BoardDao boardDao = BoardDao.getInstance(); 
	
	private BoardService() {}
	
	// 글 목록
	public List<Board> list() {
		return boardDao.list();
	}
	// 글 상세
	public Board get(Long bno) {
		return boardDao.get(bno);
	}
	// 글 작성
	public void register(Board board) {
		boardDao.register(board);
	}
	// 글 수정
	public void modify(Board board) {
		boardDao.modify(board);
	}
	// 글 삭제
	public void remove(Long bno) {
		boardDao.remove(bno);
	}
}
