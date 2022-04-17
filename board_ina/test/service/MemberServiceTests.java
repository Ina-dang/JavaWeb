package service;

import static org.junit.Assert.assertNotNull;

import java.util.List;

import org.junit.Test;

import dao.MemberDao;
import domain.Member;
import lombok.extern.log4j.Log4j;

@Log4j
public class MemberServiceTests {
	private MemberService memberService = MemberService.getInstance();
	private MemberDao memberDao = MemberDao.getInstance();
	
	@Test
	public void testExist() {
		assertNotNull(memberService);
		log.info(memberService);
	}
	
	@Test
	public void testLogin() {
		Member member = new Member();
		memberDao.login("inadang", "1234");
		log.info(memberDao.login("inadang", "1234"));
		assertNotNull(member);
	}
	
	@Test
	public void testJoin() {
		Member member = new Member("joon", "1234", "주우우운");
		memberDao.join(member);
		log.info(member);
		assertNotNull(member);
	}
	
	@Test
	public void modify() {
		Member member = new Member();
		member.setId("hwhw2");
		member.setName("으아아아");
		memberDao.modify(member);
		log.info(member); 
	}
	
	@Test
	public void remove() {
		String id = "hwhw1";
		memberDao.remove(id);
		log.info(id);
	}
}
