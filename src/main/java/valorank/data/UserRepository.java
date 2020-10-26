package valorank.data;

import org.springframework.data.repository.CrudRepository;
import valorank.User;

public interface UserRepository extends CrudRepository<User, Long> {
    User findByUsername(String username);
}
