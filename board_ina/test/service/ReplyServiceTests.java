package service;

import static org.junit.Assert.assertNotNull;

import java.util.List;

import org.junit.Test;

import domain.Reply;
import lombok.extern.log4j.Log4j;

@Log4j
public class ReplyServiceTests {
	
	private ReplyService replyService = ReplyService.getInstance();
	
	@Test
	public void testExist() {
		assertNotNull(replyService);
		log.info(replyService);
	}
	
	@Test //nullPointerException
	public void testList() {
		Reply reply = new Reply();
		List<Reply> list = replyService.list(reply.getBno());
		list.forEach(log::info);
	}
	
	
}
