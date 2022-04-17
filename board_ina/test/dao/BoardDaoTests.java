package dao;

import static org.junit.Assert.assertNotNull;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

import domain.Board;
import domain.Criteria;
import lombok.extern.log4j.Log4j;

@Log4j
public class BoardDaoTests {
	private BoardDao boardDao = BoardDao.getInstance();
	
	
	@Test 
	public void testExist() {
		assertNotNull(boardDao);
		log.info(boardDao); 
	}
	 
	@Test 
	public void testList() {
		Criteria cri = new Criteria();
		cri.setAmount(50);
		List<Board> board = boardDao.list(cri);
		assertNotNull(board);
		boardDao.list(cri);
		board.forEach(log::info);
	}
	
	@Test
	public void testGet() {
		boardDao.get(101L);
		log.info(boardDao.get(101L));
	}
	
	
	@Test
	public void testRegister() {
		Board board = new Board();
		List<Board> boards = new ArrayList<Board>();
		boards.size();
		board.setTitle("안녕하세요");
		board.setContent("안녕");
		board.setWriter("merona");
		boards.add(new Board(null, "하이하이", "하이",  2, "inadang"));
		boardDao.register(board);
		assertNotNull(boards);
		log.info(boards);
		
	}
	
	
	@Test
	public void testCount() {
		Criteria cri = new Criteria();
		cri.setAmount(50);
		List<Board> boards = boardDao.list(cri);
		log.info(boards.size());
		boardDao.count(cri);
	}
	
	@Test
	public void testModify() {
		Board board = new Board(101L, "update", "updatetete", 1);
		boardDao.modify(board);
		log.info(board);
		
	} 
	@Test
	public void testRemove() {
		Long bno = 62L;
		boardDao.remove(bno);
		log.info(bno);
	}
}
