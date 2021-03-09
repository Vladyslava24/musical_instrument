package ua.kpi.tef.musical_instrument.controller.exception;

public class UserNotFoundException extends Exception {
    public UserNotFoundException(String message) {
        super(message);
    }
}
