package pl.lostworld.lostworldbackend.user.resources.plan;

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
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@Table(name = "travel_plan")
public class TravelPlan {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @CreationTimestamp
    private LocalDateTime createDateTime;

    @UpdateTimestamp
    private LocalDateTime updateDateTime;

    @ManyToMany
    private List<Continent> sequenceOfContinents = new ArrayList<>();

    @ManyToMany
    private List<Country> sequenceOfCountries = new ArrayList<>();

    @ManyToMany
    private List<City> sequenceOfCities = new ArrayList<>();

    @ManyToMany
    private List<Relic> sequenceOfRelics = new ArrayList<>();

    @ManyToOne
    private User user;

    private String description;
}

