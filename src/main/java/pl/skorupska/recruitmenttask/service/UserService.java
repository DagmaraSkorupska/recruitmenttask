package pl.skorupska.recruitmenttask.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Service;
import pl.skorupska.recruitmenttask.domain.Supervisor;
import pl.skorupska.recruitmenttask.domain.User;
import pl.skorupska.recruitmenttask.repo.UserRepository;


import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    private UserRepository userRepo;

    @Autowired
    public UserService(UserRepository userRepo) {
        this.userRepo = userRepo;
    }

    public List<User> getAllUsers() {
        return userRepo.findAll();
    }

    public Optional<User> getUser(Long id) {
        return userRepo.findById(id);
    }

    public User saveUser(User user) {
        return userRepo.save(user);
    }

    @EventListener(ApplicationReadyEvent.class)
    public void useradd() {
        Supervisor supervisor = new Supervisor("Jan", "Kowalski", "senior");
        saveUser(new User("Adam", "Nowak", "junior", supervisor));
    }
}
