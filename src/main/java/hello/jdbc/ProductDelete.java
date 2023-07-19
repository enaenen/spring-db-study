package hello.jdbc;

import static hello.jdbc.Main.TERMINATE_COMMAND;
import static hello.jdbc.Main.command;
import static hello.jdbc.Main.isSureExit;
import static hello.jdbc.Main.itemService;
import static hello.jdbc.Main.scanner;

public class ProductDelete {

	public Status itemDelete() {
		System.out.println("상품 삭제를 시작합니다.");
		System.out.print("삭제할 상품의 이름을 입력해주세요: ");
		command = scanner.next();
		if (command.equals(TERMINATE_COMMAND)) {
			if (isSureExit()) {
				return Status.MENU;
			}
		}
		if (itemService.deleteItem(command)) {
			System.out.println("상품 삭제가 완료되었습니다.");
			return Status.MENU;
		} else {
			System.out.println("상품 삭제가 실패하였습니다.");
			return Status.DELETE;
		}
	}

	;

}
