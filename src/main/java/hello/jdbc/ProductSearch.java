package hello.jdbc;

import static hello.jdbc.Main.*;

public class ProductSearch {
    public Status ItemSearch(){
        System.out.println("상품 검색을 시작합니다.");
        command = scanner.next();
        itemService.searchItem(command);
        return Status.EXIT;
    }
}
