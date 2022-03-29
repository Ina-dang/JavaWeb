package utils;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;

public class DBConn {
	public static Connection getConnection() throws SQLException, ClassNotFoundException {
//		HikariConfig hikariConfig = new HikariConfig();
//		hikariConfig.setDriverClassName("oracle.jdbc.driver.OracleDriver");
		Class.forName("oracle.jdbc.driver.OracleDriver");
		Connection conn = DriverManager.getConnection(
				"jdbc:oracle:thin:@np.inadang.com:1521/xe", "ODOUNG", "1234");
		
//		hikariConfig.setJdbcUrl("jdbc:oracle:thin:@np.inadang.com:1521/xe");
//		hikariConfig.setUsername("ODOUNG");
//		hikariConfig.setPassword("1234");
		
//		hikariConfig.setJdbcUrl("jdbc:oracle:thin:@localhost:1521/xe");
//		hikariConfig.setUsername("hr");
//		hikariConfig.setPassword("1234");
		
//		HikariDataSource dataSource = new HikariDataSource(hikariConfig);
//		Connection conn = new HikariDataSource(hikariConfig).getConnection();
//		dataSource.close();
		return conn;
	}
	public static void main(String[] args) throws Exception{
		System.out.println(getConnection());
		
		//SQL문장 직접처리해야함
//		PreparedStatement pstmt = getConnection().prepareStatement("");
		
		//프로시저, 함수 호출
		CallableStatement cstmt = getConnection().prepareCall("{call proc_test(?)}");
		cstmt.setString(1, "javamail");
		cstmt.executeUpdate();
	}
}
