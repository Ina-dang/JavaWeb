package vo;

import java.sql.Date;

import lombok.Data;


@Data
public class MemberVo {
	private final String id;
	private final String pw;
	private final String name;
	private final String nick;
	private final String email;
	private final Date joindate;
	}
