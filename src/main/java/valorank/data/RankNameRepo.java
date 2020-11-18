package valorank.data;

import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.CrudRepository;
import valorank.RankName;
import valorank.User;

import java.util.List;

public interface RankNameRepo extends CrudRepository<RankName, Long> {
    List<RankName> findAll();

    List<RankName> findAllByUser(User user, Pageable pageable);
}
