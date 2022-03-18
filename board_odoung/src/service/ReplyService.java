package service;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import dao.BoardDao;
import dao.ReplyDao;
import domain.Board;
import domain.Reply;

public class ReplyService {
	List<Reply> replies = new ArrayList<>();
	
	{
//		members.add(new Member());
	}
	
	public void register() {
		Scanner scanner = new Scanner(System.in);
		
		System.out.println("글번호 > ");
		Long bno = Long.parseLong(scanner.nextLine());
		System.out.println("내용 > ");
		String content = scanner.nextLine();
		System.out.println("작성자 > ");
		String writer = scanner.nextLine();
		
		Reply reply = new Reply(null, content, null, bno, writer); 

		new ReplyDao().register(reply);
	}
	
	public void remove() {
		Scanner scanner = new Scanner(System.in);
		
		System.out.println("댓글번호 > ");
		Long rno = Long.parseLong(scanner.nextLine());
		
		new ReplyDao().remove(rno);
	}
	
	public void list() {
		System.out.println("댓글 목록 조회");
		Scanner scanner = new Scanner(System.in);
		
		System.out.println("글번호 > ");
		Long bno = Long.parseLong(scanner.nextLine());
		
		new ReplyDao().list(bno).forEach(System.out::println);
	}
}
