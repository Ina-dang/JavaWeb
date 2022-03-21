package domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data @NoArgsConstructor @AllArgsConstructor
@Builder
public class Board {
	// int(기본형), Integer(참조형) : null 처리 가능여부 
	private Long bno ; // PK
	private String title;
	private String content;
	private int hitcount; // 0
	private String regDate;
	
	private String writer; // FK
	// 아이디, 조회수, 작성시각
	
	public Board(Long bno, String title, String content, String writer) {
		this.bno = bno;
		this.title = title;
		this.content = content;
		this.writer = writer;
	}
	
	public static void main(String[] args) {
		Board board = new Board(1L, "제목", "내용", "작성자");
		System.out.println(board);
		
		Board board2 = new Board();
		board2.setBno(2L);
		board2.setTitle("제목");
		board2.setContent("내용");
		board2.setWriter("작성자");
		System.out.println(board2);
		
		Board board3 = Board.builder().bno(3L).title("제목").content("내용").build();
		System.out.println(board3);
	}
}
