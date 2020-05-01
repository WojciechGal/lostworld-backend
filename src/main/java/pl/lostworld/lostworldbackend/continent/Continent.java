package pl.lostworld.lostworldbackend.continent;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import pl.lostworld.lostworldbackend.country.Country;
import pl.lostworld.lostworldbackend.rating.continent.ContinentRating;
import pl.lostworld.lostworldbackend.user.additionalResources.article.Article;
import pl.lostworld.lostworldbackend.validator.continent.UniqueContinentField;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@Table(name = "continents")
public class Continent {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    @UniqueContinentField(column = "name")
    private String name;

    @ManyToMany(mappedBy = "continents")
    private List<Country> countries = new ArrayList<>();

    @CreationTimestamp
    private LocalDateTime createDateTime;

    @UpdateTimestamp
    private LocalDateTime updateDateTime;

    @OneToMany(mappedBy = "continent")
    @JsonIgnore
    private List<ContinentRating> continentRatingList = new ArrayList<>();

    private String description;

    @ManyToMany(mappedBy = "continents")
    private List<Article> articles = new ArrayList<>();
}
