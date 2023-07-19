package hello.jdbc.connection;


import static hello.jdbc.connection.ConnectionConst.*;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class DBConnectionUtil {

	public static Connection getConnection() {
		try {
			Connection connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);
			log.info("get connection={}, class={}", connection, connection.getClass());
			return connection;
		} catch (SQLException e) {
			log.error("get connection error", e);
			throw new IllegalArgumentException(e);
		}
	}

	public static void close(Connection con, Statement stmt, ResultSet rs) {
		if (rs != null) {
			try {
				rs.close();
			} catch (SQLException e) {
				log.info("error", e);
			}
		}
		if (stmt != null) {
			try {
				stmt.close();
			} catch (SQLException e) {
				log.info("error", e);
			}
		}
		if (con != null) {
			try {
				con.close();
			} catch (SQLException e) {
				log.info("error", e);
			}
		}
	}
}
