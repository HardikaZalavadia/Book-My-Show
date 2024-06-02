package BMS.exception;

public class ShowSeatNotAvailableException extends RuntimeException {
    public ShowSeatNotAvailableException(String seatIsNotAvailable) {
        super(seatIsNotAvailable);
    }
}
