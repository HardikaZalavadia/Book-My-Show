package BMS.models;

import BMS.models.constant.MovieFeature;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.List;

@Entity
@Getter
@Setter
public class Movie extends BaseModel{
    private String name;
    private LocalDateTime releaseDate;
    private String description;
    private double ratings;

    @OneToMany
    private List<Actor> actors;
    @Enumerated(EnumType.STRING)
    @ElementCollection
    private List<MovieFeature> movieFeatures;

    public Movie() {
    }

    public Movie(String name, String description) {
        this.name = name;
        this.description = description;
    }
}
