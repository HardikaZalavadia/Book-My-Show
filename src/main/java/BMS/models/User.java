package BMS.models;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Entity(name = "BMS_User")
@Setter
@Getter
public class User  extends BaseModel{
    private String name;
    @Column(unique = true)
    private String email;
    private String password;
    private String phoneNumber;
    @OneToMany
    private List<Ticket> tickets;
}
