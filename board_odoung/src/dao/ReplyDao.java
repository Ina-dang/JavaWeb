package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import domain.Board;
import domain.Reply;
import service.ReplyService;
import utils.DBConn;

public class ReplyDao {
	private static ReplyDao replyDao = new ReplyDao();
	public static ReplyDao getInstance() {
		return replyDao;
	}
	private ReplyDao() {}
	
	public List<Reply> list(Long bno) {
		List<Reply> list = new ArrayList<Reply>();
		try {
			// connection 취득
			Connection conn = DBConn.getConnection();

			
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
			e.printStackTrace();
		}
		return list;
	}
	
	public void register(Reply reply) {
		try {
			// connection 취득
			Connection conn = DBConn.getConnection();

			
			// 문장 생성
			String sql = "INSERT INTO TBL_REPLY (RNO, CONTENT, BNO, WRITER)"
					+ " VALUES (SEQ_REPLY.NEXTVAL, ?, ?, ?)";
			PreparedStatement pstmt = conn.prepareStatement(sql);
			
			// 파라미터 바인딩
			pstmt.setString(1, reply.getContent());
			pstmt.setLong(2, reply.getBno());
			pstmt.setString(3, reply.getWriter());
			
			// 문장 실행(반영)
			pstmt.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
							//댓글 개별삭제 | bno를통한 삭제 >> 딸린댓글전체삭제
	public void remove(Long rno) {
		try {
			// connection 취득
			Connection conn = DBConn.getConnection();

			
			// 문장 생성
			String sql = "DELETE TBL_REPLY\r\n" + 
					"WHERE RNO = ?";
			PreparedStatement pstmt = conn.prepareStatement(sql);
			
			// 파라미터 바인딩
			pstmt.setLong(1, rno);
			
			// 문장 실행(반영)
			pstmt.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
	
	
	public Reply get(Long rno) {
		Reply reply = null;
		try {
			Connection conn = DBConn.getConnection();
			
			String sql = "SELECT * FROM TBL_REPLY " + 
					"WHERE RNO = ?";
			PreparedStatement pstmt = conn.prepareStatement(sql);
			
			// 파라미터 바인딩
			pstmt.setLong(1, rno);
			
			// 결과집합 생성
			ResultSet rs = pstmt.executeQuery();
			
			// 결과집합 순회 후 데이터 바인딩
			while(rs.next()) {
				int idx = 1;
				reply = new Reply(rs.getLong(idx++), rs.getString(idx++), rs.getString(idx++), 
						rs.getLong(idx++), rs.getString(idx++));
				
			pstmt.executeUpdate();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return reply;
	}
	
	//댓글딸린 글삭제
	public void removeAll(Long bno) {
		try {
			Connection conn = DBConn.getConnection();
			
			String sql = "DELETE TBL_REPLY " + 
					"WHERE BNO = ?";
			PreparedStatement pstmt = conn.prepareStatement(sql);
			
			// 파라미터 바인딩
			pstmt.setLong(1, bno);
			
			pstmt.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}		
	}
	
	
//	(글내용수정, 시간 현재시간)
	public void modify(Reply reply) {
		try {
			Connection conn = DBConn.getConnection();
			
			// 쿼리
			String sql = "UPDATE TBL_REPLY SET\r\n" + 
					"CONTENT = ?,\r\n" + 
					"REGDATE = SYSDATE\r\n" + 
					"WHERE RNO = ?";
			
			PreparedStatement pstmt = conn.prepareStatement(sql);
			
			//파라미터바인딩
			pstmt.setString(1, reply.getContent());
			pstmt.setLong(2, reply.getRno());
			
			pstmt.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
