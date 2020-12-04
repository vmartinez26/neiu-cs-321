package valorank.security;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;
import valorank.RankName;
import valorank.User;
import valorank.data.RankNameRepo;
import valorank.data.UserRepository;

import javax.validation.Valid;

@Slf4j
@Controller
@RequestMapping("/updateU")
public class UpdateRegistrationController {
    private UserRepository userRepository;
    private PasswordEncoder passwordEncoder;
    private RankNameRepo rankNameRepo;

    @Autowired
    public UpdateRegistrationController(UserRepository userRepository, PasswordEncoder passwordEncoder){
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @GetMapping("/{userId}")
    public String updateUser(@PathVariable("userId") long id, Model model){
        User user = userRepository.findById(id).get();
        model.addAttribute("user", user);
        return "update-user";
    }

    
}
