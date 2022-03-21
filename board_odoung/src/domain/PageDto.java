package domain;

import lombok.Getter;
import lombok.ToString;

@Getter //셋터필요없고 생성자로 다 할거
@ToString
//페이징 버튼 담당
public class PageDto {
	private static final int PAGE_COUNT = 10; //한페이지에 몇 개 보여주는지
	
	private int start; //하단에 보여질 페이지의 시작점 ex 시작숫자 1, 13
 	private int end; //하단에 보여질 페이지의 끝점 ex 끝숫자 페이지 마지막 24
	private int total; //게시글총갯스
	private boolean next; //다음페이지
	private boolean prev; //이전페이지
	private Criteria cri; //pageNum, amount

	
	
	// total, page, amount
	public PageDto(int total, Criteria cri) {
		this.total = total;
		this.cri = cri;
		
		//1페이지 10 
		//2페이지 10
		//..9도 10
		//11부터 20
		end = (cri.getPageNum() + (PAGE_COUNT - 1)) / PAGE_COUNT * PAGE_COUNT;
		start = end - PAGE_COUNT + 1;
		
		int realEnd = (total + (cri.getAmount()-1)) /cri.getAmount();
		if (realEnd < end) {
			end = realEnd;
		}
		
		prev = cri.getPageNum() > 1;
		next = cri.getPageNum() < realEnd;
				
	}
	public static void main(String[] args) {
//		int i = 11;
//		int result = (i+9) / 10 * 10;
//		
//		System.out.println(result);
//		System.out.printf("%10s %10s %10s %10s %n", "origin", "floor", "round", "ceil");
//		for(int j = 1; j <= 30; j++) {
//			System.out.printf("%10d %10d %10d %10d %n", j, j/10*10, (j+5)/10*10, (j+9)/10*10);
//		}
		
		PageDto dto = new PageDto(190, new Criteria(11, 10, 1));
		System.out.println(dto);
	}
}
