package dao;

import static org.junit.Assert.assertNotNull;

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
	public static MemberDao getInstance(){
		return memberDao;
	}
	private MemberDao() {}
	
	public List<Board> list(){
		List<Board> list = new ArrayList<Board>();
		try {
			//클래스로드 및 커넥션취득
			Connection conn = DBConn.getConnection();
			
			//쿼리 생성
			String sql = "SELECT BNO, TITLE, HITCOUNT, \r\n" + 
					"CASE\r\n" + 
					"    WHEN SYSDATE - REGDATE > 1 THEN TO_CHAR(REGDATE, 'YY/MM/DD')\r\n" + 
					"    ELSE TO_CHAR(REGDATE, 'HH24:MI:SS')\r\n" + 
					"END REGDATE,\r\n" + 
					"WRITER FROM T_BOARD ORDER BY 1 DESC";
			
			PreparedStatement pstmt = conn.prepareStatement(sql);
			
			//결과집합 생성
			ResultSet rs = pstmt.executeQuery();
			
			//결과집합 순회 후 데이터 바인딩
			while(rs.next()) {
				Board board = new Board(rs.getLong(1), rs.getString(2), null,
						rs.getInt(3), rs.getString(4), rs.getString(5));
				list.add(board);
			}
			
			

		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}
	
	//회원가입
	public void join(Member member) {
		try {
			Connection conn = DBConn.getConnection();
			
			//문장 생성
			String sql = "{call PROC_INSERT_MEMBER(?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)}";

			CallableStatement cstmt = conn.prepareCall(sql);        
			
			//파라미터바인딩
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
			
			cstmt.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	//로그인
	public Member login(String id, String pw) {
		Member member = null;
		try {
			//DB연결
			Connection conn = DBConn.getConnection();
			
			//쿼리생성 (아이디 패스워드 일치)
			String sql = "SELECT * FROM T_MEMBER WHERE ID = ? AND PW = ?";
			
			PreparedStatement pstmt = conn.prepareStatement(sql);
			
			int idx = 1;
			pstmt.setString(idx++, id.trim());
			pstmt.setString(idx++, pw.trim());
			
			//결과집합생성
			ResultSet rs = pstmt.executeQuery();
			
			while (rs.next()) {
				int idx2 = 1;
				member = new Member(rs.getString(idx2++), rs.getString(idx2++), rs.getString(idx2++));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return member;
	}
	
	//이메일 중복체크
	public Member emailFind(String email) {
		Member member = null;
		try {
			Connection conn = DBConn.getConnection();
			
			String sql = "SELECT * FROM T_MEMBER WHERE EMAIL = ?";
			
			PreparedStatement pstmt = conn.prepareStatement(sql);
			int idx = 1;
			pstmt.setString(idx++, email.trim().toLowerCase());
			
			ResultSet rs = pstmt.executeQuery();
			
			while(rs.next()) {
				int idx2 = 1;
				member = new Member(
						rs.getString(idx2++),rs.getString(idx2++),rs.getString(idx2++),rs.getString(idx2++),
						rs.getString(idx2++),rs.getString(idx2++),rs.getString(idx2++),rs.getString(idx2++),
						rs.getString(idx2++),rs.getString(idx2++),rs.getString(idx2++),rs.getString(idx2++),
						rs.getString(idx2++),rs.getString(idx2++),rs.getString(idx2++)
						);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return member;
	}
	
	//아이디 중복체크
	public Member idFind(String id) {
		Member member = null;
		try {
			Connection conn = DBConn.getConnection();
			
			String sql = "SELECT * FROM T_MEMBER WHERE ID = ?";
			
			PreparedStatement pstmt = conn.prepareStatement(sql);
			int idx = 1;
			pstmt.setString(idx++, id.trim().toLowerCase());
			
			ResultSet rs = pstmt.executeQuery();
			
			while (rs.next()) {
				int idx2 = 1;
				member = new Member(
				rs.getString(idx2++),rs.getString(idx2++),rs.getString(idx2++),rs.getString(idx2++),
				rs.getString(idx2++),rs.getString(idx2++),rs.getString(idx2++),rs.getString(idx2++),
				rs.getString(idx2++),rs.getString(idx2++),rs.getString(idx2++),rs.getString(idx2++),
				rs.getString(idx2++),rs.getString(idx2++),rs.getString(idx2++)
				);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return member;
	}
	
	//로그인 체크
	public Member get(String id) {
		Member member = null;
		try {
			Connection conn = DBConn.getConnection();
			
			String sql = "SELECT * FROM T_MEMBER WHERE ID = ?";
			
			PreparedStatement pstmt = conn.prepareStatement(sql);
			int idx = 1;
			ResultSet rs = pstmt.executeQuery();
			
			while(rs.next()) {
				int idx2 = 1;
				member = new Member(
				rs.getString(idx2++), rs.getString(idx2++), rs.getString(idx2++),
				rs.getString(idx2++), rs.getString(idx2++), rs.getString(idx2++),
				rs.getString(idx2++), rs.getString(idx2++), rs.getString(idx2++),
				rs.getString(idx2++), rs.getString(idx2++), rs.getString(idx2++),
				rs.getString(idx2++), rs.getString(idx2++), rs.getString(idx2++)
				);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return member;
	}
	
	//업데이트 비동기 요청
	public void updateAuthToken(Member member) {
		try {
			Connection conn = DBConn.getConnection();
			
			String sql = "UPDATE T_MEMBER SET AUTH_TOKEN = ? WHERE ID = ?";
			
			PreparedStatement pstmt = conn.prepareStatement(sql);
			
			pstmt.setString(1, member.getAuthToken());
			pstmt.setString(2, member.getId());
			
			pstmt.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	//이메일 인증 일치 확인
	public void updateAuth(Member member) {
		try {
			Connection conn = DBConn.getConnection();
			
			String sql = "UPDATE T_MEMBER SET AUTH = ? WHERE ID = ?";
			
			PreparedStatement pstmt = conn.prepareStatement(sql);
			
			pstmt.setString(1, member.getAuth());
			pstmt.setString(2, member.getId());
			
			pstmt.executeUpdate();			
		} catch (Exception e) {
			 e.printStackTrace();
		}
	}
	
	//회원정보수정
	public void modify(Member member) {
		try {
			Connection conn = DBConn.getConnection();
			
			StringBuilder sb = new StringBuilder();
			sb.append("UPDATE T_MEMBER SET \n");
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
			
			PreparedStatement pstmt = conn.prepareStatement(sql);
			
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
			
			pstmt.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	//회원탈퇴
	public void remove(String id) {
		try {
			Connection conn = DBConn.getConnection();
			
			String sql = "{call quit_proc(?)}";

			CallableStatement cstmt = conn.prepareCall(sql);
			
			cstmt.setString(1, id);
			
			cstmt.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
