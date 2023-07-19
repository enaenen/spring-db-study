package hello.jdbc.service;

import hello.jdbc.domain.Item;
import hello.jdbc.domain.Orders;
import hello.jdbc.exception.DBException;
import hello.jdbc.repository.ItemRepository;
import hello.jdbc.repository.OrdersRepository;
import java.util.NoSuchElementException;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import java.sql.SQLException;

public class ItemService {

	private static final ItemRepository itemRepository = new ItemRepository();
	private static final OrdersRepository ordersRepository = new OrdersRepository();

	public Item saveItem(Item item) {
		try {
			itemRepository.save(item);
			return itemRepository.save(item);
		} catch (SQLException e) {
			System.out.println("error = " + e);
			return null;
		}
	}

	public Item searchItem(String itemName) {
		try {
			if (StringUtils.hasText(itemName)) {
				return itemRepository.findByName(itemName);
			} else {
				return null;
			}
		} catch (NoSuchElementException e) {
			return null;
		} catch (SQLException e) {
			System.out.println("error = " + e);
			return null;
		}
	}

	public Item searchItem(int code) {
		try {
			return itemRepository.findByCode(code);
		} catch (NoSuchElementException e) {
			return null;
		}
	}


	public boolean deleteItem(String itemName) {
		if (!StringUtils.hasText(itemName)) {
			System.out.println("상품명을 입력해주세요.");
			return false;
		}
		try {
			itemRepository.deleteByName(itemName);
			return true;
		}catch (DBException dbe){
			System.out.println("error = " + dbe);
			return false;
		} catch (SQLException e) {
			System.out.println("error = " + e);
			return false;
		}
	}

	@Transactional
	public boolean updateItem(int itemCode, int amount) {
		if (!orderItemAndAmountValidation(itemCode, amount)) {
			return false;
		}

		Item findItem;
		try {
			findItem = itemRepository.findByCode(itemCode);
		} catch (NoSuchElementException e) {
			System.out.println("해당 아이템이 존재하지 않습니다.");
			return false;
		}

		if (findItem.getStock() < 1 || findItem.getStock() < amount) {
			System.out.println("재고가 부족합니다.");
			return false;
		}

		findItem.orderItem(amount);
		try {
			itemRepository.update(findItem);
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
		ordersRepository.updateOrder(Orders.of(itemCode, amount));
		return true;
	}

	private boolean orderItemAndAmountValidation(int itemCode, int amount) {
		if (itemCode < 0) {
			System.out.println("아이템 코드는 0보다 커야합니다.");
			return false;
		}

		if (amount < 1) {
			System.out.println("주문수량 1개 이상이어야 합니다.");
			return false;
		}
		return true;
	}
}