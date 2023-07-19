package hello.jdbc;

import hello.jdbc.domain.Item;
import hello.jdbc.exception.DomainException;
import hello.jdbc.exception.ExceptionStatus;

import static hello.jdbc.Main.*;

public class ProductInsertV1 {
    public Status insertItem() {
        int price = 0;
        int stock = 0;

        Status currentStatus = Status.ADDNAME;
        String name = null;

        Item newItem;

        System.out.println("상품 등록을 시작합니다.");
        while (currentStatus != Status.MENU) {
            switch (currentStatus) {
                case ADDNAME:
                    System.out.print("[등록] 상품명을 입력해주세요: ");
                    command = scanner.next();
                    if (command.equals(TERMINATE_COMMAND)) {
                        if (isSureExit()) {
                            currentStatus = Status.MENU;
                            return currentStatus;
                        }
                        break;
                    }
                    name = command;
                    currentStatus = Status.ADDPRICE;
                case ADDPRICE:
                    System.out.print("[등록] 상품 가격을 입력해주세요: ");
                    command = scanner.next();
                    try {
                        price = Integer.parseInt(command);
                    } catch (NumberFormatException e) {
                        if (command.equals(TERMINATE_COMMAND)) {
                            if (isSureExit()) {
                                currentStatus = Status.MENU;
                                return currentStatus;
                            }
                        }
                        System.out.println("상품 가격이 잘못 입력되었습니다: ");
                        break;
                    }
                    currentStatus = Status.ADDSTOCK;
                case ADDSTOCK:
                    System.out.print("[등록] 상품 재고수량을 입력해주세요: ");
                    command = scanner.next();
                    try {
                        stock = Integer.parseInt(command);
                    } catch (NumberFormatException e) {
                        if (command.equals(TERMINATE_COMMAND)) {
                            if (isSureExit()) {
                                currentStatus = Status.MENU;
                                return currentStatus;
                            }
                            break;
                        }
                        System.out.println("상품 재고수량이 잘못 입력되었습니다.");
                        break;
                    }
                    try {
                        newItem = Item.of(name, price, stock);
                        Item savedItem = itemService.saveItem(newItem);
                        System.out.println("saved item info : " + savedItem);
                        return Status.MENU;
                    } catch (DomainException e) {
                        if (e.getStatus().equals(ExceptionStatus.PRODUCT_NAME_INVALID)) {
                            System.out.println("[상품 이름이 잘못되었습니다.]");
                            currentStatus = Status.ADDNAME;
                        } else if (e.getStatus().equals(ExceptionStatus.PRODUCT_PRICE_INVALID)) {
                            System.out.println("[상품 가격가 잘못되었습니다.]");
                            currentStatus = Status.ADDPRICE;
                        } else {
                            System.out.println("[상품 재고가 잘못되었습니다.]");
                            currentStatus = Status.ADDSTOCK;
                        }
                    }
                case MENU:
                    break;
            }

        }
        return Status.EXIT;
    }
}
