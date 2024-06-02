package BMS.models;

import BMS.models.constant.AuditoriumFeature;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Entity
@Getter
@Setter
public class Auditorium extends BaseModel{
    private String name;
    private int capacity;

    @OneToMany(mappedBy = "auditorium")
    private List<Show> shows;

    @OneToMany
    private List<Seat> seats;

    @Enumerated(EnumType.STRING)
    @ElementCollection
    private List<AuditoriumFeature> auditoriumFeature;

}
