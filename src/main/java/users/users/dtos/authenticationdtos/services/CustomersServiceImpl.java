package users.users.dtos.authenticationdtos.services;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import org.antlr.v4.runtime.misc.Pair;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.util.MultiValueMap;
import org.springframework.util.MultiValueMapAdapter;
import users.users.dtos.authenticationdtos.entity.*;
import users.users.dtos.authenticationdtos.designation.UserResponseDto;
import users.users.dtos.authenticationdtos.dtos.LogOutDto;
import users.users.dtos.authenticationdtos.dtos.LoginDtos;
import users.users.dtos.authenticationdtos.SignUpDto;
import users.users.dtos.authenticationdtos.respositorys.RoleRespository;
import users.users.dtos.authenticationdtos.respositorys.SessionRepository;
import users.users.dtos.authenticationdtos.respositorys.UserRespository;

import java.security.KeyPair;
import java.util.*;

@Service
public class CustomersServiceImpl implements Userservice {
    private UserRespository userRespository;
    private RoleRespository roleRespository;
    private SessionRepository sessionRepository;
    private BCryptPasswordEncoder passwordEncoder;

    public CustomersServiceImpl(UserRespository userRespository,
                                RoleRespository roleRespository,
                                SessionRepository sessionRepository,
                                BCryptPasswordEncoder passwordEncoder) {
        this.userRespository = userRespository;
        this.roleRespository = roleRespository;
        this.sessionRepository = sessionRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public UserResponseDto signUp(SignUpDto dto) {
        Roles roles=roleRespository.findByRoleName(dto.getRole().describeConstable().orElseThrow());
        Users users=new Users();
        users.setUserEmail(dto.getEmail());
        users.setName(dto.getName());
        users.setPassword(passwordEncoder.encode(dto.getPassword()));
        users.setRolesList(List.of(roles));
        userRespository.save(users);
        return UserResponseDto.fromUser(users);
    }

    @Override
    public Pair<Users, MultiValueMap<String, String>> login(LoginDtos dtos) {
       Optional<Users> savedUser =userRespository.findByUserEmail(dtos.getEmail());
       if (savedUser.isEmpty()){
           throw new RuntimeException("USER not EXISTS "+ dtos.getEmail());
       }
        Users users= savedUser.get();

       if (!passwordEncoder.matches(dtos.getPassword(),users.getPassword())){
           throw new RuntimeException("PASSWORD NOT MATCH "+ dtos.getPassword());
       }
      UserResponseDto userResponseDto= UserResponseDto.fromUser(users);
        Map<String, Object> claims=new HashMap<>();
        claims.put("USER_NAME__",userResponseDto.getName());
//        claims.put("USER_EMAIL__",userResponseDto.getEmail());
        claims.put("USER_ROLE__",userResponseDto.getRoles());
        KeyPair keyPair=Keys.keyPairFor(SignatureAlgorithm.ES256);

        String token=Jwts.builder()
                .addClaims(claims)
                .signWith(keyPair.getPrivate(),SignatureAlgorithm.ES256)
                .compact();

        MultiValueMapAdapter<String,String>header=new MultiValueMapAdapter<>(new HashMap<>());
        header.add("AUTHTOKEN",token);
        header.add("USER",users.getUserEmail());
        Pair<Users, MultiValueMap<String, String>>p=new Pair<>(users,header);
        Sessions sessions=new Sessions();
        sessions.setSessionType(SessionType.ACTIVE);
        sessions.setUsers(users);
        sessions.setToken(token);
        sessionRepository.save(sessions);
       return p;
    }

    @Override
    public Optional<UserResponseDto> validateToken(String token, int userid) {
        Optional<Sessions> savedSessions=sessionRepository.findByTokenAndUsersId(token,userid);
        if(token==null|| userid==0){
            throw new RuntimeException("SESSION IS NOT VALID "+token);
        }
        if(!savedSessions.isPresent()){
            throw new RuntimeException("SESSION IS NOT VALID ");
        }
        Sessions sessions=savedSessions.get();
        Users users=userRespository.findById(userid).orElseThrow(
                ()->new RuntimeException(" USER IF NOT VALID "+userid));
        UserResponseDto userResponseDto=UserResponseDto.fromUser(users);
        return Optional.of(userResponseDto);
    }

    @Override
    public LogoutType logout(LogOutDto dto) {
//        Users users=userRespository.findByUserEmail(dto.getEmail()).orElseThrow(
//                ()->new RuntimeException("USER EMAIL NOT VALID "+dto.getEmail()));
        Sessions sessions=sessionRepository.findByToken(dto.getToken());
        if(sessions.getUsers()==null){
            throw new RuntimeException("LOGOUT NOT VALID "+dto.getToken());
        }
        sessions.setSessionType(SessionType.LOGOUT);

        return LogoutType.LOGOUT;
    }

    @Override
    public boolean deleteById(int id) {
        return false;
    }

    @Override
    public UserResponseDto getByid(int id) {
        return null;
    }

    @Override
    public UserResponseDto findByEmail(String email) {
        return null;
    }
}
