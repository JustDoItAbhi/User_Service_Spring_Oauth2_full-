package users.users.dtos.authenticationdtos.dtos;

import lombok.Getter;
import lombok.Setter;
import users.users.dtos.authenticationdtos.designation.UserResponseDto;
import users.users.dtos.authenticationdtos.entity.SessionType;

@Setter
@Getter
public class ValidateTokenResponseDto {
private UserResponseDto userResponseDto;
private SessionType sessionType;
}
