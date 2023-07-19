package hello.jdbc;

import static hello.jdbc.Main.TERMINATE_COMMAND;
import static hello.jdbc.Main.command;
import static hello.jdbc.Main.isSureExit;
import static hello.jdbc.Main.itemService;
import static hello.jdbc.Main.scanner;

import hello.jdbc.domain.Item;
import org.springframework.transaction.annotation.Transactional;


public class OrderInsertAndProductUpdate {
	@Transactional
	public Status orderProcess() {
		int code;
		int amount;
		try {
			System.out.println("===============상품 주문을 시작합니다.===============");
			System.out.print("주문할 상품 코드를 입력해주세요: ");

			code = Integer.parseInt(scanner.next());
			if (code < 0)
				throw new NumberFormatException();
			Item item = itemService.searchItem(code);
			if (item == null){
				System.out.println("상품이 존재하지 않습니다.");
				return Status.ORDER;
			}
			System.out.println("아이템명 : " + item.getName() + " 주문가능수량 : " + item.getStock());
			System.out.print("주문 수량을 입력해주세요: ");
			amount = Integer.parseInt(scanner.next());
			if (amount < 0)
				throw new NumberFormatException();

		} catch (NumberFormatException e) {
			if (command.equals(TERMINATE_COMMAND)) {
				if (isSureExit()) {
					return Status.MENU;
				}
			}

			System.out.println("상품 코드와 수량은 0 이상의 숫자만 입력 가능합니다.");
			return Status.ORDER;
		}

		if (itemService.updateItem(code, amount)) {
			System.out.println("상품 주문이 완료되었습니다.");
			return Status.MENU;
		} else {
			System.out.println("상품 주문이 실패하였습니다.");
			return Status.ORDER;
		}
	}
}
