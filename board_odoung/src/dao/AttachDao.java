package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import domain.Attach;
import domain.Board;
import domain.Criteria;
import utils.DBConn;

public class AttachDao {
	private static AttachDao attachDao = new AttachDao();
	public static AttachDao getInstance() {
		return attachDao;
	}
	private AttachDao() {
	}
	
	public List<Attach> list(Long bno) {
		List<Attach> list = new ArrayList<Attach>();
		try {
			Connection conn = DBConn.getConnection();
			
			// 문장 생성
			String sql = "SELECT /*+ INDEX(TBL_ATTACH IDX_ATTACH_UUID_BNO) */ * FROM TBL_ATTACH WHERE BNO = ?\n";
			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setLong(1, bno);
			
			// 결과집합 생성
			ResultSet rs = pstmt.executeQuery();
			
			// 결과집합 순회 후 데이터 바인딩
			while(rs.next()) {
				int idx = 1;
				Attach attach = new Attach(
						rs.getString(idx++),
						rs.getString(idx++),
						rs.getString(idx++),
						rs.getBoolean(idx++),
						rs.getInt(idx++),
						bno);
				list.add(attach);
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}
	
	public void insert(Attach attach) {
		try {
			Connection conn = DBConn.getConnection();
			
			// 문장 생성
			String sql = "INSERT INTO TBL_ATTACH "
					+ "VALUES (?, ?, ?, ?, ?, ?)";
			PreparedStatement pstmt = conn.prepareStatement(sql);
			
			// 파라미터 바인딩
			int idx = 1;
			//시퀀스생성 (문자생성과는 별개)
			pstmt.setString(idx++,  attach.getUuid());
			pstmt.setString(idx++, attach.getOrigin());
			pstmt.setString(idx++, attach.getPath());
			pstmt.setBoolean(idx++, attach.isImage());
			pstmt.setInt(idx++, attach.getOrd());
			pstmt.setLong(idx++, attach.getBno());
			
			// 문장 실행(반영)
			pstmt.executeUpdate();
		} catch (Exception e) {
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
}
