package pl.lostworld.lostworldbackend.user;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import pl.lostworld.lostworldbackend.rating.city.CityRating;
import pl.lostworld.lostworldbackend.rating.continent.ContinentRating;
import pl.lostworld.lostworldbackend.rating.country.CountryRating;
import pl.lostworld.lostworldbackend.rating.relic.RelicRating;
import pl.lostworld.lostworldbackend.role.Role;
import pl.lostworld.lostworldbackend.validator.user.UniqueUserField;

import javax.persistence.*;
import javax.validation.constraints.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
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

    @CreationTimestamp
    private LocalDateTime createDateTime;

    @UpdateTimestamp
    private LocalDateTime updateDateTime;

    @OneToMany(mappedBy = "user")
    @JsonIgnore
    private List<ContinentRating> continentRatingList = new ArrayList<>();

    @OneToMany(mappedBy = "user")
    @JsonIgnore
    private List<CountryRating> countryRatingList = new ArrayList<>();

    @OneToMany(mappedBy = "user")
    @JsonIgnore
    private List<CityRating> cityRatingList = new ArrayList<>();

    @OneToMany(mappedBy = "user")
    @JsonIgnore
    private List<RelicRating> relicRatingList = new ArrayList<>();

    //users resources

}
