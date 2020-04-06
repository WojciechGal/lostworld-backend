package pl.lostworld.lostworldbackend.country;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import lombok.Getter;
import lombok.Setter;
import pl.lostworld.lostworldbackend.continent.Continent;
import pl.lostworld.lostworldbackend.rating.country.CountryRating;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@Table(name = "countries")
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")
public class Country {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    private String name;

    @ManyToMany
    @NotEmpty
    private List<Continent> continents = new ArrayList<>();

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

    @OneToMany(mappedBy = "country")
    private List<CountryRating> countryRatingList = new ArrayList<>();
}
