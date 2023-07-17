package hello.jdbc.repository;

import static org.junit.jupiter.api.Assertions.*;

import hello.jdbc.domain.Item;
import java.sql.SQLException;
import org.junit.jupiter.api.Test;

class ItemRepositoryV0Test {

	ItemRepositoryV0 repository = new ItemRepositoryV0();

	@Test
	void crud() throws SQLException {
		Item item = new Item("itemA", 1000, 10);
		repository.save(item);
	}
}