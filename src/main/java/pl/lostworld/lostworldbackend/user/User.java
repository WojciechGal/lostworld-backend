package pl.lostworld.lostworldbackend.user;

import lombok.Getter;
import lombok.Setter;
import pl.lostworld.lostworldbackend.role.Role;
import pl.lostworld.lostworldbackend.validator.user.UniqueUserField;

import javax.persistence.*;
import javax.validation.constraints.*;
import java.sql.Timestamp;
import java.util.Set;

@Entity
@Getter
@Setter
@Table(name = "users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    @UniqueUserField(column = "username")
    @Column(nullable = false, unique = true)
    private String username;

    @Email
    @NotBlank
    @UniqueUserField(column = "email")
    @Column(nullable = false, unique = true)
    private String email;

    @NotBlank
    @Size(min=8)
    private String password;

    private int enabled;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "user_role", joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "role_id"))
    private Set<Role> roles;

    @Column(name = "updated_on")
    private Timestamp updatedOn;

    @PreUpdate
    public void preUpdate() {
        updatedOn = new Timestamp(System.currentTimeMillis());
    }

    @Column(name = "created_on")
    private Timestamp createdOn;

    @PrePersist
    public void prePersist() {
        createdOn = new Timestamp(System.currentTimeMillis());
    }
}
