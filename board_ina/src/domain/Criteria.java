package domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data @NoArgsConstructor @AllArgsConstructor
public class Criteria {
	private int pageNum = 1;
	private int amount = 10;
	private int category = 1;
	
	
	public String getParams() {
		return "?amount=" + amount + "&category=" + category;
	}
	
	public String getParams2() {
		return getParams() + "&pageNum=" + pageNum;
	}
}
