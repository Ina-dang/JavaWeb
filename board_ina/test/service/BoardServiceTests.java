package service;

import static org.junit.Assert.assertNotNull;

import org.junit.Test;

import lombok.extern.log4j.Log4j;

@Log4j
public class BoardServiceTests {
	private BoardService boardService = BoardService.getInstance();
	
	@Test
	public void testExist() {
		assertNotNull(boardService);
		log.info(boardService);
	}
	
}
