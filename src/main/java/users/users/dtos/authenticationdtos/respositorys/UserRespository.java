package users.users.dtos.authenticationdtos.respositorys;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import users.users.dtos.authenticationdtos.entity.Users;


import java.util.Optional;

@Repository
public interface UserRespository extends JpaRepository<Users,Integer> {
   Optional<Users> findByUserEmail(String email);

   }
