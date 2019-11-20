package pl.spring.models;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Date;

@Getter
@Setter
@Document(collection="user")
public class AppUser {

    @Id
    private String id;
    private String login;
    private String password;
    private String firstName;
    private String surname;
    private String email;

}
