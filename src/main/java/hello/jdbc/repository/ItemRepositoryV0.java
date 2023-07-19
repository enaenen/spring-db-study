package hello.jdbc.repository;

import hello.jdbc.connection.DBConnectionUtil;
import hello.jdbc.domain.Item;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.NoSuchElementException;

import lombok.extern.slf4j.Slf4j;

/**
 * JDBC - DriverManager 사용
 */

@Slf4j
public class ItemRepositoryV0 {

	private Connection getConnection() {
		return DBConnectionUtil.getConnection();
	}

	public Item save(Item item) throws SQLException {
		log.info("item={}", item);

		String sql = "insert into item (name, price, stock) values (?, ?, ?)";

		Connection con = null;
		PreparedStatement pstmt = null;

		try {
			con = getConnection();
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, item.getName());
			pstmt.setInt(2, item.getPrice());
			pstmt.setInt(3, item.getStock());
			pstmt.executeUpdate();
			return item;
		} catch (SQLException e) {
			log.error("SQL error={}", e);
			throw new RuntimeException(e);
		} finally {
			close(con, pstmt, null);
		}
	}

	public Item findByName(String itemName) throws SQLException {
		String sql = "select * from item where name = ?";

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			con = getConnection();
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, itemName);

			rs = pstmt.executeQuery();
			if (rs.next()) {
				Item item = Item.of(rs.getString("name"), rs.getInt("price"),rs.getInt("stock"));
				item.setCode(rs.getInt("code"));
				return item;
			} else {
				throw new NoSuchElementException("Item not found itemName = " + itemName);
			}
		} catch (SQLException e) {
			log.error("db error", e);
			throw e;
		} finally {
			close(con, pstmt, rs);
		}
	}

	private void close(Connection con, Statement stmt, ResultSet rs) {

		if (rs != null){
			try {
				rs.close();
			} catch (SQLException e) {
				log.error("rs close error={}", e);
			}
		}

		if (stmt != null){
			try {
				stmt.close();
			} catch (SQLException e) {
				log.error("stmt close error={}", e);
			}
		}

		if (con != null){
			try {
				con.close();
			} catch (SQLException e) {
				log.error("con close error={}", e);
			}
		}
	}

}
