package valorank.web;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;
import valorank.Rank;
import valorank.RankName;
import valorank.data.RankNameRepo;
import valorank.data.RankRepository;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Controller
@RequestMapping("/modify")
public class ModifyRankController {

    private RankRepository rankRepository;
    private RankNameRepo rankNameRepo;

    @Autowired
    public ModifyRankController(RankRepository rankRepository, RankNameRepo rankNameRepo){
        this.rankRepository = rankRepository;
        this.rankNameRepo = rankNameRepo;
    }

    @GetMapping("/{rankNameId}")
    public String updateRank(@PathVariable("rankNameId") long id, Model model){
        RankName rankName = rankNameRepo.findById(id).get();
        model.addAttribute("rankName", rankName);
        addRanksToModel(model);
        return "update-rank";
    }

    @PostMapping("/update/{rankId}")
    public String processUpdateRank(@PathVariable("rankId") long id, @Valid @ModelAttribute("rank") RankName rankName, Errors errors, Model model){
        if(errors.hasErrors())
            return "/update-rank";

        RankName newRank = rankNameRepo.findById(id).get();
        newRank.setRanks(rankName.getRanks());
        newRank.setGamesPlayed(rankName.getGamesPlayed());
        newRank.setLastTenGameW(rankName.getLastTenGameW());
        rankNameRepo.save(newRank);
        log.info("Processing" + newRank);
        return "redirect:/rank/current";
    }



    private void addRanksToModel(Model model){
        List<Rank> ranks = (List<Rank>) rankRepository.findAll();
        Rank.Type[] types = Rank.Type.values();
        for (Rank.Type type: types){
            model.addAttribute(type.toString().toLowerCase(), filterByType(ranks, type));
        }

    }
    private List<Rank> filterByType(List<Rank> ranks, Rank.Type type){
        return ranks
                .stream()
                .filter(x-> x.getType().equals(type))
                .collect(Collectors.toList());
    }
}
