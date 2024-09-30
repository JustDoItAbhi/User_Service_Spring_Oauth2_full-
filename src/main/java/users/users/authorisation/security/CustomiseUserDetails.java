package users.users.authorisation.security;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import users.users.dtos.authenticationdtos.entity.Roles;
import users.users.dtos.authenticationdtos.entity.Users;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
@JsonDeserialize
@NoArgsConstructor
public class CustomiseUserDetails implements UserDetails {
    private List<CustomiseGrantedAuthority> authorities;
    private String password;
    private String username;
    private boolean enabled;
    private boolean credentialsNonExpired;
    private boolean  accountNonLocked;
    private boolean accountNonExpired;
    private int userId;


    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public CustomiseUserDetails(Users users) {
//        this.users = users;
        this.password = users.getPassword();
        this.username = users.getUserEmail();
        this.enabled=true;
        this.accountNonExpired=true;
        this.accountNonLocked=true;
        this.credentialsNonExpired=true;
        this.userId= users.getId();



        List<CustomiseGrantedAuthority> grantedAuthorities = new ArrayList<>();

        for (Roles role: users.getRolesList()) {
            grantedAuthorities.add(new CustomiseGrantedAuthority(role));
        }

        this.authorities = grantedAuthorities;
    }



    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
//    List<CustomiseGrantedAuthority>grantedAuthorities=new ArrayList<>();
//    for (Roles roles1: users.getRolesList()){
//        grantedAuthorities.add(new CustomiseGrantedAuthority(roles1));
//    }

        return authorities;
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return username;
    }
//    public boolean isEnabled() {
//        return enable;
//    }

    @Override
    public boolean isAccountNonExpired() {
        return this.accountNonExpired;
    }

    @Override
    public boolean isAccountNonLocked() {
        return this.accountNonLocked;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return this.credentialsNonExpired;
    }

    @Override
    public boolean isEnabled() {
        return this.enabled;
    }

}
