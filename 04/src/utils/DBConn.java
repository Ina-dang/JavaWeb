package utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

//DB커넥션이 필요할 때. 여기에 접속정보 저장
public class DBConn { 
	public static Connection getConnection() throws SQLException, ClassNotFoundException {
		Class.forName("oracle.jdbc.driver.OracleDriver");
		Connection conn = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521/xe", "student", "1234");
		return conn;
	}
}
