package model.exception;

public class HashException extends RuntimeException{
    public HashException(String wrongData) {
    super(wrongData);
}
}
