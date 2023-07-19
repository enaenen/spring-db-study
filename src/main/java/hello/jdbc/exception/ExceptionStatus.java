package hello.jdbc.exception;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
public enum ExceptionStatus {

    PRODUCT_NAME_INVALID("PRODUCT 이름이 유효하지 않습니다."),
    PRODUCT_PRICE_INVALID("PRODUCT 가격이 유효하지 않습니다"),
    PRODUCT_STOCK_INVALID("PRODUCT 재고량이 유효하지 않습니다");


    final private String message;

    ExceptionStatus(String message) {
        this.message = message;
    }
}
