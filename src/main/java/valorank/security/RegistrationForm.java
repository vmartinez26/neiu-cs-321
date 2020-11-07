package valorank.security;

import lombok.Data;
import org.springframework.security.crypto.password.PasswordEncoder;
import valorank.User;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

@Data

public class RegistrationForm {

    @NotNull
    @Size(min = 3, message =  "Username must have at least 3 characters")
    private String username;

    @NotNull
    @Size(min = 6, message = "Password must have at least 6 characters")
    private String password;

    @NotNull
    @NotEmpty(message = "Name is required")
    private String fullName;

    @NotNull
    @NotEmpty(message = "Email is required")
    private String email;

    @NotNull
    @NotEmpty(message = "City is required")
    private String city;

    @NotNull
    @Pattern(regexp = "[A-Z]{2}", message = "State must be two characters")
    private String state;

    public User toUser(PasswordEncoder passwordEncoder){
        return new User(username,passwordEncoder.encode(password), fullName,email,city,state);
    }



}
