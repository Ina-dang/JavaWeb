package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import domain.Board;
import domain.Criteria;
import utils.DBConn;

public class BoardDao {
	private static BoardDao boardDao = new BoardDao();
	public static BoardDao getInstance() {
		return boardDao;
	}
	private BoardDao() {}
	
	public List<Board> list(Criteria cri) {
		List<Board> list = new ArrayList<>();
		try {
			Connection conn = DBConn.getConnection();
			// 문장 생성
			String sql = "SELECT * FROM \r\n" + 
					"(\r\n" + 
					"SELECT \r\n" + 
					"    /*+ INDEX_DESC(T_BOARD PK_BOARD) */\r\n" + 
					"    BNO, TITLE, HITCOUNT, \r\n" + 
					"    CASE\r\n" + 
					"        WHEN SYSDATE - REGDATE > 1 THEN TO_CHAR(REGDATE, 'YY/MM/DD')\r\n" + 
					"        ELSE TO_CHAR(REGDATE, 'HH24:MI:SS')\r\n" + 
					"    END REGDATE,\r\n" +
					"    CATEGORY,\r\n" + 
					"    WRITER,\r\n" + 
					"    ROWNUM RN,\r\n" + 
					"    (SELECT COUNT(bno) FROM T_BOARD) CNT\r\n" + 
					"	FROM T_BOARD\r\n" + 
					"	WHERE CATEGORY = ? \r\n" + 
					"	AND ROWNUM <= ? \r\n" + 
					")\r\n" + 
					"WHERE RN > ?";
			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1,  cri.getCategory());
			pstmt.setInt(2,  cri.getPageNum() * cri.getAmount());
			pstmt.setInt(3,  (cri.getPageNum() - 1 ) * cri.getAmount());
			
			// 결과집합 생성
			ResultSet rs = pstmt.executeQuery();
			
			// 결과집합 순회 후 데이터 바인딩
			while(rs.next()) {
				Board board = new Board(rs.getLong(1), rs.getString(2), null, 
						rs.getInt(3), rs.getString(4), rs.getInt(5), rs.getString(6));
//				board.setReplyCnt(rs.getInt("REPLYCNT")); 댓글수
				list.add(board);
			}
			
			// 결과집합 순회 후 데이터 바인딩

			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return list;
	}
	
	//게시글 갯수 세기(criteria)
	public int count(Criteria cri) {
		int count = 0;
		try {
			Connection conn = DBConn.getConnection();
			// 문장 생성
			String sql = "SELECT COUNT(*) FROM T_BOARD WHERE CATEGORY = ?";
			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, cri.getCategory());
			
			// 결과집합 생성
			ResultSet rs = pstmt.executeQuery();
			
			// 결과집합 순회 후 데이터 바인딩
			if(rs.next()) {
				count = rs.getInt(1);
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return count;
	}

}
