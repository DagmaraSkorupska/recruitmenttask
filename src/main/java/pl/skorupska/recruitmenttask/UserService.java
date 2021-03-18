package pl.skorupska.recruitmenttask;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;
import pl.skorupska.recruitmenttask.domain.Supervisor;
import pl.skorupska.recruitmenttask.domain.User;
import pl.skorupska.recruitmenttask.repo.SupervisorRepository;
import pl.skorupska.recruitmenttask.repo.UserRepository;

@Component
public class UserService {

    private UserRepository userRepo;
    private SupervisorRepository supervisorRepo;

    @Autowired
    public UserService(UserRepository userRepo, SupervisorRepository supervisorRepo) {
        this.userRepo = userRepo;
        this.supervisorRepo = supervisorRepo;
    }

    @EventListener(ApplicationReadyEvent.class)
    public void runExample(){
        Supervisor supervisor = new Supervisor("Jan", "Kowalski", "senior", null);
        User user1 = new User("Adam", "Nowak", "junior", supervisor);
        userRepo.save(user1);

    }




}
