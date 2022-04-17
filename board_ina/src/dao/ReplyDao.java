package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import domain.Reply;
import utils.DBConn;

public class ReplyDao {
	private static ReplyDao replyDao = new ReplyDao();
	public static ReplyDao getInstance() {
		return replyDao;
	}
	private ReplyDao() {}
	
	public List<Reply> list(Long bno){
		List<Reply> list = new ArrayList<Reply>();
		try {
			Connection conn = DBConn.getConnection();
			
			String sql = "SELECT * FROM T_REPLY WHERE BNO = ?";
			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setLong(1, bno);
			ResultSet rs = pstmt.executeQuery();
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
			Connection conn = DBConn.getConnection();
			
			String sql = "INSERT INTO T_REPLY (RNO, CONTENT, BNO, WRITER)"
					+ " VALUES (SEQ_REPLY.NEXTVAL, ?, ?, ?)";
			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, reply.getContent());
			pstmt.setLong(2, reply.getBno());
			pstmt.setString(3, reply.getWriter());
			pstmt.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void remove(Long rno) {
		try {
			// connection 취득
			Connection conn = DBConn.getConnection();

			
			// 문장 생성
			String sql = "DELETE T_REPLY\r\n" + 
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
			
			String sql = "SELECT * FROM T_REPLY \" + \r\n" + 
					"					\"WHERE RNO = ?";
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
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return reply;
	}
	
	
//	(글내용수정, 시간 현재시간)
	public void modify(Reply reply) {
		try {
			Connection conn = DBConn.getConnection();
			
			// 쿼리
			String sql = "UPDATE T_REPLY SET\r\n" + 
					"CONTENT = ?,\r\n" + 
					"REGDATE = SYSDATE\r\n" + 
					"WHERE RNO = ?";
			
			PreparedStatement pstmt = conn.prepareStatement(sql);
			
			//파라미터바인딩
			pstmt.setString(1, reply.getContent());
			pstmt.setLong(2, reply.getRno());
			
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
