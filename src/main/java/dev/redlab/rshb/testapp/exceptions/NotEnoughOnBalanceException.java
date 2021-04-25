package dev.redlab.rshb.testapp.exceptions;

public class NotEnoughOnBalanceException extends RuntimeException {

    public NotEnoughOnBalanceException(String message) {
        super(message);
    }

}
