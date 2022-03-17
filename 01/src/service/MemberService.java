package service;

import java.util.List;

import dao.MemberDao;
import vo.MemberVo;

public interface MemberService {
	List<MemberVo> list();

	void register(MemberVo memberVo);

	MemberVo login(String id, String pw);
	
}
