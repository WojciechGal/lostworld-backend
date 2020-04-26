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

//    @ManyToMany
//    private List<Continent> continents = new ArrayList<>();
//
//    @ManyToMany
//    private List<Country> countries = new ArrayList<>();
//
//    @ManyToMany
//    private List<City> cities = new ArrayList<>();
//
//    @ManyToMany
//    private List<Relic> relics = new ArrayList<>();
}
