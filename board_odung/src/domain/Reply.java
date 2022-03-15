package domain;

public class Reply {
	private Long rno;
	private String content;
	private String regDate;
	
	private Long bno;
	private String writer;
	
	public Reply() {}

	public Reply(Long rno, String content, String regDate, Long bno, String writer) {
		this.rno = rno;
		this.content = content;
		this.regDate = regDate;
		this.bno = bno;
		this.writer = writer;
	}

	public Long getRno() {
		return rno;
	}

	public void setRno(Long rno) {
		this.rno = rno;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getRegDate() {
		return regDate;
	}

	public void setRegDate(String regDate) {
		this.regDate = regDate;
	}

	public Long getBno() {
		return bno;
	}

	public void setBno(Long bno) {
		this.bno = bno;
	}

	public String getWriter() {
		return writer;
	}

	public void setWriter(String writer) {
		this.writer = writer;
	}
	
	
}
