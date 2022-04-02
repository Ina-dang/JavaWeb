package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import domain.Board;
import utils.DBConn;

public class BoardDao {
	private static BoardDao boardDao = new BoardDao();
	public static BoardDao getInstance() {
		return boardDao;
	}
	private BoardDao() {}
	
	public List<Board> list() {
		List<Board> list = new ArrayList<>();
		try {
			Connection conn = DBConn.getConnection();
			
			// 문장 생성
			String sql = "SELECT BNO, TITLE, HITCOUNT, \r\n" + 
					"CASE\r\n" + 
					"    WHEN SYSDATE - REGDATE > 1 THEN TO_CHAR(REGDATE, 'YY/MM/DD')\r\n" + 
					"    ELSE TO_CHAR(REGDATE, 'HH24:MI:SS')\r\n" + 
					"END REGDATE,\r\n" + 
					"WRITER FROM T_BOARD ORDER BY 1 DESC";
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
}
