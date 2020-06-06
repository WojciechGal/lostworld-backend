package pl.lostworld.lostworldbackend.templates;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import pl.lostworld.lostworldbackend.city.City;
import pl.lostworld.lostworldbackend.continent.Continent;
import pl.lostworld.lostworldbackend.country.Country;
import pl.lostworld.lostworldbackend.relic.Relic;
import pl.lostworld.lostworldbackend.user.User;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@MappedSuperclass
public class UserResource {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @CreationTimestamp
    private LocalDateTime createDateTime;

    @UpdateTimestamp
    private LocalDateTime updateDateTime;

    @ManyToOne
    @NotNull
    private User user;

    private String title;

    private String description;

    //encje terytorialne powinny być pobierane od encji dominującej, a jeśli takiej nie ma - ustawiane osobno
    @ManyToMany
    @JoinTable(inverseJoinColumns = @JoinColumn(name = "continent_in_sequence_id"))
    private List<Continent> sequenceOfContinents = new ArrayList<>();

    @ManyToMany
    @JoinTable(inverseJoinColumns = @JoinColumn(name = "country_in_sequence_id"))
    private List<Country> sequenceOfCountries = new ArrayList<>();

    @ManyToMany
    @JoinTable(inverseJoinColumns = @JoinColumn(name = "city_in_sequence_id"))
    private List<City> sequenceOfCities = new ArrayList<>();

    @ManyToMany
    @JoinTable(inverseJoinColumns = @JoinColumn(name = "relic_in_sequence_id"))
    private List<Relic> sequenceOfRelics = new ArrayList<>();
}

