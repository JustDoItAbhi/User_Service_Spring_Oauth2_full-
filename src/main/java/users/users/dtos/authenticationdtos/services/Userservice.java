package users.users.dtos.authenticationdtos.services;



import org.antlr.v4.runtime.misc.Pair;
import org.springframework.util.MultiValueMap;
import users.users.dtos.authenticationdtos.designation.UserResponseDto;
import users.users.dtos.authenticationdtos.dtos.LogOutDto;
import users.users.dtos.authenticationdtos.dtos.LoginDtos;
import users.users.dtos.authenticationdtos.SignUpDto;
import users.users.dtos.authenticationdtos.entity.LogoutType;
import users.users.dtos.authenticationdtos.entity.Users;

import java.util.Optional;
public interface Userservice {
    UserResponseDto signUp(SignUpDto dto);
    Pair<Users, MultiValueMap<String, String>> login(LoginDtos dtos);
    Optional<UserResponseDto> validateToken(String token, int customerId);
    LogoutType logout(LogOutDto dto);
   boolean deleteById(int id);
   UserResponseDto getByid(int id);
   UserResponseDto findByEmail(String email);

}
