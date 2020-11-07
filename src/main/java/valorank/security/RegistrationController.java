package valorank.security;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import valorank.User;
import valorank.data.UserRepository;

import javax.validation.Valid;
import java.util.List;

@Slf4j
@Controller
@RequestMapping("/register")
public class RegistrationController {
    private UserRepository userRepository;
    private PasswordEncoder passwordEncoder;

    @Autowired
    public RegistrationController(UserRepository userRepository, PasswordEncoder passwordEncoder){
        this.userRepository = userRepository;
        this.passwordEncoder =passwordEncoder;
    }

    @GetMapping
    public String registerForm(){
        return "registration";
    }

    @ModelAttribute(name = "registrationForm")
    public RegistrationForm addRegistrationFormToModel(){
        return new RegistrationForm();
    }
    @PostMapping
    public String processRegistration(@Valid @ModelAttribute("registrationForm") RegistrationForm registrationForm, Errors errors){
        if(errors.hasErrors())
            return "/registration";

        try{
        userRepository.save(registrationForm.toUser(passwordEncoder));

        } catch (DataIntegrityViolationException e){
            errors.rejectValue("username", "invalidUsername", "Username not available. Please choose another username");
            return "/registration";
        }
        return "redirect:/login";
    }
}
