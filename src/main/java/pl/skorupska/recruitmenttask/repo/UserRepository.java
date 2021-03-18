package pl.skorupska.recruitmenttask.repo;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import pl.skorupska.recruitmenttask.domain.User;

@Repository
public interface UserRepository extends CrudRepository<User, Long> {

}
