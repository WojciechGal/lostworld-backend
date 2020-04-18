package pl.lostworld.lostworldbackend.user.resources.plan;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import pl.lostworld.lostworldbackend.continent.Continent;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.Map;

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

    //todo sprawdzić czy w DB będą się zapisywać takie same klucze
    @OneToMany
    @JoinTable(name = "sequence_of_continents_in_travel_plan",
            joinColumns = {@JoinColumn(name = "travel_plan_id", referencedColumnName = "id")},
            inverseJoinColumns = {@JoinColumn(name = "continent_id", referencedColumnName = "id")})
    private Map<Integer, Continent> sequenceOfContinents;
}

