package users.users.authorisation.security;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import users.users.dtos.authenticationdtos.entity.Roles;
import users.users.dtos.authenticationdtos.respositorys.RoleRespository;
@JsonDeserialize
@NoArgsConstructor
public class CustomiseGrantedAuthority implements GrantedAuthority {
//    private Roles roles;
    private String authority;

    public CustomiseGrantedAuthority(Roles roles) {
        this.authority = roles.getRoleName();
    }

    @Override
    public String getAuthority() {
        return authority;
    }
}
