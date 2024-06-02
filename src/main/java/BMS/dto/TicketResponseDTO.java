package BMS.dto;

import BMS.models.Ticket;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
public class TicketResponseDTO {
    private int userId;

    private List<TicketForUser> ticketForUsers;

}
