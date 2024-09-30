package users.users.dtos.authenticationdtos.designation;

import lombok.Getter;
import lombok.Setter;
import users.users.dtos.authenticationdtos.entity.Roles;
import users.users.dtos.authenticationdtos.entity.Users;

import java.util.ArrayList;
import java.util.List;
@Getter
@Setter
public class UserResponseDto {
    private int id;
    private String name;
    private String email;
    private String password;
    private List<RoleResponseDto> roles;
public static UserResponseDto fromUser(Users users){
    UserResponseDto userResponseDto=new UserResponseDto();
    userResponseDto.setId(users.getId());
    userResponseDto.setName(users.getName());
    userResponseDto.setEmail(users.getUserEmail());
    userResponseDto.setPassword(users.getPassword());
    //added role to userreposen;
    userResponseDto.roles=new ArrayList<>();
    for(Roles roles1:users.getRolesList()){
        RoleResponseDto responseDto=new RoleResponseDto();
        responseDto.setRoleName(roles1.getRoleName());
        userResponseDto.roles.add(responseDto);
    }
    return userResponseDto;
}
}
