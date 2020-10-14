package valorank.data;

import org.springframework.data.repository.CrudRepository;
import valorank.RankName;

import java.util.List;

public interface RankNameRepo extends CrudRepository<RankName, Long> {
    List<RankName> findAll();

}
