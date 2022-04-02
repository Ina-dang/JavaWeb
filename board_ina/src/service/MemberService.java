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
	
	public Member login(Member member) {
		return memberDao.login(member.getId(), member.getPw());
	}
	public void join(Member member) {
		memberDao.join(member);
	}
	public Member idFind(String id) {
		return memberDao.idFind(id);
	}
	public Member emailFind(String email) {
		return memberDao.emailFind(email);
	}
	public void updateAuthToken(Member member) {
		memberDao.updateAuthToken(member);
	}
	public void updateAuth(Member member) {
		memberDao.updateAuth(member);
	}
	public void modify(Member member) {
		memberDao.modify(member);
	}
	public void remove(Member member) {
		memberDao.remove(member.getId());
	}
}
