package member.vo;

import java.sql.Date;

import lombok.Data;

@Data
public class MemberVo {
	private final String id; //비초기화상태라 어노테이션 주석하면 에러가 뜬다.
	private final String pwd; //상수는 항상 초기화 해줘야하고 Data어노테이션이 생성자를 자동으로 잔들어준다
	private final String name;
	private final String email;
	private final Date joinDate;
	/*
	 * 애들은 인스턴스변수 
	 * 인스턴스 변수는 인스턴스 초기화 시에 이루어진다
	 * 값이 비워진 상태에서 초기화 시키면 참조자료형이기때문에 null값으로 된다
	 * 
	 * 지금은 생성자가 있음 
	 * 얘에대한 값의 할당을 MemberVo생성시점으로 넘김
	 * 단 한 번 설정하고 값변경 못함
	 * 
	 * 그래서 세터가 없음 (final)이니까
	 */
}
