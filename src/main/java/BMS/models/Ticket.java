package BMS.models;

import BMS.models.constant.TicketStatus;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.List;

@Entity
@Setter
@Getter
public class Ticket extends BaseModel{

    private LocalDateTime bookingTime;
    private double totalAmount;

    @ManyToOne
    private Show shows;

    @OneToMany
    private List<Payment> payments;

    @ManyToMany
    private List<ShowSeat> showSeats;

    @Enumerated(EnumType.STRING)
    private TicketStatus ticketStatus;

}
