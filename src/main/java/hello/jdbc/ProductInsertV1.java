package hello.jdbc;

import hello.jdbc.domain.Item;

import static hello.jdbc.Main.*;

public class ProductInsertV1 {
    public Status insertItem() {
        int price = 0;
        int stock = 0;

        Status currentStatus = Status.NAME;
        String name = null;

        Item newItem;

        System.out.println("상품 등록을 시작합니다.");
        while (currentStatus != Status.EXIT) {
            switch (currentStatus) {
                case NAME:
                    System.out.println("[등록] 상품명을 입력해주세요.");
                    command = scanner.next();
                    if (command.equals(TERMINATE_COMMAND)) {
                        if (isSureExit()) {
                            currentStatus = Status.EXIT;
                            return currentStatus;
                        }
                    }
                    if (command == null || command.isEmpty()) {
                        System.out.println("상품명이 입력되지 않았습니다.");
                        break;
                    }
                    name = command;
                    currentStatus = Status.PRICE;
                case PRICE:
                    System.out.println("[등록] 상품 가격을 입력해주세요.");
                    command = scanner.next();
                    try {
                        price = Integer.parseInt(command);
                    } catch (NumberFormatException e) {
                        if (command.equals(TERMINATE_COMMAND)) {
                            if (isSureExit()) {
                                currentStatus = Status.EXIT;
                                return currentStatus;
                            }
                        }
                        System.out.println("상품 가격이 잘못 입력되었습니다.");
                        break;
                    }
                    if (price < 0) {
                        System.out.println("상품 가격이 잘못 입력되었습니다.");
                        break;
                    }
                    currentStatus = Status.STOCK;
                case STOCK:
                    System.out.println("[등록] 상품 재고수량을 입력해주세요.");
                    command = scanner.next();
                    try {
                        stock = Integer.parseInt(command);
                    } catch (NumberFormatException e) {
                        if (command.equals(TERMINATE_COMMAND)) {
                            if (isSureExit()) {
                                currentStatus = Status.EXIT;
                                return currentStatus;
                            }
                            break;
                        }
                        System.out.println("상품 재고수량이 잘못 입력되었습니다.");
                        break;
                    }
                    if (stock < 0) {
                        System.out.println("상품 재고수량이 잘못 입력되었습니다.");
                        break;
                    }

                    currentStatus = Status.NAME;
                    newItem = new Item(name, price, stock);
                    Item savedItem = itemService.saveItem(newItem);
                    System.out.println("saved item info : " + savedItem);
                    return Status.MENU;
                case EXIT:
                    break;
            }

        }
        return Status.EXIT;
    }
}
