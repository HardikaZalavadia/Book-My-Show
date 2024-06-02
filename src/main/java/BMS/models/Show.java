package BMS.models;

import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.List;

@Entity(name = "BMS_Show")
@Getter
@Setter
public class Show extends BaseModel{
    private LocalDateTime startTime;
    private LocalDateTime endTime;

    @ManyToOne
    private Movie movie;

    @ManyToOne
    private Auditorium auditorium;

    @OneToMany
    private List<ShowSeat> showSeats;

}
/*      show            Auditorium
         M                  1

        show            showSeat
          1                 M
 */
