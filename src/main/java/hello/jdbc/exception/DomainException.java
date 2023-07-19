package hello.jdbc.exception;


import lombok.Getter;

@Getter
public class DomainException extends RuntimeException{
    final ExceptionStatus status;
    public DomainException(ExceptionStatus status) {
        this.status = status;
    }
}