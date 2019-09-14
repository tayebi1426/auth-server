package ir.demisco.cloud.auth.api;

import ir.demisco.cloud.auth.model.User;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

/**
 * Created by m.jokar on 9/12/2019.
 */
public interface UserRepository extends CrudRepository<User,Long> {

    List<User> findByUsername(String userName);
}
