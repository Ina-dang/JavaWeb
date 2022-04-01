package service;

import dao.BoardDao;
import dao.MemberDao;
import dao.ReplyDao;
import domain.Member;
import utils.DBConn;

public class MemberService {
	private static MemberService memberService = new MemberService();
	public static MemberService getInstance() {
		return memberService;
	}
	private MemberService() {}
	
	private MemberDao memberDao = MemberDao.getInstance();
	private BoardDao boardDao = BoardDao.getInstance();
	private ReplyDao replyDao = ReplyDao.getInstance();
	
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
	public void modify(Member member) {
		memberDao.modify(member);
	}
	
//	//원래는 트랜잭션 처리 되어야함
//	public void remove(Member member) {
//		//원래 트랜잭션처리하는법
////		DBConn.getConnection().setAutoCommit(false); //오토 커밋 멈추게하고
//		//글삭제
//		boardDao.modifyNullByWriter(member.getId());
//		//댓글삭제
//		replyDao.modifyNullByWriter(member.getId());
//
//		//여기서 커밋 롤백 해야함
////		DBConn.getConnection().commit();
////		DBConn.getConnection().rollback();
//		
//		memberDao.remove(member.getId());
//		
//	}
	
	public void remove(Member member) {
		memberDao.remove(member.getId());
	}
}
