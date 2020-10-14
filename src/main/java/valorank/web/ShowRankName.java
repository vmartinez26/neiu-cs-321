package valorank.web;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import valorank.RankName;
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
    //Still need to figure out how to get the single rank name from
}
