package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import domain.Board;
import domain.Member;
import utils.DBConn;

public class MemberDao {
	private static MemberDao memberDao = new MemberDao();
	public static MemberDao getInstance() {
		return memberDao;
	}
	private MemberDao() {}
	
	public List<Board> list() {
		List<Board> list = new ArrayList<Board>();
		try {
			//클래스 로드
			Class.forName("oracle.jdbc.driver.OracleDriver");
			// connection 취득
			Connection conn = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521/xe", 
					"odoung", "1234");
			
			// 문장 생성
			String sql = "SELECT BNO, TITLE, HITCOUNT, \r\n" + 
					"CASE\r\n" + 
					"    WHEN SYSDATE - REGDATE > 1 THEN TO_CHAR(REGDATE, 'YY/MM/DD')\r\n" + 
					"    ELSE TO_CHAR(REGDATE, 'HH24:MI:SS')\r\n" + 
					"END REGDATE,\r\n" + 
					"WRITER FROM TBL_BOARD ORDER BY 1 DESC";
			PreparedStatement pstmt = conn.prepareStatement(sql);
			
			// 결과집합 생성
			ResultSet rs = pstmt.executeQuery();
			
			// 결과집합 순회 후 데이터 바인딩
			while(rs.next()) {
				Board board = new Board(rs.getLong(1), rs.getString(2), null, 
						rs.getInt(3), rs.getString(4), rs.getString(5));
				list.add(board);
			}
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return list;
	}
	public void register(Member member) {
		try {
			Connection conn = DBConn.getConnection();
			
			// 문장 생성
			String sql = "INSERT INTO TBL_MEMBER VALUES (?, ?, ?)";
			PreparedStatement pstmt = conn.prepareStatement(sql);
			
			// 파라미터 바인딩
			int idx = 1;
			pstmt.setString(idx++, member.getId());
			pstmt.setString(idx++, member.getPw());
			pstmt.setString(idx++, member.getName());
			
			// 문장 실행(반영)
			pstmt.executeUpdate();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void modify(Member member) {
		try {
			//클래스 로드
			Class.forName("oracle.jdbc.driver.OracleDriver");
			// connection 취득
			Connection conn = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521/xe", 
					"odoung", "1234");
			
			// 문장 생성
			String sql = "UPDATE TBL_MEMBER SET\r\n" + 
					"PW = ?,\r\n" + 
					"NAME = ?\r\n" + 
					"WHERE ID = ?";
			PreparedStatement pstmt = conn.prepareStatement(sql);
			
			// 파라미터 바인딩
			int idx = 1;
			pstmt.setString(idx++, member.getPw());
			pstmt.setString(idx++, member.getName());
			pstmt.setString(idx++, member.getId());
			
			// 문장 실행(반영)
			pstmt.executeUpdate();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void remove(String id) {
		try {
			//클래스 로드
			Class.forName("oracle.jdbc.driver.OracleDriver");
			// connection 취득
			Connection conn = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521/xe", 
					"odoung", "1234");
			
			// 문장 생성
			String sql = "DELETE TBL_MEMBER \r\n" + 
					"WHERE ID = ?";
			PreparedStatement pstmt = conn.prepareStatement(sql);
			
			// 파라미터 바인딩
			pstmt.setString(1, id);
			
			// 문장 실행(반영)
			pstmt.executeUpdate();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	public Member login(String id, String pw) {
		Member member = null;
		try {
			//클래스 로드
			Class.forName("oracle.jdbc.driver.OracleDriver");
			// connection 취득
			Connection conn = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521/xe", 
					"odoung", "1234");
			
			// 문장 생성
			String sql = "SELECT * FROM TBL_MEMBER\r\n" + 
					"WHERE ID = ? AND PW = ?";
			PreparedStatement pstmt = conn.prepareStatement(sql);
			int idx = 1;
			pstmt.setString(idx++, id.trim());
			pstmt.setString(idx++, pw.trim());
			
			// 결과집합 생성
			ResultSet rs = pstmt.executeQuery();
			
			// 결과집합 순회 후 데이터 바인딩
			while(rs.next()) {
				int idx2 = 1;
				member = new Member(rs.getString(idx2++), rs.getString(idx2++), rs.getString(idx2++));
			}
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return member;
	}
}
