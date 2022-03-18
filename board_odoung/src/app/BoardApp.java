package app;

import java.util.Scanner;

import service.BoardService;
import service.MemberService;
import service.ReplyService;

public class BoardApp {
	public static void main(String[] args) {
		BoardService boardService = BoardService.getInstance();
//		MemberService memberService = new MemberService();
		ReplyService replyService = new ReplyService();
		Scanner scanner = new Scanner(System.in);
		
		// /board_odoung/board/list 접속주소  게시글 목록이 표의 형태로 화면에 출력
		// controller 제작, jsp 제작, service 수정
		// localhost odoung 스키마 >> 이관 >> 도메인 odoung 스키마
		
		
		for(;;){
			System.out.println("1. 댓글 목록 조회  2. 댓글 등록  3. 댓글 삭제 4. 게시글 상세조회");
			int input = Integer.parseInt(scanner.nextLine()); 
			switch (input) {
			case 1:
				replyService.list();
				break;
			case 2:
				replyService.register();
				break;
			case 3:
				replyService.remove();
				break;
			case 4:
				boardService.get(1L);
				break;

			default:
				break;
			}
			
//			System.out.println("1. 목록 조회  2. 등록  3. 수정  4. 삭제");
//			if(memberService.member == null) { // 비로그인
//				System.out.println("1. 회원가입  2. 로그인  ");
//				int input = Integer.parseInt(scanner.nextLine());
//				switch (input) {
//				case 1:
//					memberService.register();
//					break;
//				case 2:
//					memberService.login();
//					break;
//				}
//			}
//			else { // 로그인
//				System.out.println("1. 정보 수정  2. 탈퇴  3. 로그아웃");
//				int input = Integer.parseInt(scanner.nextLine());
//				switch (input) {
//				case 1:
//					memberService.modify();
//					break;
//				case 2:
//					memberService.remove();
//					break;
//				case 3 :
//					memberService.logout();
//					break;
//				}
//			}
		}
	}
}
