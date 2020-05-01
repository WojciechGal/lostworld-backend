package pl.lostworld.lostworldbackend.user.additionalResources.report;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import pl.lostworld.lostworldbackend.user.User;
import pl.lostworld.lostworldbackend.user.additionalResources.plan.TravelPlan;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@Table(name = "reports")
public class Report {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @CreationTimestamp
    private LocalDateTime createDateTime;

    @UpdateTimestamp
    private LocalDateTime updateDateTime;

    @ManyToOne
    @NotNull
    private User user;

    @OneToOne
    private TravelPlan travelPlan;

    private String title;

    private String description;

    //todo czy jesli relacja nie ma planu to powinna mieć encje terytorialne?
    //todo może encje terytorialne osobno?
}
