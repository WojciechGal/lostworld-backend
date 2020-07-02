package pl.lostworld.lostworldbackend.user;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIdentityReference;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import pl.lostworld.lostworldbackend.city.City;
import pl.lostworld.lostworldbackend.continent.Continent;
import pl.lostworld.lostworldbackend.country.Country;
import pl.lostworld.lostworldbackend.rating.city.CityRating;
import pl.lostworld.lostworldbackend.rating.continent.ContinentRating;
import pl.lostworld.lostworldbackend.rating.country.CountryRating;
import pl.lostworld.lostworldbackend.rating.relic.RelicRating;
import pl.lostworld.lostworldbackend.relic.Relic;
import pl.lostworld.lostworldbackend.role.Role;
import pl.lostworld.lostworldbackend.user.additionalResources.album.Album;
import pl.lostworld.lostworldbackend.user.additionalResources.article.Article;
import pl.lostworld.lostworldbackend.user.additionalResources.plan.TravelPlan;
import pl.lostworld.lostworldbackend.user.additionalResources.report.Report;
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
    private String username;

    @Email
    @NotBlank
    @UniqueUserField(column = "email")
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
    @JsonIdentityInfo(generator= ObjectIdGenerators.PropertyGenerator.class, property="id")
    @JsonIdentityReference(alwaysAsId=true)
    private List<ContinentRating> continentRatingList = new ArrayList<>();

    @OneToMany(mappedBy = "user")
    @JsonIdentityInfo(generator= ObjectIdGenerators.PropertyGenerator.class, property="id")
    @JsonIdentityReference(alwaysAsId=true)
    private List<CountryRating> countryRatingList = new ArrayList<>();

    @OneToMany(mappedBy = "user")
    @JsonIdentityInfo(generator= ObjectIdGenerators.PropertyGenerator.class, property="id")
    @JsonIdentityReference(alwaysAsId=true)
    private List<CityRating> cityRatingList = new ArrayList<>();

    @OneToMany(mappedBy = "user")
    @JsonIdentityInfo(generator= ObjectIdGenerators.PropertyGenerator.class, property="id")
    @JsonIdentityReference(alwaysAsId=true)
    private List<RelicRating> relicRatingList = new ArrayList<>();

    //USERS RESOURCES

    @OneToMany(mappedBy = "user")
    private List<TravelPlan> travelPlans = new ArrayList<>();

    @OneToMany(mappedBy = "user")
    private List<Report> reports = new ArrayList<>();

    @ManyToMany
    @JoinTable(inverseJoinColumns = @JoinColumn(name = "observed_user_id"))
    @JsonIdentityInfo(generator= ObjectIdGenerators.PropertyGenerator.class, property="id")
    @JsonIdentityReference(alwaysAsId=true)
    private List<User> observedUsers = new ArrayList<>();

    @OneToMany(mappedBy = "user")
    private List<Article> articles = new ArrayList<>();

    @ManyToMany
    @JoinTable(inverseJoinColumns = @JoinColumn(name = "visited_continent_id"))
    @JsonIdentityInfo(generator= ObjectIdGenerators.PropertyGenerator.class, property="id")
    @JsonIdentityReference(alwaysAsId=true)
    private List<Continent> visitedContinents = new ArrayList<>();

    @ManyToMany
    @JoinTable(inverseJoinColumns = @JoinColumn(name = "visited_country_id"))
    @JsonIdentityInfo(generator= ObjectIdGenerators.PropertyGenerator.class, property="id")
    @JsonIdentityReference(alwaysAsId=true)
    private List<Country> visitedCountries = new ArrayList<>();

    @ManyToMany
    @JoinTable(inverseJoinColumns = @JoinColumn(name = "visited_city_id"))
    @JsonIdentityInfo(generator= ObjectIdGenerators.PropertyGenerator.class, property="id")
    @JsonIdentityReference(alwaysAsId=true)
    private List<City> visitedCities = new ArrayList<>();

    @ManyToMany
    @JoinTable(inverseJoinColumns = @JoinColumn(name = "visited_relic_id"))
    @JsonIdentityInfo(generator= ObjectIdGenerators.PropertyGenerator.class, property="id")
    @JsonIdentityReference(alwaysAsId=true)
    private List<Relic> visitedRelics = new ArrayList<>();

    @OneToMany(mappedBy = "user")
    private List<Album> albums = new ArrayList<>();

    /////////////////////////////////////////////////////////////////////////////
    //wprowadzenie oczekiwania przyjęcia do znajomych / oraz znajmoych
    @ManyToMany
    @JoinTable(inverseJoinColumns = @JoinColumn(name = "expected_user_id"))
    @JsonIdentityInfo(generator= ObjectIdGenerators.PropertyGenerator.class, property="id")
    @JsonIdentityReference(alwaysAsId=true)
    private List<User> expectedUsers = new ArrayList<>();

    @ManyToMany(mappedBy = "expectedUsers")
    @JsonIdentityInfo(generator= ObjectIdGenerators.PropertyGenerator.class, property="id")
    @JsonIdentityReference(alwaysAsId=true)
    private List<User> pendingUsers = new ArrayList<>();

    @ManyToMany
    @JoinTable(inverseJoinColumns = @JoinColumn(name = "friend_user_id"))
    @JsonIdentityInfo(generator= ObjectIdGenerators.PropertyGenerator.class, property="id")
    @JsonIdentityReference(alwaysAsId=true)
    private List<User> friends = new ArrayList<>();
    /////////////////////////////////////////////////////////////////////////////

    //todo należy wprowadzić zapis wiadomości pomiędzy uzytkownikami, którzy są znajomymi
}
