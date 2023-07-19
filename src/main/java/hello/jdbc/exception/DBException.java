package hello.jdbc.exception;

public class DBException extends RuntimeException{
	final ExceptionStatus status;
	public DBException(ExceptionStatus status) {
		this.status = status;
	}
}
