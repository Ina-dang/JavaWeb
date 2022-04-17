package dao;

import static org.junit.Assert.assertNotNull;

import java.util.List;

import org.junit.Test;

import domain.Attach;
import lombok.extern.log4j.Log4j;

@Log4j
public class AttachDaoTests {
	private AttachDao attachDao = AttachDao.getInstance();
	
	@Test
	public void testExists() {
		assertNotNull(attachDao);
		log.info(attachDao); 
	}
	 
	@Test
	public void testList() { 
		List<Attach> list = attachDao.list(202L);
		System.out.println(list);
		log.info(list);
	}
	
	@Test
	public void TestInsert() {
		Attach attach = new Attach();
		attach.setBno(8L);
		log.info(attach);
	}
	
	@Test
	public void TestRemove() {
		Long bno = 200L;
		attachDao.remove(bno);
	}
}
