package member.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import member.vo.MemberVo;
import utils.DBConn;

public class MemberDao {
	//DAO의 리스트를 가져오기
	/*
	 * singleton, 한번 쓰고 계속해서 유지시킬 목적
	 */
	//자기타입읜 인스턴스를 프라이빗 스태틱 상태로
	private final static MemberDao dao = new MemberDao();
	//자기타임의 메서트 리턴도 자기타입으로
	public static MemberDao getInstance() {
		return dao;
	}
	//그리고 기본 생성자 생성
	private MemberDao() {} 
	
	/*
	 *  	
	 */
	
	private PreparedStatement pstmt;
	private Connection conn;
	
	public List<MemberVo> list() {
		List<MemberVo> list = new ArrayList();
		try {
			conn = DBConn.getConnection();
			String query = "SELECT * FROM T_MEMBER";
			//값을 넣어줘야할 때 사용
//			stmt = conn.createStatement();
//			System.out.println(stmt);
//			ResultSet rs = stmt.executeQuery(query);
			
			System.out.println(query);
			//객체값할당시점에 쿼리 먼저넣고 execute실행때 쿼리 넣는 위 stmt랑 다름
			//prepareStatement : 전처리된 sql문장 >> 파라미터가 있을 때 
			pstmt = conn.prepareStatement(query);
//			pstmt.setString(, x);
			ResultSet rs = pstmt.executeQuery();
			
			while (rs.next()) {
				int idx = 1;
				MemberVo vo = new MemberVo(
						rs.getString(idx++),
						rs.getString(idx++),
						rs.getString(idx++),
						rs.getString(idx++),
						rs.getDate(idx++)
						) ;
				list.add(vo);
			}
			rs.close();
//			stmt.close();
			pstmt.close();
			conn.close();
		} catch (Exception e) {
			e.printStackTrace();

		}
		return list;
	}
	
	public static void main(String[] args) {
		new MemberDao().list().forEach(System.out::println);
	}
	
	public void register (MemberVo memberVo) {
		try {
			conn = DBConn.getConnection();
			pstmt = conn.prepareStatement("INSERT INTO T_MEMBER VALUES(?, ?, ?, ?, SYSDATE)");
			int idx = 1;
			pstmt.setString(idx++, memberVo.getId());
			pstmt.setString(idx++, memberVo.getPwd());
			pstmt.setString(idx++, memberVo.getName());
			pstmt.setString(idx++, memberVo.getEmail());
			
			pstmt.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	public void remove(String id) {
		try {
			conn = DBConn.getConnection();
			pstmt = conn.prepareStatement("DELETE T_MEMBER WHERE ID = ?");
			int idx = 1;
			pstmt.setString(idx++, id);
			
			pstmt.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	public MemberVo login(String id, String pwd) {
		MemberVo memberVo = null;
		try {
			conn = DBConn.getConnection();
			String query = "SELECT * FROM T_MEMBER WHERE ID = ? AND PWD = ?";
			System.out.println(query);
			
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, id);
			pstmt.setString(2, pwd);
			
			ResultSet rs = pstmt.executeQuery();
			
			while (rs.next()) {
				int idx = 1;
				memberVo = new MemberVo(
						rs.getString(idx++),
						rs.getString(idx++),
						rs.getString(idx++),
						rs.getString(idx++),
						rs.getDate(idx++)
						) ;
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

