package hello.jdbc;

import hello.jdbc.service.ItemService;

import java.util.Scanner;

public class Main {
    public static final ProductInsertV1 productInsert = new ProductInsertV1();
    public static final ProductSearch productSearch = new ProductSearch();
    public static final ProductDelete productDelete = new ProductDelete();
    public static final OrderInsertAndProductUpdate orderInsertAndProductUpdate = new OrderInsertAndProductUpdate();

    public static final ItemService itemService = new ItemService();
    public static final Scanner scanner = new Scanner(System.in);
    public static final String TERMINATE_COMMAND = "exit";
    public static String command = "";

    public static void main(String[] args) {
        Status status = Status.MENU;

        while (status != Status.EXIT) {
            if(status == Status.MENU) {
                System.out.println(
                        "|   A [(ADD)상품 등록]  |  S [(SEARCH)상품 검색]   |   O [(ORDER)상품 주문]   |   D [(DELETE)상품 제거]   |   E [(EXIT)프로그램 종료]   |");
                command = scanner.next().toLowerCase();
                status = getStatus();
            }

            switch (status) {
                case ADDNAME:
                case ADDPRICE:
                case ADDSTOCK:
                    status = productInsert.insertItem();
                    break;
                case SEARCH:
                    status = productSearch.itemSearch();
                    break;
                case DELETE:
                    status = productDelete.itemDelete();
                    break;
                case ORDER:
                    status = orderInsertAndProductUpdate.orderProcess();
                    break;
                case EXIT:
                    break;
            }
            if (status.equals(Status.EXIT)) {
                if (isSureExit()) {
                    break;
                }
                else {
                    status = Status.MENU;
                }
            }
        }
        System.out.println("============================ 프로그램을 종료합니다. ============================");
            scanner.close();
    }

    private static Status getStatus() {
        Status status;
        switch (command.toLowerCase()) {
            case "a":
                status = Status.ADDNAME;
                break;
            case "s":
                status = Status.SEARCH;
                break;
            case "d":
                status = Status.DELETE;
                break;
            case "o":
                status = Status.ORDER;
                break;
            case "e":
                status = Status.EXIT;
                break;
            default:
                System.out.println("잘못된 입력입니다.");
                status = Status.MENU;
                break;
        }
        return status;
    }

    public static boolean isSureExit() {
        System.out.println("종료하시겠습니까? Y / N");
        String answer = scanner.next().toLowerCase();
        return answer.equals("y") || answer.equals("yes") ? true : false;
    }
}
