package users.users.dtos.authenticationdtos.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.ManyToOne;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
@Entity
public class Sessions extends Basemodels{
private String token;
@Enumerated(EnumType.STRING)
private SessionType sessionType;
@ManyToOne
private Users users;
private Date expiryDate;
}
