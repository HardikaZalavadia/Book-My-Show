package BMS.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.List;
@Getter
@Setter
public class TicketRequestDTO {
    private int userId;
    private List<Integer> ticketId;

}
