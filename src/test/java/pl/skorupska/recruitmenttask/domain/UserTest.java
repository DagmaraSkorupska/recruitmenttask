package pl.skorupska.recruitmenttask.domain;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import pl.skorupska.recruitmenttask.repo.UserRepository;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
public class UserTest {
    private final UserRepository userRepository;

    @Autowired
    public UserTest(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @BeforeEach
    public void cleanUp() {
        userRepository.deleteAll();
    }

    @Test
    public void saveNewUserTest() {
        //Given
        Supervisor supervisor = new Supervisor("Jan", "Kowalski", "senior");
        User user = new User("Adam", "Nowak", "junior", supervisor);
        userRepository.save(user);

        //When
        long countUser = userRepository.count();

        //Then
        assertEquals(1, countUser);

        //cleanUp
        userRepository.deleteAll();
    }

    @Test
    public void deleteUserTest() {
        //Given
        Supervisor supervisor = new Supervisor("Jan", "Kowalski", "senior");
        User user = new User("Adam", "Nowak", "junior", supervisor);
        User save = userRepository.save(user);

        //When
        userRepository.delete(save);
        long countUser = userRepository.count();

        //Then
        assertEquals(0, countUser);

        //cleanUp
        userRepository.deleteAll();
    }
}
