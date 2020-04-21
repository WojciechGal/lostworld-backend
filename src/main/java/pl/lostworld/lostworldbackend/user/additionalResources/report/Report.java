package pl.lostworld.lostworldbackend.user.additionalResources.report;

import lombok.Getter;
import lombok.Setter;
import pl.lostworld.lostworldbackend.user.User;
import pl.lostworld.lostworldbackend.user.additionalResources.plan.TravelPlan;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity
@Getter
@Setter
@Table(name = "reports")
public class Report {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @NotNull
    private User user;

    @OneToOne
    private TravelPlan travelPlan;

    private String description;
}
