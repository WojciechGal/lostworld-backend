package pl.lostworld.lostworldbackend.user.additionalResources.plan;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import pl.lostworld.lostworldbackend.city.City;
import pl.lostworld.lostworldbackend.continent.Continent;
import pl.lostworld.lostworldbackend.country.Country;
import pl.lostworld.lostworldbackend.relic.Relic;
import pl.lostworld.lostworldbackend.user.User;
import pl.lostworld.lostworldbackend.user.additionalResources.report.Report;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@Table(name = "travel_plans")
public class TravelPlan {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @NotNull
    private User user;

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

    private String description;

    @OneToOne(mappedBy = "travelPlan")
    private Report report;
}

