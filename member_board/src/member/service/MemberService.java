package member.service;

import java.util.List;

import member.vo.MemberVo;

public interface MemberService {
	//인터페이스 >> 추상메서드 상태
	// 이걸사용해서 구현
	List<MemberVo> list(String keyword);

	void register(MemberVo memberVo);

	void remove(String id);

	MemberVo login(String id, String pwd);
}
