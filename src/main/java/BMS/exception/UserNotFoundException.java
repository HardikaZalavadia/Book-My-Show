package BMS.exception;

public class UserNotFoundException extends RuntimeException {
    public UserNotFoundException(String userIsNotFound) {
        super(userIsNotFound);
    }
}
