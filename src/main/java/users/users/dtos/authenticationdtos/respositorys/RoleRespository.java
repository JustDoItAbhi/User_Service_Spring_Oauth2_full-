package users.users.dtos.authenticationdtos.respositorys;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import users.users.dtos.authenticationdtos.entity.Roles;

@Repository
public interface RoleRespository extends JpaRepository<Roles, Integer> {
    Roles findByRoleName(String name);
}
