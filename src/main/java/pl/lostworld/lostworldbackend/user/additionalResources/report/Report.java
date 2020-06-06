package pl.lostworld.lostworldbackend.user.additionalResources.report;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import pl.lostworld.lostworldbackend.templates.UserResource;
import pl.lostworld.lostworldbackend.user.additionalResources.album.Album;
import pl.lostworld.lostworldbackend.user.additionalResources.plan.TravelPlan;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@EqualsAndHashCode(callSuper = true)
@Entity
@Getter
@Setter
@Table(name = "reports")
public class Report extends UserResource {

    @OneToOne
    private TravelPlan travelPlan;

    @OneToMany(mappedBy = "report")
    private List<Album> albums = new ArrayList<>();
}
