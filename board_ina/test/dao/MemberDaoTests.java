package dao;

import static org.junit.Assert.assertNotNull;

import org.junit.Test;

import controller_member.MemberAuth;
import domain.Member;
import lombok.extern.log4j.Log4j;

@Log4j //롬복제공
public class MemberDaoTests {
	private MemberDao memberDao = MemberDao.getInstance();
//롬복없으면 log라는 인스턴스 못찾아서 이거 해줘야함
//	private static final Logger log = Logger.getLogger(MemberDao.class); 
	
	@Test //이것 JUnit이 제공
	//대상 객체 존재여부 확인
	public void testExist() { 
		log.info(memberDao);
	}
	
	@Test //메서드테스트 
	public void testLogin() {
		Member member = memberDao.login("inadang", "1234");
		log.info(member);
		
		//실패시키기 Junit Assert내에 들어있음
		assertNotNull(member);
	}
	
	@Test 
	public void testJoin() {
		Member member = new Member();
		member.setId("jinn");
		member.setId("jinn"); 
		member.setPw("1234");
		member.setName("쥐이이인"); 
		memberDao.join(member);
		
		log.info(member);
	}
	
	@Test
	public void testAuth() {
		MemberAuth memberAuth = new MemberAuth();
		memberAuth.getClass();
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
