package dao;

import static org.junit.Assert.assertNotNull;

import org.junit.Test;

import domain.Member;
import lombok.extern.log4j.Log4j;

@Log4j //롬복제공
public class MemberDaoTests {
	private MemberDao memberDao = MemberDao.getInstance();
//롬복없으면 log라는 인스턴스 못찾아서 이거 해줘야함
//	private static final Logger log = Logger.getLogger(MemberDao.class); 
	
//	@Test //이것 JUnit이 제공
//	//대상 객체 존재여부 확인
//	public void testExist() {
//		log.info(memberDao);
//	}
//	
//	@Test //메서드테스트
//	public void testLogin() {
//		Member member = memberDao.login("inadang", "1234");
//		log.info(member);
//		//기대값 : 로그인성공 하면 자바맨 실패하면 null 값
//		
//		//실패시키기 Junit Assert내에 들어있음
//		assertNotNull(member);
//	}
//	
//	@Test // 회원가입 하나하나씩 오류확인
//	public void testJoin() {
//		Member member = null;
////		Member member = new Member();
////		member.setId("javaman");
//		member.setId("javaman2"); //이때 DB에 실제로 들어감. 롤백해두됨
//		member.setPw("1234");
//		member.setName("가나다라");
//		
//		
//		memberDao.register(member);
//	}
	
	@Test
	public void testMyPage() {
		Member member = null;
		
		
	}
}
