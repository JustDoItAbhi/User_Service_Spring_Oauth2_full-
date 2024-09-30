package users.users.dtos.authenticationdtos.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
public class Roles extends Basemodels{
    @ManyToOne
    private Users users;
    private String roleName;
    private String roleDiscription;
}
