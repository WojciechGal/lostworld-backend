package pl.lostworld.lostworldbackend.user.additionalResources.plan;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import pl.lostworld.lostworldbackend.templates.UserResource;
import pl.lostworld.lostworldbackend.user.additionalResources.report.Report;

import javax.persistence.*;

@EqualsAndHashCode(callSuper = true)
@Entity
@Getter
@Setter
@Table(name = "travel_plans")
public class TravelPlan extends UserResource {

    @OneToOne(mappedBy = "travelPlan")
    private Report report;
}

