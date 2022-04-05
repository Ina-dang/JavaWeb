package service;

import java.util.List;

import dao.BoardDao;
import domain.Board;
import domain.Criteria;

public class BoardService {
	private static BoardService boardService = new BoardService();
	public static BoardService getInstance() {
		return boardService;
	}
	private BoardDao boardDao = BoardDao.getInstance();
	
	private BoardService() {};
	
	//글 목록
	public List<Board> list(Criteria cri){
		return boardDao.list(cri);
	}
	
	//게시글 갯수
	public int count(Criteria cri) {
		return boardDao.count(cri);
	}

	public Board get(Long bno) {
		Board board = boardDao.get(bno);
		//나중에 첨부파일도 뜨게 해야함
		return board;
	}
}
