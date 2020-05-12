package pl.lostworld.lostworldbackend.user.additionalResources.article;

import lombok.Getter;
import lombok.Setter;
import pl.lostworld.lostworldbackend.city.City;
import pl.lostworld.lostworldbackend.continent.Continent;
import pl.lostworld.lostworldbackend.country.Country;
import pl.lostworld.lostworldbackend.relic.Relic;
import pl.lostworld.lostworldbackend.user.User;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@Table(name = "articles")
public class Article {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @NotNull
    private User user;

    private String title;

    private String description;

    @ManyToMany
    @JoinTable(joinColumns = @JoinColumn(name = "article_id"), inverseJoinColumns = @JoinColumn(name = "continent_id"))
    private List<Continent> continents = new ArrayList<>();

    @ManyToMany
    @JoinTable(joinColumns = @JoinColumn(name = "article_id"), inverseJoinColumns = @JoinColumn(name = "country_id"))
    private List<Country> countries = new ArrayList<>();

    @ManyToMany
    @JoinTable(joinColumns = @JoinColumn(name = "article_id"), inverseJoinColumns = @JoinColumn(name = "city_id"))
    private List<City> cities = new ArrayList<>();

    @ManyToMany
    @JoinTable(joinColumns = @JoinColumn(name = "article_id"), inverseJoinColumns = @JoinColumn(name = "relic_id"))
    private List<Relic> relics = new ArrayList<>();
}
