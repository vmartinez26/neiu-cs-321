package valorank.web;

import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import valorank.RankName;
import valorank.User;
import valorank.data.RankNameRepo;

import java.util.List;

@Slf4j
@Controller
@RequestMapping("/showrankname")
public class ShowRankName {

    private final RankNameRepo rankNameRepo;

    public ShowRankName(RankNameRepo rankNameRepo) {
        this.rankNameRepo = rankNameRepo;
    }

    @GetMapping
    public String showTheName(){
        return "showrankname";
    }

    @ModelAttribute("showrn")
    public List<RankName> showrn(){
        return rankNameRepo.findAll();
    }

    @ModelAttribute
    public void addUser(Model model, @AuthenticationPrincipal User user){
        String username = user.getUsername();
        model.addAttribute("username",username);
    }
}
