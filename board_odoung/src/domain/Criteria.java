package domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

// DTO
// 모든게시글에서 Criteria를 쓸 애들은 페이지수량번호, 페이지수량 항상 가지고다녀야함
@Data @NoArgsConstructor @AllArgsConstructor
public class Criteria {
//	@NonNull //널값이 아닌 생성자에 의한 생성자 >> RequiredArgsConstructor
	private int pageNum = 1 ;
	private int amount = 10 ;
	private int category = 1;
	
	//(총게시글 갯수 + 9) / 어마운트 
	
	//하단에 페이지 버튼 객체 따로 만듦 >> 스프링 없어서 일단 다 써줘야함
	public String getParams2() {
		return getParams() + "&pageNum=" + pageNum;
	}
	
	public String getParams() {
		return	"?amount=" + amount + 
				"&category=" + category;
	}
}
