package dao;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

import domain.Board;
import lombok.extern.log4j.Log4j;

@Log4j
public class BoardDaoTests {
	private BoardDao boardDao = BoardDao.getInstance();
	
	@Test
	public void testExist() {
		log.info(boardDao);
	}
	
	@Test
	public void testList() {
		
	}
	@Test
	public void testGet() {
		
	}
	
	
	@Test
	public void testRegister() {
		Board board = new Board();
		List<Board> boards = new ArrayList<Board>();
		boards.size();
		board.setTitle("안녕하세요");
		board.setContent("안녕");
		board.setWriter("merona");
		boards.add(new Board(null, "하이하이", "하이", 2, "inadang"));
//		log.info(board);
		log.info(boards);
	}
	
	
	@Test
	public void testCount() {
		
	}
	@Test
	public void testModify() {
		
	}
	@Test
	public void testRemove() {
		
	}
	
}
