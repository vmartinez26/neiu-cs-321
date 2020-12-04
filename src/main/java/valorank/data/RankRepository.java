package valorank.data;

import org.springframework.data.repository.CrudRepository;
import valorank.Rank;


import java.util.List;


public interface RankRepository extends CrudRepository<Rank, String> {



    //List<Rank> findAllByRankById(Long id);
    //Rank findByName(String name);
    //System.out.print(Rank);

}
