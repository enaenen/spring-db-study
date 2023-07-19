package hello.jdbc.domain;

import lombok.Data;

@Data
public class Orders {
	private int id;
	private int itemCode;
	private int amount;

	private Orders() {
	}

	private Orders(int itemCode, int amount) {

		this.itemCode = itemCode;
		this.amount = amount;
	}
	public static Orders of(int itemCode, int amount) {
		return new Orders(itemCode, amount);
	}


}
