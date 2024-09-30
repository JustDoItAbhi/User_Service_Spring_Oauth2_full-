package users.users.dtos.authenticationdtos.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import users.users.dtos.authenticationdtos.designation.RoleRequestDto;
import users.users.dtos.authenticationdtos.designation.RoleResponseDto;
import users.users.dtos.authenticationdtos.entity.Roles;
import users.users.dtos.authenticationdtos.respositorys.RoleRespository;
import users.users.dtos.authenticationdtos.respositorys.UserRespository;

@Service
public class RoleServiceImplI implements iRoleService {
    @Autowired
    private RoleRespository roleRespository;
    @Autowired
    private UserRespository userRespository;

    @Override
    public RoleResponseDto createRole(RoleRequestDto requestDto) {
        Roles roles=new Roles();
        roles.setRoleName(requestDto.getRoleName());
        roles.setRoleDiscription(requestDto.getRoleDiscription());
        roleRespository.save(roles);
        return RoleResponseDto.fromRole(roles);
    }

    @Override
    public RoleResponseDto getRoleById(int id) {
        return null;
    }
}