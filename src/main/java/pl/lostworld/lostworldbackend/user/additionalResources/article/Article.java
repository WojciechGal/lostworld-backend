package pl.lostworld.lostworldbackend.user.additionalResources.article;

import com.fasterxml.jackson.annotation.*;
import lombok.Getter;
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

@Entity
@Getter
@Setter
@Table(name = "articles")
public class Article {

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

    @ManyToMany
    @JoinTable(joinColumns = @JoinColumn(name = "article_id"), inverseJoinColumns = @JoinColumn(name = "continent_id"))
    @JsonIdentityInfo(generator= ObjectIdGenerators.PropertyGenerator.class, property="id")
    @JsonIdentityReference(alwaysAsId=true)
    @AllExists
    private List<Continent> continents = new ArrayList<>();

    @ManyToMany
    @JoinTable(joinColumns = @JoinColumn(name = "article_id"), inverseJoinColumns = @JoinColumn(name = "country_id"))
    @JsonIdentityInfo(generator= ObjectIdGenerators.PropertyGenerator.class, property="id")
    @JsonIdentityReference(alwaysAsId=true)
    @AllExists
    private List<Country> countries = new ArrayList<>();

    @ManyToMany
    @JoinTable(joinColumns = @JoinColumn(name = "article_id"), inverseJoinColumns = @JoinColumn(name = "city_id"))
    @JsonIdentityInfo(generator= ObjectIdGenerators.PropertyGenerator.class, property="id")
    @JsonIdentityReference(alwaysAsId=true)
    @AllExists
    private List<City> cities = new ArrayList<>();

    @ManyToMany
    @JoinTable(joinColumns = @JoinColumn(name = "article_id"), inverseJoinColumns = @JoinColumn(name = "relic_id"))
    @JsonIdentityInfo(generator= ObjectIdGenerators.PropertyGenerator.class, property="id")
    @JsonIdentityReference(alwaysAsId=true)
    @AllExists
    private List<Relic> relics = new ArrayList<>();

    @JsonSetter("user")
    public void setUserById(Long id) {
        if (id != null) {
            this.user = new User(id);
        } else {
            this.user = null;
        }
    }

    @JsonSetter("continents")
    public void setContinentsByIds(List<Long> ids) {
        if (ids != null) {
            this.continents = ids.stream().map(Continent::new).collect(Collectors.toList());
        } else {
            this.continents = null;
        }
    }

    @JsonSetter("countries")
    public void setCountriesByIds(List<Long> ids) {
        if (ids != null) {
            this.countries = ids.stream().map(Country::new).collect(Collectors.toList());
        } else {
            this.countries = null;
        }
    }

    @JsonSetter("cities")
    public void setCitiesByIds(List<Long> ids) {
        if (ids != null) {
            this.cities = ids.stream().map(City::new).collect(Collectors.toList());
        } else {
            this.cities = null;
        }
    }

    @JsonSetter("relics")
    public void setRelicsByIds(List<Long> ids) {
        if (ids != null) {
            this.relics = ids.stream().map(Relic::new).collect(Collectors.toList());
        } else {
            this.relics = null;
        }
    }
}
