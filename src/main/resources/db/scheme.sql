
DROP TABLE IF EXISTS item;
-- 상품테이블
CREATE TABLE item
(
    code  INT AUTO_INCREMENT PRIMARY KEY,
    name  VARCHAR(255) NOT NULL,
    price INT          NOT NULL,
    stock INT          NOT NULL
);

DROP TABLE IF EXISTS orders;

-- 주문테이블
CREATE TABLE orders
(
    id        INT AUTO_INCREMENT PRIMARY KEY,
    item_code INT NOT NULL,
    amount    INT NOT NULL,
    FOREIGN KEY (item_code) REFERENCES item (code)
);

-- 상품 테이블에 예시 데이터 삽입
INSERT INTO item (name, price, stock)
VALUES ('아이폰', 1300000, 50),
       ('맥북', 2000000, 30),
       ('애플워치', 160000, 20);

-- 주문 테이블에 예시 데이터 삽입
INSERT INTO orders (item_code, amount)
VALUES (1, 2),
       (2, 1),
       (3, 3);
