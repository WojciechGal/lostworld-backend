package pl.lostworld.lostworldbackend.continent;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import lombok.Getter;
import lombok.Setter;
import pl.lostworld.lostworldbackend.country.Country;
import pl.lostworld.lostworldbackend.rating.continent.ContinentRating;
import pl.lostworld.lostworldbackend.rating.country.CountryRating;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@Table(name = "continents")
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")
public class Continent {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    private String name;

    @ManyToMany(mappedBy = "continents")
    private List<Country> countries = new ArrayList<>();

    @Column(name = "updated_on")
    private Timestamp updatedOn;

    @PreUpdate
    public void preUpdate() {
        updatedOn = new Timestamp(System.currentTimeMillis());
    }

    @Column(name = "created_on")
    private Timestamp createdOn;

    @PrePersist
    public void prePersist() {
        createdOn = new Timestamp(System.currentTimeMillis());
    }

    @OneToMany(mappedBy = "continent")
    private List<ContinentRating> continentRatingList = new ArrayList<>();
}
