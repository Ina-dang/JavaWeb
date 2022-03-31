package service;

import dao.MemberDao;
import domain.Member;

public class MemberService {
	private static MemberService memberService = new MemberService();
	public static MemberService getInstance() {
		return memberService;
	}
	private MemberService() {}
	
	private MemberDao memberDao = MemberDao.getInstance();
	
	public void register(Member member) {
		memberDao.register(member);
	}
	
	public Member login(Member member) {
		return memberDao.login(member.getId(), member.getPw());
	}
	
	public Member get(String id) {
		return memberDao.get(id);
	}
	public Member findBy(String email) {
		return memberDao.findBy(email);
	}
	public void updateAuthToken(Member member) {
		memberDao.updateAuthToken(member);
	}
	public void updateAuth(Member member) {
		memberDao.updateAuth(member);
		
	}
	
	//지저분해져서 뺌
//	//null이 아니라면 true >> 회원이 존재한다는 뜻
//	public boolean existBy(String id) {
//		return get(id) != null;
//	}
	
//	public boolean existByEmail(String email) {
//		return findBy(email) != null;
//	}
}
