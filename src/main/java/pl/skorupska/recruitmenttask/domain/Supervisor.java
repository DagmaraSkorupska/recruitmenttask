package pl.skorupska.recruitmenttask.domain;

import javax.persistence.*;
import java.util.List;

@Entity
public class Supervisor {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String firstName;
    private String lastName;
    private String stand;

    public Supervisor(String firstName, String lastName, String stand, List<User> user) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.stand = stand;
        this.user = user;
    }

    public Supervisor() {
    }

    @OneToMany(mappedBy = "supervisor")
    private List<User> user;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getStand() {
        return stand;
    }

    public void setStand(String stand) {
        this.stand = stand;
    }

    public List<User> getUser() {
        return user;
    }

    public void setUser(List<User> user) {
        this.user = user;
    }
}
