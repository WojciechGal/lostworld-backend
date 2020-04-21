package pl.lostworld.lostworldbackend.user.additionalResources.report;

import lombok.Getter;
import lombok.Setter;
import pl.lostworld.lostworldbackend.user.additionalResources.plan.TravelPlan;

import javax.persistence.*;

@Entity
@Getter
@Setter
@Table(name = "reports")
public class Report {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne
    private TravelPlan travelPlan;

    private String description;
}
