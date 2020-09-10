package valorank.web;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import valorank.Rank;
import valorank.Rank.Type;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Controller
@RequestMapping("/interiorrank")
public class InterRankController {

    @GetMapping
    public String showDesignForm(){return "interiorrank";}

    @ModelAttribute
    public void addAttributes(Model model){
        List<Rank> ranks = createRankingList();
        Type[] types = Rank.Type.values();
        for (Type type: types){
            model.addAttribute(type.toString().toLowerCase(), filterByType(ranks, type));
        }
    }

    private List<Rank> filterByType(List<Rank> ranks, Type type){
        return ranks
                .stream()
                .filter(x-> x.getType().equals(type))
                .collect(Collectors.toList());
    }

    private List<Rank> createRankingList(){
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

    }
}
