package users.users.dtos.authenticationdtos.dtos;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ValidateRequestDto {
    private int id;
    private String token;
}
