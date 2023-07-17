package hello.jdbc.domain;

import lombok.Data;

@Data
public class Item {
	private int code;
	private String name;
	private int price;
	private int stock;

	public Item() {
	}

	public Item(int code, String name, int price, int stock) {
		this.code = code;
		this.name = name;
		this.price = price;
		this.stock = stock;
	}
}
