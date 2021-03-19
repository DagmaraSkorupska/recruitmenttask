package pl.skorupska.recruitmenttask.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import pl.skorupska.recruitmenttask.controller.exception.UserNotFoundException;
import pl.skorupska.recruitmenttask.domain.User;
import pl.skorupska.recruitmenttask.service.UserService;

import java.util.List;

@RestController
public class UserController {

    private UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping(value = "/users")
    public List<User> getUsers() {
        return userService.getAllUsers();
    }

    @GetMapping(value = "/users/{userId}")
    public User getUser(@PathVariable Long userId) throws UserNotFoundException {
        return userService.getUser(userId).orElseThrow(() -> new UserNotFoundException(userId));
    }

    @PostMapping("/addUsers")
    public User createUser(@RequestBody User user) {
        return userService.saveUser(user);
    }
}
