package valorank.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/rank")
public class RankingController {
    @GetMapping("/current")
    public String showRank(){
        return "showRank";
    }
}