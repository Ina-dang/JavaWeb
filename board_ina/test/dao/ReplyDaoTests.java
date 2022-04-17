package dao;


import static org.junit.Assert.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

import domain.Reply;
import lombok.extern.log4j.Log4j;

@Log4j
public class ReplyDaoTests {
	
	private ReplyDao replyDao = ReplyDao.getInstance();
	
	@Test
	public void testExist() {
		assertNotNull(replyDao);
		log.info(replyDao); 
	}
	
	@Test
	public void testList() {
		Reply reply = new Reply();
		List<Reply> list = replyDao.list(reply.getBno());
		list.forEach(log::info);
	}
	 
	@Test //미적용
	public void testGet() {
		Long rno = 46L;
		log.info(replyDao.get(rno));
//		assertNotNull(rno);
		
	}
	@Test 
	public void testRegister() {
		Reply reply = new Reply(null, "heeeey", null, 169L, "merona");
		Reply reply1 = new Reply(null, "hi", null, 169L, "merona");
		replyDao.register(reply); 
		replyDao.register(reply1);
		log.info(reply);
		assertNotNull(reply);
	}
	@Test
	public void testModify() {
		Reply reply = new Reply(46L, "update relpy", null, 169L, "merona");
		
		replyDao.modify(reply);
		log.info(reply);
	}
	
	
	@Test
	public void testRemove() {
		Long rno = 42L;
		replyDao.remove(rno);
		log.info(rno);
	}
}
