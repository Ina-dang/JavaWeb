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
	
	private BoardService() {};
	
	//글 목록
	public List<Board> list(){
		return boardDao.list();
	}
	
	
	
}
