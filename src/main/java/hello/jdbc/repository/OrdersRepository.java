package hello.jdbc.repository;

import hello.jdbc.connection.DBConnectionUtil;
import hello.jdbc.domain.Orders;
import java.sql.Connection;
import java.sql.PreparedStatement;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class OrdersRepository {

	private Connection getConnection() {
		return DBConnectionUtil.getConnection();
	}

	public Orders updateOrder(Orders orders) {

		String sql = "insert into orders(item_code, amount) values(?, ?)";
		Connection con = null;
		PreparedStatement pstmt = null;

		try {
			con = getConnection();
			con.setAutoCommit(false);

			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, orders.getItemCode());
			pstmt.setInt(2, orders.getAmount());
			pstmt.executeUpdate();
			con.commit();
			return orders;
		} catch (Exception e) {
			log.error("db error", e);
			throw new RuntimeException(e);
		} finally {
			DBConnectionUtil.close(con, null, null);
		}
	}


}
