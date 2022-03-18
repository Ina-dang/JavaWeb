package domain;

public class Reply {
	public Long rno;
	public String content;
	public String regDate;
	
	public Long bno;
	public String writer;
	
	public Reply() {
		// TODO Auto-generated constructor stub
	}

	public Reply(Long rno, String content, String regDate, Long bno, String writer) {
		super();
		this.rno = rno;
		this.content = content;
		this.regDate = regDate;
		this.bno = bno;
		this.writer = writer;
	}

	@Override
	public String toString() {
		return String.format("Reply [rno=%s, content=%s, regDate=%s, bno=%s, writer=%s]", rno, content, regDate, bno,
				writer);
	}

	
}
