package dao;

import java.sql.CallableStatement;
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
	
	public void register(Member member) {
		try {
			Connection conn = DBConn.getConnection();
			
			//프로시저 호출 시 사용될 sql 문장
			String sql = "{call PROC_INSERT_MEMBER(?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)}";
			
//			PreparedStatement pstmt = conn.prepareStatement(sql);
			CallableStatement cstmt = conn.prepareCall(sql);
			
			// 파라미터 바인딩
			int idx = 1;
			
			cstmt.setString(idx++, member.getId());
			cstmt.setString(idx++, member.getPw());
			cstmt.setString(idx++, member.getName());
			
			cstmt.setString(idx++, member.getSi());
			cstmt.setString(idx++, member.getSgg());
			cstmt.setString(idx++, member.getEmd());
			cstmt.setString(idx++, member.getRoadAddr());
			cstmt.setString(idx++, member.getAddrDetail());
			cstmt.setString(idx++, member.getZipNo());
			cstmt.setString(idx++, member.getRoadFullAddr());
			cstmt.setString(idx++, member.getJibunAddr());
			
			cstmt.setString(idx++, member.getEmail());
			
			// 문장 실행(반영)
			cstmt.executeUpdate();
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	
	//회원 정보 수정
	public void modify(Member member) {
		try {
			// connection 취득
			Connection conn = DBConn.getConnection();

//			문자열 조합이 길어질수록 스트링 버퍼, 빌더 쓰는게 훨 좋음
			StringBuilder sb = new StringBuilder();
			sb.append("UPDATE TBL_MEMBER SET \n");
			if (member.getPw() != null && !member.getPw().equals("")) {
				sb.append("PW = ?, \n");
			}
			sb.append("NAME = ?, \n");
			sb.append("SI = ?, \n");
			sb.append("SGG = ?, \n");
			sb.append("EMD = ?, \n");
			sb.append("ROADADDR = ?, \n");
			sb.append("ADDRDETAIL = ?, \n");
			sb.append("ZIPNO = ?, \n");
			sb.append("ROADFULLADDR = ?, \n");
			sb.append("JIBUNADDR = ? \n");
			sb.append("WHERE ID = ?");
			
			String sql = sb.toString();
			// 문장 생성
//			String sql = "UPDATE tbl_member SET\n" + 
//					"    PW = ?,\n" + 
//					"    NAME = ?,\n" + 
//					"    SI = ?,\n" + 
//					"    SGG = ?,\n" + 
//					"    EMD = ?,\n" + 
//					"    ROADADDR = ?,\n" + 
//					"    ADDRDETAIL = ?,\n" + 
//					"    ZIPNO = ?,\n" + 
//					"    ROADFULLADDR = ?,\n" + 
//					"    JIBUNADDR = ?\n" + 
//					"WHERE ID = ?;";
			PreparedStatement pstmt = conn.prepareStatement(sql);
			
			// 파라미터 바인딩
			int idx = 1;
			if (member.getPw() != null && !member.getPw().equals("")) {
				pstmt.setString(idx++, member.getPw());
			}
			pstmt.setString(idx++, member.getName());
			pstmt.setString(idx++, member.getSi());
			pstmt.setString(idx++, member.getSgg());
			pstmt.setString(idx++, member.getEmd());
			pstmt.setString(idx++, member.getRoadAddr());
			pstmt.setString(idx++, member.getAddrDetail());
			pstmt.setString(idx++, member.getZipNo());
			pstmt.setString(idx++, member.getRoadFullAddr());
			pstmt.setString(idx++, member.getJibunAddr());
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
			// connection 취득
			Connection conn = DBConn.getConnection();
			
			// 문장 생성
			String sql = "{call quit_proc(?)}";

			CallableStatement cstmt = conn.prepareCall(sql);
			
			// 파라미터 바인딩
			cstmt.setString(1, id);
			
			// 문장 실행(반영)
			cstmt.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
	
	public Member login(String id, String pw) {
		Member member = null;
		try {
			//클래스 로드
			Class.forName("oracle.jdbc.driver.OracleDriver");
			// connection 취득
			Connection conn = DBConn.getConnection();
			
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
	
	//로그인 중복체크
	public Member get(String id) {
		Member member = null;
		try {
			// connection 취득
			Connection conn = DBConn.getConnection();
			
			// 문장 생성
			String sql = "SELECT * FROM TBL_MEMBER\r\n" + 
					"WHERE ID = ?";
			PreparedStatement pstmt = conn.prepareStatement(sql);
			int idx = 1;
			pstmt.setString(idx++, id.trim().toLowerCase());
			
			// 결과집합 생성
			ResultSet rs = pstmt.executeQuery();
			
			// 결과집합 순회 후 데이터 바인딩
			while(rs.next()) {
				int idx2 = 1;
				member = new Member(
						rs.getString(idx2++), rs.getString(idx2++), rs.getString(idx2++),
						rs.getString(idx2++), rs.getString(idx2++), rs.getString(idx2++),
						rs.getString(idx2++), rs.getString(idx2++), rs.getString(idx2++),
						rs.getString(idx2++), rs.getString(idx2++), rs.getString(idx2++),
						rs.getString(idx2++), rs.getString(idx2++)
						);
			}
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return member;
	}
	
	//아이디 찾기
	public Member findBy(String email) {
		Member member = null;
		try {
			// connection 취득
			Connection conn = DBConn.getConnection();
			
			// 문장 생성
			String sql = "SELECT * FROM TBL_MEMBER\r\n" + 
					"WHERE EMAIL = ?";
			PreparedStatement pstmt = conn.prepareStatement(sql);
			int idx = 1;
			pstmt.setString(idx++, email.trim().toLowerCase());
			
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
	
	//비동기요청
	public void updateAuthToken(Member member) {
		try {
			Connection conn = DBConn.getConnection();
			
			// 문장 생성
			String sql = "UPDATE TBL_MEMBER SET\r\n" +
					"AUTH_TOKEN = ? \r\n" +
					"WHERE ID = ?";
			PreparedStatement pstmt = conn.prepareStatement(sql);
			
			pstmt.setString(1, member.getAuthToken());
			pstmt.setString(2, member.getId());
			
			pstmt.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	//이메일인증확인
	public void updateAuth(Member member) {
		try {
			Connection conn = DBConn.getConnection();
			
			// 문장 생성
			String sql = "UPDATE TBL_MEMBER SET\r\n" +
					"AUTH = ? \r\n" +
					"WHERE ID = ?";
			PreparedStatement pstmt = conn.prepareStatement(sql);
			
			pstmt.setString(1, member.getAuth());
			pstmt.setString(2, member.getId());
			
			pstmt.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
}
