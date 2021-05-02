package sit.int221.cars.exceptions;

import java.time.LocalDateTime;

public class ExceptionResponse {

    public static enum ERROR_CODE {
        ITEM_DOES_NOT_EXIST(1001), ITEM_ALREADY_EXIST(2001) ,ITEM_NAME_ALREADY_EXIST(2002),ITEM_INCOMPLETE_DELIVERY(3001),ITEM_INCOMPLETE_DELETION(3002);
        private int value;

        ERROR_CODE(int value) {
            this.value = value;
        }

        public int getValue() {
            return value;
        }
    }

    private ERROR_CODE errorCode;
    private int errorStatus;
    private String message;
    private LocalDateTime dateTime;


    public ExceptionResponse(ERROR_CODE errorCode, String message, LocalDateTime dateTime) {
        this.errorCode = errorCode;
        this.errorStatus = errorCode.value;
        this.message = message;
        this.dateTime = dateTime;
    }

    public ERROR_CODE getErrorCode() {
        return errorCode;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public LocalDateTime getDateTime() {
        return dateTime;
    }

    public void setDateTime(LocalDateTime dateTime) {
        this.dateTime = dateTime;
    }

    public int getErrorStatus() { return errorStatus; }
}