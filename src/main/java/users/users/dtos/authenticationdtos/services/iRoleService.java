package users.users.dtos.authenticationdtos.services;



import users.users.dtos.authenticationdtos.designation.RoleRequestDto;
import users.users.dtos.authenticationdtos.designation.RoleResponseDto;

public interface iRoleService {
    RoleResponseDto createRole(RoleRequestDto requestDto);
    RoleResponseDto getRoleById(int id);
}
