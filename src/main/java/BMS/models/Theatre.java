package BMS.models;

import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import lombok.Getter;
import lombok.Setter;

import java.util.List;
@Entity
@Getter
@Setter
public class Theatre extends BaseModel {
    private String name;
    private String Address;

    @OneToMany
    private List<Auditorium> auditoriums;

    @OneToOne
    private City city;

    public Theatre() {
    }

    public Theatre(String name, String address) {
        this.name = name;
        Address = address;
    }
}
