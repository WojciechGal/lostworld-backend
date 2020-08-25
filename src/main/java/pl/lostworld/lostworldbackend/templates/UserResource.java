package pl.lostworld.lostworldbackend.templates;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIdentityReference;
import com.fasterxml.jackson.annotation.JsonSetter;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import pl.lostworld.lostworldbackend.city.City;
import pl.lostworld.lostworldbackend.continent.Continent;
import pl.lostworld.lostworldbackend.country.Country;
import pl.lostworld.lostworldbackend.relic.Relic;
import pl.lostworld.lostworldbackend.user.User;
import pl.lostworld.lostworldbackend.validator.AllExists;
import pl.lostworld.lostworldbackend.validator.Exists;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Getter
@Setter
@NoArgsConstructor
@MappedSuperclass
public class UserResource {

    public UserResource(Long id) {
        this.id = id;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @CreationTimestamp
    private LocalDateTime createDateTime;

    @UpdateTimestamp
    private LocalDateTime updateDateTime;

    @ManyToOne
    @NotNull
    @JsonIdentityInfo(generator= ObjectIdGenerators.PropertyGenerator.class, property="id")
    @JsonIdentityReference(alwaysAsId=true)
    @Exists
    private User user;

    private String title;

    private String description;

    //encje terytorialne powinny być pobierane od encji dominującej, a jeśli takiej nie ma - ustawiane osobno
    @ManyToMany
    @JoinTable(inverseJoinColumns = @JoinColumn(name = "continent_in_sequence_id"))
    @JsonIdentityInfo(generator= ObjectIdGenerators.PropertyGenerator.class, property="id")
    @JsonIdentityReference(alwaysAsId=true)
    @AllExists
    private List<Continent> sequenceOfContinents = new ArrayList<>();

    @ManyToMany
    @JoinTable(inverseJoinColumns = @JoinColumn(name = "country_in_sequence_id"))
    @JsonIdentityInfo(generator= ObjectIdGenerators.PropertyGenerator.class, property="id")
    @JsonIdentityReference(alwaysAsId=true)
    @AllExists
    private List<Country> sequenceOfCountries = new ArrayList<>();

    @ManyToMany
    @JoinTable(inverseJoinColumns = @JoinColumn(name = "city_in_sequence_id"))
    @JsonIdentityInfo(generator= ObjectIdGenerators.PropertyGenerator.class, property="id")
    @JsonIdentityReference(alwaysAsId=true)
    @AllExists
    private List<City> sequenceOfCities = new ArrayList<>();

    @ManyToMany
    @JoinTable(inverseJoinColumns = @JoinColumn(name = "relic_in_sequence_id"))
    @JsonIdentityInfo(generator= ObjectIdGenerators.PropertyGenerator.class, property="id")
    @JsonIdentityReference(alwaysAsId=true)
    @AllExists
    private List<Relic> sequenceOfRelics = new ArrayList<>();

    @JsonSetter("user")
    public void setUserById(Long id) {
        if (id != null) {
            this.user = new User(id);
        } else {
            this.user = null;
        }
    }

    @JsonSetter("sequenceOfContinents")
    public void setSequenceOfContinentsByIds(List<Long> ids) {
        if (ids != null) {
            this.sequenceOfContinents = ids.stream().map(Continent::new).collect(Collectors.toList());
        } else {
            this.sequenceOfContinents = null;
        }
    }

    @JsonSetter("sequenceOfCountries")
    public void setSequenceOfCountriesByIds(List<Long> ids) {
        if (ids != null) {
            this.sequenceOfCountries = ids.stream().map(Country::new).collect(Collectors.toList());
        } else {
            this.sequenceOfCountries = null;
        }
    }

    @JsonSetter("sequenceOfCities")
    public void setSequenceOfCitiesByIds(List<Long> ids) {
        if (ids != null) {
            this.sequenceOfCities = ids.stream().map(City::new).collect(Collectors.toList());
        } else {
            this.sequenceOfCities = null;
        }
    }

    @JsonSetter("sequenceOfRelics")
    public void setSequenceOfRelicsByIds(List<Long> ids) {
        if (ids != null) {
            this.sequenceOfRelics = ids.stream().map(Relic::new).collect(Collectors.toList());
        } else {
            this.sequenceOfRelics = null;
        }
    }
}

