package gs.gs.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@Entity

public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String username;
    private String name;
    private String surname;
    private String password;
    @Column(nullable = false, unique = true)
    private String email;

    @OneToMany( fetch = FetchType.LAZY)
    private List<Order> orders;

    public User(String username, String password, String firstName, String lastName, String email) {
        this.username=username;
        this.password = password;
        this.name = firstName;
        this.surname = lastName;
        this.email = email;
        this.orders=new ArrayList<>();
    }

}
