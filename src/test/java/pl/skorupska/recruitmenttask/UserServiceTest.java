package pl.skorupska.recruitmenttask;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import pl.skorupska.recruitmenttask.domain.Supervisor;
import pl.skorupska.recruitmenttask.domain.User;
import pl.skorupska.recruitmenttask.repo.UserRepository;
import pl.skorupska.recruitmenttask.service.UserService;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class UserServiceTest {
    @InjectMocks
    private UserService userService;

    @Mock
    UserRepository userRepository;

    @Test
    public void testGetAllUsers(){
        //Given
        List<User> userList = new ArrayList<>();
        Supervisor supervisor = new Supervisor("Jan", "Kowalski", "senior");
        User user = new User("Adam", "Nowak", "junior", supervisor);
        userList.add(user);
        when(userRepository.findAll()).thenReturn(userList);

        //When
        List<User> resultList = userService.getAllUsers();

        //than
        assertEquals(1, resultList.size());
        assertEquals("Adam", resultList.get(0).getFirstName());

        //cleanUp
        userRepository.deleteAll();
    }

    @Test
    public void testGetUserById(){
        //Given
        List<User> userList = new ArrayList<>();
        Supervisor supervisor = new Supervisor("Jan", "Kowalski", "senior");
        User user = new User("Adam", "Nowak", "junior", supervisor);
        userList.add(user);
        when(userRepository.findById(user.getId())).thenReturn(userList.stream().findAny());

        //When
        Optional<User> result = userService.getUser(user.getId());

        //than
        assertEquals(user.getId(), result.get().getId());
        assertEquals("Adam", Objects.requireNonNull(result.get()).getFirstName());

        //cleanUp
        userRepository.deleteAll();
    }

    @Test
    public void testSaveUser(){
        //Given
        Supervisor supervisor = new Supervisor("Jan", "Kowalski", "senior");
        User user = new User("Adam", "Nowak", "junior", supervisor);
        when(userRepository.save(user)).thenReturn(user);

        //When
        Optional<User> result = Optional.ofNullable(userService.saveUser(user));

        //than
        assertEquals("Nowak", result.get().getLastName());

        //cleanUp
        userRepository.deleteAll();
    }
}
