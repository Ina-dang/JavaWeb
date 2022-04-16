package dao;

import static org.junit.Assert.assertNotNull;

import org.junit.Test;

import lombok.extern.log4j.Log4j;

@Log4j
public class ReplyDaoTests {
	
	private ReplyDao replyDao = ReplyDao.getInstance();
	
	@Test
	public void testExist() {
		assertNotNull(replyDao);
	}
	
	@Test
	public void testList() {
		
	}
	@Test
	public void testGet() {
		
	}
	@Test
	public void testRegister() {
		
	}
	@Test
	public void testModify() {
		
	}
	@Test
	public void testRemove() {
		
	}
}
