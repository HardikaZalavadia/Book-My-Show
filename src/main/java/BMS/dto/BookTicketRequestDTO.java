package BMS.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.List;
@Getter
@Setter
public class BookTicketRequestDTO {
    private List<Integer> showSeatId;
    private int showId;
    private int userId;

}
