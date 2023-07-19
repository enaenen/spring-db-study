package hello.jdbc.exception;

import lombok.Getter;

@Getter
public enum ExceptionStatus {

    PRODUCT_NAME_INVALID("PRODUCT 이름이 유효하지 않습니다."),
    PRODUCT_PRICE_INVALID("PRODUCT 가격이 유효하지 않습니다"),
    PRODUCT_STOCK_INVALID("PRODUCT 재고량이 유효하지 않습니다"),

    PRODUCT_AMOUNT_NOT_ENOUGH("PRODUCT 재고량이 부족합니다."),

    DB_NO_DATA_FOUND("데이터가 존재하지 않습니다."),
    DB_ORDER_EXIST("해당 상품을 주문한 회원이 있어 삭제할 수 없습니다.");



    final private String message;

    ExceptionStatus(String message) {
        this.message = message;
    }
}
