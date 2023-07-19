package hello.jdbc;

import hello.jdbc.domain.Item;
import hello.jdbc.service.ItemService;

import java.util.Scanner;

public class Main {
    public static final ProductInsertV1 productInsert = new ProductInsertV1();
    public static final Scanner scanner = new Scanner(System.in);
    public static final String TERMINATE_COMMAND = "exit";
    public static final ItemService itemService = new ItemService();
    public static String command = "";

    public static void main(String[] args) {
        Status status = Status.MENU;

        while (status != Status.EXIT) {
            System.out.println("|   A [(ADD)상품 등록]  |   s [(SEARCH)상품 조회]   |   d [(DELETE)상품 제거]   |   u [(UPDATE)상품 수정]   |");
            command = scanner.next().toLowerCase();
            switch (command.charAt(0)) {
                case 'a':
                    status = productInsert.insertItem();
                    break;
                case 's':


                case 'd':
                case 'u':
                default:
                    if (isSureExit())
                        status = Status.EXIT;
                    break;
            }
        }
        System.out.println("============================ 프로그램을 종료합니다. ============================");
            scanner.close();
    }

    public static boolean isSureExit() {
        System.out.println("종료하시겠습니까? Y / N");
        String answer = scanner.next().toLowerCase();
        return answer.equals("y") || answer.equals("yes") ? true : false;
    }
}
