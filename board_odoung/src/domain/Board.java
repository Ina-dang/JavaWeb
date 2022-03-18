package domain;

import java.lang.reflect.Field;
import java.util.Arrays;
import java.util.List;

public class Board {
	// int(기본형), Integer(참조형) : null 처리 가능여부 
	private Long bno ; // PK
	private String title;
	private String content;
	private int hitcount; // 0
	private String regDate;
	
	private String writer; // FK
	// 아이디, 조회수, 작성시각
	
	public Board() {
		// TODO Auto-generated constructor stub
	}
	
	public Board(Long bno, String title, String content, String writer) {
		this.bno = bno;
		this.title = title;
		this.content = content;
		this.writer = writer;
	}
	
	public Board(Long bno, String title, String content, int hitcount, String regDate, String writer) {
		super();
		this.bno = bno;
		this.title = title;
		this.content = content;
		this.hitcount = hitcount;
		this.regDate = regDate;
		this.writer = writer;
	}
	
	
	
	
	public Long getBno() {
		return bno;
	}

	public void setBno(Long bno) {
		this.bno = bno;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public int getHitcount() {
		return hitcount;
	}

	public void setHitcount(int hitcount) {
		this.hitcount = hitcount;
	}

	public String getRegDate() {
		return regDate;
	}

	public void setRegDate(String regDate) {
		this.regDate = regDate;
	}

	public String getWriter() {
		return writer;
	}

	public void setWriter(String writer) {
		this.writer = writer;
	}

	public static void main(String[] args) {
		Board b = new Board();
		b.hitcount= 100;
		List<Field> list = Arrays.asList(new Board().getClass().getFields());
		list.forEach(f -> {
			System.out.println(f.getType().isPrimitive());
			
			if(f.getType().isPrimitive()) {
				try {
					System.out.println(f.getInt(b));
				} catch (IllegalArgumentException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (IllegalAccessException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			System.out.println(String.format("%s %s %b", f.getType(), f.getName(), f.isAccessible()));
		});
	}
}
