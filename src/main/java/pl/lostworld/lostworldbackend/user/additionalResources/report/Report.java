package pl.lostworld.lostworldbackend.user.additionalResources.report;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIdentityReference;
import com.fasterxml.jackson.annotation.JsonSetter;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import pl.lostworld.lostworldbackend.templates.UserResource;
import pl.lostworld.lostworldbackend.user.additionalResources.album.Album;
import pl.lostworld.lostworldbackend.user.additionalResources.plan.TravelPlan;
import pl.lostworld.lostworldbackend.validator.AllExists;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@EqualsAndHashCode(callSuper = true)
@Entity
@Getter
@Setter
@NoArgsConstructor
@Table(name = "reports")
public class Report extends UserResource {

    public Report(Long id) {
        super(id);
    }

    @OneToOne(cascade = CascadeType.MERGE)
    private TravelPlan travelPlan;

    @OneToMany(mappedBy = "report")
    @JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")
    @JsonIdentityReference(alwaysAsId = true)
    @AllExists
    private List<Album> albums = new ArrayList<>();

    @JsonSetter("albums")
    public void setAlbumsByIds(List<Long> ids) {
        if (ids != null) {
            this.albums = ids.stream().map(Album::new).collect(Collectors.toList());
        } else {
            this.albums = null;
        }
    }
}
