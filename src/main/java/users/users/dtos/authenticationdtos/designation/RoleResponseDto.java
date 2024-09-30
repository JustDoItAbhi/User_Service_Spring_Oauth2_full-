package users.users.dtos.authenticationdtos.designation;

import lombok.Getter;
import lombok.Setter;
import users.users.dtos.authenticationdtos.entity.Roles;

@Getter
@Setter
public class RoleResponseDto {
    private int Id;
    private String roleName;
    private String RoleDiscription;
    public static RoleResponseDto fromRole(Roles roles){
        RoleResponseDto responseDto=new RoleResponseDto();
        responseDto.setId(roles.getId());
        responseDto.setRoleDiscription(roles.getRoleDiscription());
        responseDto.setRoleName(roles.getRoleName());
        return responseDto;
    }
}
