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
}
