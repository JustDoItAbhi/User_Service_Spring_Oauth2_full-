package users.users.dtos.authenticationdtos.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import users.users.dtos.authenticationdtos.designation.RoleRequestDto;
import users.users.dtos.authenticationdtos.designation.RoleResponseDto;
import users.users.dtos.authenticationdtos.services.iRoleService;

@RestController
@RequestMapping("/role")
public class RolesController {
@Autowired
    private iRoleService roleService;
@PostMapping("/")
    public ResponseEntity<RoleResponseDto> createRole(@RequestBody RoleRequestDto requestDto){
    return ResponseEntity.ok(roleService.createRole(requestDto));
}
}
