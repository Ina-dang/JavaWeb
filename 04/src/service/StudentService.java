package service;

import java.util.List;

import dao.StudentDao;
import domain.Student;

public class StudentService {
	private StudentDao studentDao = new StudentDao(); //원래는 싱글턴해야함
	
	//요청은 전부다 컨트롤러, 화면전달은 전체다 뷰한테 
	public List<Student> list(){
		return studentDao.list();
	}
	//이전에 이 출력을 app에서 했지만 
}
