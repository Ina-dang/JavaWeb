package service;

import java.util.List;

import dao.MemberDao;
import vo.MemberVo;

public class MemberServiceImpl implements MemberService{
	//다형성으로 싱글턴만들기
	private static final MemberService MemberService = new MemberServiceImpl();
	public static MemberService GetInstance() {
		return MemberService;
	}
	private MemberServiceImpl() {}
	
	private MemberDao memberDao = MemberDao.getInstance();

	@Override
	public List<MemberVo> list() {
		return memberDao.list();
	}
	
	@Override
	public void register(MemberVo memberVo) {
		memberDao.register(memberVo);
	}
	
	@Override
	public MemberVo login(String id, String pw) {
		return memberDao.login(id,pw);
	};
}
