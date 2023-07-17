package hello.jdbc.connection;

import static org.junit.jupiter.api.Assertions.*;

import java.sql.Connection;
import lombok.extern.slf4j.Slf4j;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

@Slf4j
class DBConnectionUtilTest {

	@Test
	void getConnection() {
		Connection con = DBConnectionUtil.getConnection();
		Assertions.assertThat(con).isNotNull();
	}
}