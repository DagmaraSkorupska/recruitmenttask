package pl.skorupska.recruitmenttask;

import com.google.gson.Gson;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import pl.skorupska.recruitmenttask.controller.UserController;
import pl.skorupska.recruitmenttask.domain.Supervisor;
import pl.skorupska.recruitmenttask.domain.User;
import pl.skorupska.recruitmenttask.service.UserService;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.hasSize;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@WebMvcTest(UserController.class)
public class UserControllerTest {

    private MockMvc mockMvc;

    @Autowired
    public UserControllerTest(MockMvc mockMvc) {
        this.mockMvc = mockMvc;
    }

    @MockBean
    private UserService userService;

    @Test
    public void shouldGetUsers() throws Exception {
        //Given
        List<User> userList = new ArrayList<>();
        Supervisor supervisor = new Supervisor("Jan", "Kowalski", "senior");
        User user = new User("Adam", "Nowak", "junior", supervisor);
        userList.add(user);
        when(userService.getAllUsers()).thenReturn(userList);
        //When&Then
        mockMvc.perform(get("/users")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())

                .andExpect(jsonPath("$", hasSize(1)))
                .andExpect(jsonPath("$[0].firstName", is("Adam")))
                .andExpect(jsonPath("$[0].lastName", is("Nowak")))
                .andExpect(jsonPath("$[0].stand", is("junior")))
                .andExpect(jsonPath("$[0].supervisor.firstName", is("Jan")))
                .andExpect(jsonPath("$[0].supervisor.lastName", is("Kowalski")))
                .andExpect(jsonPath("$[0].supervisor.stand", is("senior")));
    }

    @Test
    public void shouldGetOneUser() throws Exception {
        //Given
        Supervisor supervisor = new Supervisor("Jan", "Kowalski", "senior");
        User user = new User("Adam", "Nowak", "junior", supervisor);
        user.setId(1L);
        when(userService.getUser(user.getId())).thenReturn(Optional.of(user));
        //When&Then
        mockMvc.perform(get("/users/1")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())

                .andExpect(jsonPath("$.firstName", is("Adam")))
                .andExpect(jsonPath("$.lastName", is("Nowak")))
                .andExpect(jsonPath("$.stand", is("junior")))
                .andExpect(jsonPath("$.supervisor.firstName", is("Jan")))
                .andExpect(jsonPath("$.supervisor.lastName", is("Kowalski")))
                .andExpect(jsonPath("$.supervisor.stand", is("senior")));
    }

    @Test
    public void shouldCreateUsers() throws Exception {
        //Given
        Supervisor supervisor = new Supervisor("Jan", "Kowalski", "senior");
        User user = new User("Adam", "Nowak", "junior", supervisor);
        when(userService.saveUser(user)).thenReturn(user);

        Gson gson = new Gson();
        String jsonContent = gson.toJson(user);

        //When&Then
        mockMvc.perform(post("/addUsers")
                .contentType(MediaType.APPLICATION_JSON)
                .characterEncoding("UTF-8")
                .content(jsonContent))
                .andExpect(status().isOk());
    }
}



















