package member.service;

import java.util.List;

import member.dao.MemberDao;
import member.vo.MemberVo;

public class MemberServiceImpl implements MemberService{
	//다형성으로 만듦
	private static final MemberService MemberService = new MemberServiceImpl();
	
	public static MemberService GetInstance() {
		return MemberService;
	}
	
	private MemberServiceImpl() {
	}
	
	private MemberDao memberDao = MemberDao.getInstance();
	
	@Override
	public List<MemberVo> list() {
		return memberDao.list();
	}

	@Override
	public void register(MemberVo memberVo) {
		// TODO Auto-generated method stub
		memberDao.register(memberVo);
	}

	@Override
	public void remove(String id) {
		// TODO Auto-generated method stub
		memberDao.remove(id);
	}
}
