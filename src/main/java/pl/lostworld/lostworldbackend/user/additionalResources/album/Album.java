package pl.lostworld.lostworldbackend.user.additionalResources.album;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import pl.lostworld.lostworldbackend.city.City;
import pl.lostworld.lostworldbackend.continent.Continent;
import pl.lostworld.lostworldbackend.country.Country;
import pl.lostworld.lostworldbackend.relic.Relic;
import pl.lostworld.lostworldbackend.user.User;
import pl.lostworld.lostworldbackend.user.additionalResources.photo.Photo;
import pl.lostworld.lostworldbackend.user.additionalResources.report.Report;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@Table(name = "albums")
public class Album {

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

    @ManyToOne
    private Report report;

    private String title;

    private String description;

    @OneToMany(mappedBy = "album")
    private List<Photo> photos = new ArrayList<>();

    //encje terytorialne powinny być pobierane od report, a jeśli takiego nie ma - ustawiane osobno
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

