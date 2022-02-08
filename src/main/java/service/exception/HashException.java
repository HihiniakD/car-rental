package service.exception;

/**
 * Exception which can be thrown when password can`t be hashed by algorithm
 * @see controller.security.Security#hashPassword(String)
 */
public class HashException extends RuntimeException{
    public HashException(String wrongData) {
    super(wrongData);
}
}
