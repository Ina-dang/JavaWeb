package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import domain.Board;
import domain.Reply;

public class ReplyDao {
	public List<Reply> list(Long bno) {
		List<Reply> list = new ArrayList<Reply>();
		try {
			//클래스 로드
			Class.forName("oracle.jdbc.driver.OracleDriver");
			// connection 취득
			Connection conn = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521/xe", 
					"odoung", "1234");
			
			// 문장 생성
			String sql = "SELECT * FROM TBL_REPLY WHERE BNO = ?";
			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setLong(1, bno);
			
			// 결과집합 생성
			ResultSet rs = pstmt.executeQuery();
			
			// 결과집합 순회 후 데이터 바인딩
			while(rs.next()) {
				int idx = 1;
				Reply reply = new Reply(rs.getLong(idx++), rs.getString(idx++), 
						rs.getString(idx++), rs.getLong(idx++), rs.getString(idx++));
				list.add(reply);
			}
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return list;
	}
	public void register(Reply reply) {
		try {
			//클래스 로드
			Class.forName("oracle.jdbc.driver.OracleDriver");
			// connection 취득
			Connection conn = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521/xe", 
					"odoung", "1234");
			
			// 문장 생성
			String sql = "INSERT INTO TBL_REPLY (RNO, CONTENT, BNO, WRITER)"
					+ " VALUES (SEQ_REPLY.NEXTVAL, ?, ?, ?)";
			PreparedStatement pstmt = conn.prepareStatement(sql);
			
			// 파라미터 바인딩
			pstmt.setString(1, reply.content);
			pstmt.setLong(2, reply.bno);
			pstmt.setString(3, reply.writer);
			
			// 문장 실행(반영)
			pstmt.executeUpdate();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	
	public void remove(Long rno) {
		try {
			//클래스 로드
			Class.forName("oracle.jdbc.driver.OracleDriver");
			// connection 취득
			Connection conn = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521/xe", 
					"odoung", "1234");
			
			// 문장 생성
			String sql = "DELETE TBL_REPLY\r\n" + 
					"WHERE RNO = ?";
			PreparedStatement pstmt = conn.prepareStatement(sql);
			
			// 파라미터 바인딩
			pstmt.setLong(1, rno);
			
			// 문장 실행(반영)
			pstmt.executeUpdate();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
}
