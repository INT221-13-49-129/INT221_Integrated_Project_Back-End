package sit.int221.cars.exceptions;

public class ProductException extends RuntimeException{
    /**
	 * 
	 */
	private static final long serialVersionUID = 7257851496901467926L;
	ExceptionResponse.ERROR_CODE errorCode;
    public ProductException(ExceptionResponse.ERROR_CODE errorCode, String s) {
        super(s);
        this.errorCode = errorCode;
    }
    public ExceptionResponse.ERROR_CODE getErrorCode() {
        return this.errorCode;
    }
}
