package pl.lostworld.lostworldbackend.user.resources.plan.sequence;

import lombok.Getter;
import lombok.Setter;
import pl.lostworld.lostworldbackend.continent.Continent;
import pl.lostworld.lostworldbackend.user.resources.plan.TravelPlan;

import javax.persistence.*;

@Entity
@Getter
@Setter
@Table(name = "travel_plan_continent_in_sequence")
public class ContinentInSequence {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private int positionInPlan;

    @ManyToOne
    private TravelPlan travelPlan;

    @ManyToOne
    private Continent continent;
}
