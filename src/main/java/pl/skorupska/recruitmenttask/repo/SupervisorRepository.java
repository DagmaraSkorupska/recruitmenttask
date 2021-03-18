package pl.skorupska.recruitmenttask.repo;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import pl.skorupska.recruitmenttask.domain.Supervisor;

@Repository
public interface SupervisorRepository extends CrudRepository<Supervisor, Long> {
}
