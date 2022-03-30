//package utils;
//
//import java.sql.Connection;
//import java.sql.SQLException;
//
//import com.zaxxer.hikari.HikariConfig;
//import com.zaxxer.hikari.HikariDataSource;
//
//public class DBConn2 {
//	public static Connection getConnection() throws SQLException, ClassNotFoundException {
////		Class.forName("oracle.jdbc.driver.OracleDriver");
////		Connection conn = DriverManager.getConnection(
////				"jdbc:oracle:thin:@np.inadang.com:1521/xe", "ODOUNG", "1234");
//		
//		HikariConfig hikariConfig = new HikariConfig();
//		hikariConfig.setDriverClassName("oracle.jdbc.driver.OracleDriver");
//		hikariConfig.setJdbcUrl("jdbc:oracle:thin:@np.inadang.com:1521/xe");
//		hikariConfig.setUsername("ODOUNG");
//		hikariConfig.setPassword("1234");
//		
//		HikariDataSource dataSource = new HikariDataSource(hikariConfig);
//		Connection conn = dataSource.getConnection();
//		dataSource.close();
//		return conn;
//	}
//	public static void main(String[] args) throws Exception{
//		System.out.println(getConnection());
//	}
//}
