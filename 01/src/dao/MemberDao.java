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
	private final static MemberDao dao = new MemberDao();
	public static MemberDao getInstance() {
		return dao;
	}
	private MemberDao() {}

	
	private Connection conn;
	private PreparedStatement pstmt;
	
	List<MemberVo> list() {
		List<MemberVo> list = new ArrayList<MemberVo>();
		
		try {
			conn = DBConn.getConnection();
			String query = "SELECT * FROM T_HOME";
			pstmt = conn.prepareStatement(query);
			ResultSet rs = pstmt.executeQuery();
			
			while (rs.next()) {
				int idx = 1;
				MemberVo memberVo = new MemberVo(
						rs.getString(idx++), //ID
						rs.getString(idx++), //Pwd
						rs.getString(idx++), //name
						rs.getString(idx++), //nick
						rs.getString(idx++), //email
						rs.getDate(idx++)	//join					
						);
				list.add(memberVo);
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
}
