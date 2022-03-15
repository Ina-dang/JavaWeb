package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import domain.Student;
import utils.DBConn;

public class StudentDao {
	public List<Student> list() {
		List<Student> list = new ArrayList<Student>();
		//sql과 관련된 모든 메서드는 checked exception 메서드 예외처리해줘야함
		//그래서 try catch 해줘야함
		try {
			Connection conn = DBConn.getConnection(); //utils에 만들어둔 디비커넥션가져옴
			PreparedStatement pstmt = conn.prepareStatement("SELECT STUDNO, NAME, DEPTNO FROM STUDENT"); //sqlException
			ResultSet rs = pstmt.executeQuery(); //sqlException
			while(rs.next()) {
				int idx = 1;
				list.add(new Student(rs.getLong(idx++), rs.getString(idx++), rs.getLong(idx++)));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		System.out.println(list);
		return list;
	}
}
