package users.users.dtos.authenticationdtos.respositorys;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import users.users.dtos.authenticationdtos.entity.Sessions;

import java.util.Optional;

@Repository
public interface SessionRepository extends JpaRepository<Sessions, Integer> {
    Optional<Sessions> findByTokenAndUsersId(String token, int CustomerId);
    Sessions findByToken(String token);
}
