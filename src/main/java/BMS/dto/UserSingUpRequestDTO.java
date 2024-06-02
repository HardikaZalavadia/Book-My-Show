package BMS.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserSingUpRequestDTO {
    private String name;
    private String password;
    private String emailId;
}
