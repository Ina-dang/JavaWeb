package dao;

import java.sql.Connection;
import java.sql.DriverManager;
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
	private BoardDao() {
	}
	
	public List<Board> list(Criteria cri) {
		List<Board> list = new ArrayList<Board>();
		try {
			Connection conn = DBConn.getConnection();
			
			// 문장 생성
			String sql = "SELECT B.*, (SELECT COUNT(*) FROM tbl_reply R WHERE R.BNO = B.BNO) REPLYCNT\n" + 
					"FROM (\n" + 
					"    SELECT\n" + 
					"        /*+ INDEX_DESC(TBL_BOARD PK_BOARD)*/\n" + 
					"        BNO,\n" + 
					"        TITLE,\n" + 
					"        HITCOUNT, \n" + 
					"        CASE\n" + 
					"            WHEN SYSDATE - REGDATE > 1 THEN TO_CHAR(REGDATE, 'YY/MM/DD')\n" + 
					"            ELSE TO_CHAR(REGDATE, 'HH24:MI:SS')\n" + 
					"        END REGDATE,\n" + 
					"        CATEGORY, \n" + 
					"        WRITER, \n" +
					"        ROWNUM RN,\n" + 
					"        (SELECT COUNT(bno) FROM TBL_BOARD) CNT\n" + 
					"    FROM TBL_BOARD\n" + 
					"    WHERE CATEGORY = ? --카테고리 어떤거 쓸지\n" + 
					"    AND ROWNUM <=? --페이징하기위해\n" + 
					")B\n" + 
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
				board.setReplyCnt(rs.getInt("REPLYCNT"));
				list.add(board);
			}
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return list;
	}
	public void register(Board board) {
		try {
			Connection conn = DBConn.getConnection();
			
			String sql = "SELECT SEQ_BOARD.NEXTVAL FROM DUAL";
			PreparedStatement pstmt = conn.prepareStatement(sql);
			ResultSet rs = pstmt.executeQuery();
			rs.next();
			board.setBno(rs.getLong(1));
			
			// 문장 생성
			sql = "INSERT INTO TBL_BOARD(BNO, TITLE, CONTENT, WRITER, CATEGORY)"
					+ "VALUES (?, ?, ?, ?, ?)";
			pstmt = conn.prepareStatement(sql);
			
			// 파라미터 바인딩
			int idx = 1;
			//시퀀스생성 (문자생성관는 별개)
			pstmt.setLong(idx++,  board.getBno());
			pstmt.setString(idx++, board.getTitle());
			pstmt.setString(idx++, board.getContent());
			pstmt.setString(idx++, board.getWriter());
			pstmt.setInt(idx++, board.getCategory());
			
			// 문장 실행(반영)
			pstmt.executeUpdate();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void modify(Board board) {
		try {
			Connection conn = DBConn.getConnection();
			
			// 문장 생성
			String sql = "UPDATE TBL_BOARD SET\r\n" + 
					"TITLE = ?,\r\n" + 
					"CONTENT = ?,\r\n" + 
					"REGDATE = SYSDATE\r\n" + 
					"WHERE BNO = ?";
			PreparedStatement pstmt = conn.prepareStatement(sql);
			
			// 파라미터 바인딩
			pstmt.setString(1, board.getTitle());
			pstmt.setString(2, board.getContent());
			pstmt.setLong(3, board.getBno());
			
			// 문장 실행(반영)
			pstmt.executeUpdate();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void remove(Long bno) {
		try {
			Connection conn = DBConn.getConnection();
			
			// 문장 생성
			String sql = "DELETE TBL_BOARD\r\n" + 
					"WHERE BNO = ?";
			PreparedStatement pstmt = conn.prepareStatement(sql);
			
			// 파라미터 바인딩
			pstmt.setLong(1, bno);
			
			// 문장 실행(반영)
			pstmt.executeUpdate();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	public Board get(Long bno) {
		Board board = null;
		try {
			Connection conn = DBConn.getConnection();
			
			String sql = "UPDATE TBL_BOARD SET\r\n" + 
					"HITCOUNT = HITCOUNT + 1\r\n" + 
					"WHERE BNO = ?";
			PreparedStatement pstmt = conn.prepareStatement(sql);
			
			// 파라미터 바인딩
			pstmt.setLong(1, bno);
			
			// 문장 실행(반영)
			pstmt.executeUpdate();
			
			// 문장 생성
			sql = "SELECT BNO, TITLE, CONTENT, HITCOUNT, \r\n" + 
					"CASE\r\n" + 
					"    WHEN SYSDATE - REGDATE > 1 THEN TO_CHAR(REGDATE, 'YY/MM/DD')\r\n" + 
					"    ELSE TO_CHAR(REGDATE, 'HH24:MI:SS')\r\n" + 
					"END REGDATE,\r\n" + 
					"CATEGORY, WRITER FROM TBL_BOARD WHERE BNO = ?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setLong(1, bno);
			
			// 결과집합 생성
			ResultSet rs = pstmt.executeQuery();
			
			// 결과집합 순회 후 데이터 바인딩
			while(rs.next()) {
				int idx = 1;
				board = new Board(rs.getLong(idx++), rs.getString(idx++), rs.getString(idx++), 
						rs.getInt(idx++), rs.getString(idx++), rs.getInt(idx++), rs.getString(idx++));
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return board;
	}
	
	
	public int count(Criteria cri) {
		int count = 0;
		try {
			Connection conn = DBConn.getConnection();
			// 문장 생성
			String sql = "SELECT COUNT(*) FROM TBL_BOARD WHERE CATEGORY = ?";
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
	
	
	
	
	public static void main(String[] args) {
		BoardDao boardDao = BoardDao.getInstance();
		boardDao.list(new Criteria()).forEach(System.out::println);
		System.out.println("===========================");
		Criteria cri = new Criteria(2, 10, 1);
		boardDao.list(cri).forEach(System.out::println);
		System.out.println("==========================");
		cri.setAmount(20);
		boardDao.list(cri).forEach(System.out::println);
		
	}
}
