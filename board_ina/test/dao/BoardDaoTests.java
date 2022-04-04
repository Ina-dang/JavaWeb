package dao;

import org.junit.Test;

import domain.Criteria;
import domain.PageDto;
import lombok.extern.log4j.Log4j;

@Log4j
public class BoardDaoTests {
	private BoardDao boardDao = BoardDao.getInstance();
	
	@Test
	public void testList(){
//		boardDao.list(new Criteria()).forEach(System.out :: println);
//		Criteria cri = new Criteria(5, 5, 1);
//		boardDao.list(new Criteria()).forEach(System.out :: println);
	}
	
	@Test
	public void testDto() {
		PageDto dto = new PageDto(10, new Criteria(11, 5, 1));
		System.out.println(dto);
	}
}
