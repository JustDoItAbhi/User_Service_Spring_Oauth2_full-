package users.users.authorisation.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import users.users.dtos.authenticationdtos.entity.Users;
import users.users.dtos.authenticationdtos.respositorys.UserRespository;

import java.util.Optional;

@Service
public class CustomiseUserService implements UserDetailsService {
    @Autowired
    private UserRespository userRespository;
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<Users>savedUser=userRespository.findByUserEmail(username);
        if (!savedUser.isPresent()){
            throw new UsernameNotFoundException("CHECK USER EMAIL "+username);
        }
        Users users=savedUser.get();
        CustomiseUserDetails customiseUserDetails=new CustomiseUserDetails(savedUser.get());
        return customiseUserDetails;
    }
}
