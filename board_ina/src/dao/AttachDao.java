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
			String sql = "SELECT /*+ INDEX(T_ATTACH IDX_ATTACH_UUID_BNO) */ * FROM T_ATTACH WHERE BNO = ?\n";
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
			String sql = "INSERT INTO T_ATTACH "
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
	
	public void remove(Long bno) {
		try {
			Connection conn = DBConn.getConnection();
			
			// 문장 생성
			String sql = "DELETE T_ATTACH\r\n" + 
					"WHERE BNO = ?";
			PreparedStatement pstmt = conn.prepareStatement(sql);
			
			// 파라미터 바인딩
			pstmt.setLong(1, bno);
			
			// 문장 실행(반영)
			pstmt.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
