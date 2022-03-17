package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import utils.DBConn;
import vo.MemberVo;

public class MemberDao {
	//싱글턴
	//자기 타입의 인스턴스를 private static 상태로
	private final static MemberDao dao = new MemberDao();
	//자기 타입의 메서드 리턴도 자기타입으로
	public static MemberDao getInstance() {
		return dao;
	}
	//그리고 기본 생성자 생성
	private MemberDao() {}

	
	private Connection conn;
	private PreparedStatement pstmt;
	
	public List<MemberVo> list() {
		List<MemberVo> list = new ArrayList<MemberVo>();
		
		try {
			conn = DBConn.getConnection();
			String query = "SELECT * FROM T_HOME";
			pstmt = conn.prepareStatement(query);
			ResultSet rs = pstmt.executeQuery();
			
			while (rs.next()) {
				int idx = 1;
				MemberVo vo = new MemberVo(
						rs.getString(idx++), //ID
						rs.getString(idx++), //pw
						rs.getString(idx++), //name
						rs.getString(idx++), //nick
						rs.getString(idx++), //email
						rs.getDate(idx++)	//joindate					
						);
				list.add(vo);
			}
			rs.close();
			conn.close();
			pstmt.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}
	
	public static void main(String[] args) {
		new MemberDao().list().forEach(System.out::println);
	}
	
	public void register(MemberVo memberVo) {
		try {
			conn = DBConn.getConnection();
			pstmt = conn.prepareStatement("INSERT INTO T_HOME VALUES(?, ?, ?, ?, ?, SYSDATE)");
			int idx = 1;
			pstmt.setString(idx++, memberVo.getId());
			pstmt.setString(idx++, memberVo.getPw());
			pstmt.setString(idx++, memberVo.getName());
			pstmt.setString(idx++, memberVo.getNick());
			pstmt.setString(idx++, memberVo.getEmail());
			
			pstmt.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public MemberVo login(String id, String pw) {
		MemberVo memberVo = null;
		try {
			conn = DBConn.getConnection();
			pstmt = conn.prepareStatement("SELECT * FROM T_HOME WHERE ID = ? AND PW = ? ");
			pstmt.setString(1, id);
			pstmt.setString(2, pw);
			
			ResultSet rs = pstmt.executeQuery();
			
			while (rs.next()) {
				int idx = 1;
				memberVo = new MemberVo(
						rs.getString(idx++),
						rs.getString(idx++),
						rs.getString(idx++),
						rs.getString(idx++),
						rs.getString(idx++),
						rs.getDate(idx++)
						);
			}
			rs.close();
			pstmt.close();
			conn.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return memberVo;
	}
}
