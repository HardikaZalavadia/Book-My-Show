package BMS.models;

import BMS.models.constant.SeatType;
import BMS.models.constant.ShowSeatStatus;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class ShowSeat extends BaseModel{
    private double price;

    @ManyToOne
    private Show show;

    @ManyToOne
    private Seat seat;

    @Enumerated(EnumType.STRING)
    private SeatType seatType;

    @Enumerated(EnumType.STRING)
    private ShowSeatStatus showSeatStatus;

    public ShowSeat() {
    }

    public ShowSeat(double price, Show show, Seat seat, ShowSeatStatus showSeatStatus, SeatType seatType) {
        this.price = price;
        this.show = show;
        this.seat = seat;
        this.showSeatStatus = showSeatStatus;
        this.seatType = seatType;
    }
}
/*   show       showSeat
           1            M

          seat      showSeat
            1           M
     */