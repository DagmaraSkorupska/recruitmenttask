package pl.skorupska.recruitmenttask.repo;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import pl.skorupska.recruitmenttask.domain.User;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends CrudRepository<User, Long> {
    @Override
    List<User> findAll();

    @Override
    Optional<User> findById(Long id);
}
