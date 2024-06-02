package BMS.models;

import BMS.models.constant.SeatStatus;
import BMS.models.constant.SeatType;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class Seat extends BaseModel{
    private int rowNo;
    private int col;
    private String seatNumber;

    @Enumerated(EnumType.STRING)
    private SeatStatus seatStatus;

    @Enumerated(EnumType.STRING)
    private SeatType seatType;

    public Seat() {
    }

    public Seat(int rowNo, int col, String seatNumber, SeatStatus seatStatus, SeatType seatType) {
        this.rowNo = rowNo;
        this.col = col;
        this.seatNumber = seatNumber;
        this.seatStatus = seatStatus;
        this.seatType = seatType;
    }
}
