package valorank.data;

import org.springframework.data.repository.CrudRepository;
import valorank.User;

import java.util.List;

public interface UserRepository extends CrudRepository<User, Long> {
    User findByUsername(String username);
}
