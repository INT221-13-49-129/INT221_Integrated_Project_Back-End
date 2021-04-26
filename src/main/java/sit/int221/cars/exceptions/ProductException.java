package sit.int221.cars.exceptions;

public class ProductException extends RuntimeException{
    ExceptionResponse.ERROR_CODE errorCode;
    public ProductException(ExceptionResponse.ERROR_CODE errorCode, String s) {
        super(s);
        this.errorCode = errorCode;
    }
    public ExceptionResponse.ERROR_CODE getErrorCode() {
        return this.errorCode;
    }
}
