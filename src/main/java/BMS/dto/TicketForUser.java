package BMS.dto;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.List;

@Setter
@Getter
public class TicketForUser {
    private int ticketId;
    private int showId;
    private String movieName;
    private List<String> seatNumbers;
    private LocalDateTime startTime;
    private LocalDateTime endTime;
    private LocalDateTime bookingTime;
    private double totalAmount;
}
