package service.exception;

/**
 * Exception which can be thrown in Service layer.
 * Used to transfer error messages from Service to View layer
 */
public class ServiceException extends RuntimeException{
    public ServiceException(String wrongData) {
        super(wrongData);
    }
}
