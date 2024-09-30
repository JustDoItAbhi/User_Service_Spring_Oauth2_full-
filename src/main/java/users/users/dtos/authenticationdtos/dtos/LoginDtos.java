package users.users.dtos.authenticationdtos.dtos;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class LoginDtos {
    private String email;
    private String password;
}
