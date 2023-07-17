package hello.jdbc.domain;

import lombok.Data;

@Data
public class Orders {
	private int id;
	private int itemCode;
	private int amount;

	public Orders() {
	}

	public Orders(int id, int itemCode, int amount) {
		this.id = id;
		this.itemCode = itemCode;
		this.amount = amount;
	}
}
