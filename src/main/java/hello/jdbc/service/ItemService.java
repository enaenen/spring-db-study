package hello.jdbc.service;

import hello.jdbc.domain.Item;
import hello.jdbc.repository.ItemRepositoryV0;
import org.springframework.util.StringUtils;

import java.sql.SQLException;

public class ItemService {
    private static final ItemRepositoryV0 itemRepositoryV0 = new ItemRepositoryV0();

    public Item saveItem(Item item) {
        try {
            return itemRepositoryV0.save(item);
        } catch (SQLException e) {
            System.out.println("error = " + e);
            return null;
        }
    }

    public Item searchItem(String itemName) {
        try {
            if (StringUtils.hasText(itemName))
                return itemRepositoryV0.findByName(itemName);
            else
                return null;
        } catch (SQLException e) {
            System.out.println("error = " + e);
            return null;
        }
    }


}