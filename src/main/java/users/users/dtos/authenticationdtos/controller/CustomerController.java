package users.users.dtos.authenticationdtos.controller;

import org.antlr.v4.runtime.misc.Pair;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.*;
import users.users.dtos.authenticationdtos.designation.UserResponseDto;
import users.users.dtos.authenticationdtos.*;
import users.users.dtos.authenticationdtos.dtos.LogOutDto;
import users.users.dtos.authenticationdtos.dtos.LoginDtos;
import users.users.dtos.authenticationdtos.dtos.ValidateRequestDto;
import users.users.dtos.authenticationdtos.dtos.ValidateTokenResponseDto;
import users.users.dtos.authenticationdtos.entity.LogoutType;
import users.users.dtos.authenticationdtos.entity.SessionType;
import users.users.dtos.authenticationdtos.entity.Users;
import users.users.dtos.authenticationdtos.services.Userservice;

import java.util.Optional;

@RestController
@RequestMapping("/users")
public class CustomerController {
@Autowired
    private Userservice userservice;
@PostMapping("/signup")
    public ResponseEntity<UserResponseDto> SignUp(@RequestBody SignUpDto dto){
    UserResponseDto userResponseDto=userservice.signUp(dto);
    return new ResponseEntity<>(userResponseDto, HttpStatus.CREATED);
}

    @PostMapping("/login")
    public ResponseEntity<UserResponseDto> login(@RequestBody LoginDtos dtos) {
        try {
            Pair<Users, MultiValueMap<String, String>> userWithHeaders =
                    userservice.login(dtos);
            if (userWithHeaders.a == null) {
                return new ResponseEntity<>(null, HttpStatus.UNAUTHORIZED);
            }
            return new ResponseEntity<>(UserResponseDto.fromUser(userWithHeaders.a),
                    userWithHeaders.b, HttpStatus.OK);
        }catch (InvalidCredentialsException exception){
            throw new RuntimeException(exception.getMessage());
        }
    }
    @PostMapping("/logout")
    public ResponseEntity<LogoutType> logout(@RequestBody LogOutDto dto){
    return ResponseEntity.ok( userservice.logout(dto));
    }
//    @DeleteMapping("{id}")
//    public ResponseEntity<Boolean> deleteId(@PathVariable ("id")int id) {
//        boolean isDeleted = userservice.deleteById(id);
//        if (isDeleted) {
//            // Return HTTP 200 OK status with no content
//            return ResponseEntity.ok().build();
//        } else {
//            // Return HTTP 404 Not Found if the customer does not exist
//            return ResponseEntity.notFound().build();
//        }
//    }
   @PostMapping("/validate")
        public ResponseEntity<ValidateTokenResponseDto> VALIDATE(@RequestBody ValidateRequestDto dto){
       Optional<UserResponseDto>UserResponseDto =userservice.validateToken(dto.getToken(),dto.getId());
       if (UserResponseDto.isEmpty()){
           ValidateTokenResponseDto validateTokenResponseDto =new ValidateTokenResponseDto();
           validateTokenResponseDto.setSessionType(SessionType.NOT_VALID);
           return new ResponseEntity<>(validateTokenResponseDto,HttpStatus.OK);
       }
       ValidateTokenResponseDto validateTokenResponseDto =new ValidateTokenResponseDto();
       validateTokenResponseDto.setSessionType(SessionType.ACTIVE);
       validateTokenResponseDto.setUserResponseDto(UserResponseDto.get());
       return new ResponseEntity<>(validateTokenResponseDto,HttpStatus.OK);
        }
////        @GetMapping("/{id}")
////    public ResponseEntity<UserResponseDto> getbYiD(@PathVariable ("id")int id){
////    return ResponseEntity.ok(Userservice.getByid(id));
////        }
//
//    @GetMapping("/{email}")
//    public ResponseEntity getbYiD(@PathVariable ("email")String email){
//        return ResponseEntity.ok(userservice.findByEmail(email));
//
//    }


}
