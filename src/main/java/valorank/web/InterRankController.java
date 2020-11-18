package valorank.web;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import valorank.Rank;
import valorank.Rank.Type;
import valorank.RankName;
import valorank.User;
import valorank.data.RankNameRepo;
import valorank.data.RankRepository;

import javax.validation.Valid;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Controller
@RequestMapping("/interiorrank")
public class InterRankController {

    private final RankRepository rankRepo;
    private final RankNameRepo rankNameRepo;

    @Autowired
    public InterRankController(RankRepository rankRepo, RankNameRepo rankNameRepo){
        this.rankRepo = rankRepo;
        this.rankNameRepo = rankNameRepo;
    }

    @GetMapping
    public String showDesignForm(){return "interiorrank";}

    @PostMapping
    public String processInteriorRank(@Valid @ModelAttribute("interiorrank") RankName rankName/*temp name*/,
                                      Errors errors, @AuthenticationPrincipal User user){
        if (errors.hasErrors())
            return "interiorrank";

        rankName.setUser(user);
        RankName savedRankName = rankNameRepo.save(rankName);

        log.info("Proccesing..." + rankName);
        return "redirect:/rank/current";
    }

    private void addUserInfoToModel(Model model, User user){
        model.addAttribute("username", user.getUsername());
    }

    @ModelAttribute
    public void addAttributes(Model model){
        List<Rank> ranks = (List<Rank>) rankRepo.findAll();
        Type[] types = Rank.Type.values();
        for (Type type: types){
            model.addAttribute(type.toString().toLowerCase(), filterByType(ranks, type));
        }

    }

    @ModelAttribute(name = "rankName")
    public RankName addRankNameToModel(){
        return new RankName();
    }

    private List<Rank> filterByType(List<Rank> ranks, Type type){
        return ranks
                .stream()
                .filter(x-> x.getType().equals(type))
                .collect(Collectors.toList());
    }

    /*private List<Rank> createRankingList(){
        List<Rank> ranks = Arrays.asList(
                new Rank("UR", "Unrated", Type.UNRATED),
                new Rank("I1", "Iron One", Type.IRON),
                new Rank("I2", "Iron two", Type.IRON),
                new Rank("I3", "Iron three", Type.IRON),
                new Rank("B1", "Bronze One", Type.BRONZE),
                new Rank("B2", "Bronze Two", Type.BRONZE),
                new Rank("B3", "Bronze Three", Type.BRONZE),
                new Rank("S1", "Silver One", Type.SILVER),
                new Rank("S2", "Silver Two", Type.SILVER),
                new Rank("S3", "Silver Three", Type.SILVER),
                new Rank("G1", "Gold One", Type.GOLD),
                new Rank("G2", "Gold Two", Type.GOLD),
                new Rank("G3", "Gold Three", Type.GOLD),
                new Rank("P1", "Plat One", Type.PLATINUM),
                new Rank("P2", "Plat Two", Type.PLATINUM),
                new Rank("P3", "Plat Three", Type.PLATINUM),
                new Rank("D1", "Diamond One", Type.DIAMOND),
                new Rank("D2", "Diamond Two", Type.DIAMOND),
                new Rank("D3", "Diamond Three", Type.DIAMOND),
                new Rank("IM1", "Immortal One", Type.IMMORTAL),
                new Rank("IM2", "Immortal Two", Type.IMMORTAL),
                new Rank("IM3", "Immortal Three", Type.IMMORTAL),
                new Rank("Rad", "Radiant", Type.RADIANT)



        );

        return ranks;

    }*/
}
