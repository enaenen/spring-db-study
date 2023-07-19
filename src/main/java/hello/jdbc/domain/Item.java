package hello.jdbc.domain;

import hello.jdbc.exception.DomainException;
import hello.jdbc.exception.ExceptionStatus;
import lombok.Data;
import org.springframework.util.StringUtils;

@Data
public class Item {
	private int code;
	private String name;
	private int price;
	private int stock;

	private Item() {
	}

	private void isValid(){
		if (!StringUtils.hasText(name))
			throw new DomainException(ExceptionStatus.PRODUCT_STOCK_INVALID);
		if (price < 0)
			throw new DomainException(ExceptionStatus.PRODUCT_PRICE_INVALID);
		if (stock < 0)
			throw new DomainException(ExceptionStatus.PRODUCT_STOCK_INVALID);
	}
	public static Item of(String name, int price, int stock){
		Item item = new Item();
		item.setName(name);
		item.setPrice(price);
		item.setStock(stock);
		item.isValid();
		return item;
	}
	public void orderItem(int amount){
		if (stock < amount)
			throw new DomainException(ExceptionStatus.PRODUCT_AMOUNT_NOT_ENOUGH);
		stock -= amount;
	}
	public String toString(){
		return String.format("상품코드: %d,\n상품명: %s\n상품가격: %d\n상품재고: %d\n", code, name, price, stock);
	}
}
