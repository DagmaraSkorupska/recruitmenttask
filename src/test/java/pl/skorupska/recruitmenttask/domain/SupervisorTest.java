package pl.skorupska.recruitmenttask.domain;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import pl.skorupska.recruitmenttask.repo.SupervisorRepository;
import pl.skorupska.recruitmenttask.repo.UserRepository;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
public class SupervisorTest {
    private final SupervisorRepository supervisorRepository;
    private final UserRepository userRepository;

    @Autowired
    public SupervisorTest(SupervisorRepository supervisorRepository, UserRepository userRepository) {
        this.supervisorRepository = supervisorRepository;
        this.userRepository = userRepository;
    }

    @BeforeEach
    public void cleanUp() {
        userRepository.deleteAll();
        supervisorRepository.deleteAll();
    }

    @Test
    public void saveNewSupervisorTest() {
        //Given
        Supervisor supervisor = new Supervisor("Jan", "Kowalski", "senior");
        supervisorRepository.save(supervisor);

        //When
        long countSupervisor = supervisorRepository.count();

        //Then
        assertEquals(1, countSupervisor);

        //cleanUp
        supervisorRepository.deleteAll();
    }

    @Test
    public void deleteSupervisorTest() {
        //Given
        Supervisor supervisor = new Supervisor("Jan", "Kowalski", "senior");
        Supervisor save = supervisorRepository.save(supervisor);

        //When
        supervisorRepository.delete(save);
        long countSupervisor= supervisorRepository.count();

        //Then
        assertEquals(0, countSupervisor);

        //cleanUp
        supervisorRepository.deleteAll();
    }
}
