package service;

import java.util.List;

import dao.MemberDao;

public class MemberServiceImpl implements MemberService{
	//다형성으로
	private static final MemberService MemberService = new MemberServiceImpl();
	
	public static MemberService GetInstance() {
		return MemberService;
	}
	private MemberServiceImpl() {}
	
	private MemberDao memberDao = MemberDao.getInstance();

	@Override
	public List<MemberDao> list() {
		// TODO Auto-generated method stub
		return null;
	};
}
