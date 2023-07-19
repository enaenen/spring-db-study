package hello.jdbc.repository;

import static hello.jdbc.connection.DBConnectionUtil.*;

import hello.jdbc.connection.DBConnectionUtil;
import hello.jdbc.domain.Item;
import hello.jdbc.exception.DBException;
import hello.jdbc.exception.ExceptionStatus;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.SQLIntegrityConstraintViolationException;
import java.util.NoSuchElementException;

import lombok.extern.slf4j.Slf4j;

/**
 * JDBC - DriverManager 사용
 */

@Slf4j
public class ItemRepository {

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

	public Item update(Item item) throws SQLException {
		log.info("item={}", item);

		String sql = "update item set name=?, price=?, stock=? where code = ?";

		Connection con = null;
		PreparedStatement pstmt = null;

		try {
			con = getConnection();
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, item.getName());
			pstmt.setInt(2, item.getPrice());
			pstmt.setInt(3, item.getStock());
			pstmt.setInt(4, item.getCode());
			int resultSize = pstmt.executeUpdate();
			log.info("resultSize={}", resultSize);
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
				Item item = Item.of(rs.getString("name"), rs.getInt("price"), rs.getInt("stock"));
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

	public void deleteByName(String itemName) throws SQLException, DBException {
		String sql = "delete from item where name = ?";

		Connection con = null;
		PreparedStatement pstmt = null;

		try {
			con = getConnection();
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, itemName);
			pstmt.executeUpdate();
		} catch (SQLIntegrityConstraintViolationException e){
//			log.debug("SQL error={}", e);
			throw new DBException(ExceptionStatus.DB_ORDER_EXIST);
		} catch (SQLException e) {
//			log.debug("db error", e);
			throw e;
		} finally {
			close(con, pstmt, null);
		}
	}

	public Item findByCode(int itemCode) {
		String sql = "select * from item where code = ?";

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			con = getConnection();
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, itemCode);

			rs = pstmt.executeQuery();
			if (rs.next()) {
				Item item = Item.of(rs.getString("name"), rs.getInt("price"), rs.getInt("stock"));
				item.setCode(rs.getInt("code"));
				return item;
			} else {
				throw new NoSuchElementException("Item not found itemCode = " + itemCode);
			}
		} catch (SQLException e) {
			log.error("db error", e);
			throw new RuntimeException(e);
		} finally {
			close(con, pstmt, rs);
		}
	}

//	public void updateStock(int itemCode, int amount) {
//		String sql = "update item set stock = stock - ? where code = ?";
//
//		Connection con = null;
//		PreparedStatement pstmt = null;
//
//		try {
//			con = getConnection();
//			pstmt = con.prepareStatement(sql);
//			pstmt.setInt(1, amount);
//			pstmt.setInt(2, itemCode);
//			pstmt.executeUpdate();
//		} catch (SQLException e) {
//			log.error("db error", e);
//			throw new RuntimeException(e);
//		} finally {
//			close(con, pstmt, null);
//		}
//	}
}
