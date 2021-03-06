package domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data @NoArgsConstructor @AllArgsConstructor
public class Member {
	private String id;
	private String pw;
	private String name;
	
	private String si;
	private String sgg;
	private String emd;
	private String roadAddr;
	private String addrDetail;
	private String zipNo;
	private String roadFullAddr;
	private String jibunAddr;
	
	private String email;
	
	private String auth;
	private String authToken;
	
	//3개월 지나면 휴면하려고 만듦
	private String joinDate;
	
	public Member(String id, String pw, String name) {
		this.id = id;
		this.pw = pw;
		this.name = name;
	}

	public Member(String id, String pw, String name, String email, String auth) {
		this.id = id;
		this.pw = pw;
		this.name = name;
		this.email = email;
	}

}
