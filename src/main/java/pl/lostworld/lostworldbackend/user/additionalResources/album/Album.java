package pl.lostworld.lostworldbackend.user.additionalResources.album;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import pl.lostworld.lostworldbackend.templates.UserResource;
import pl.lostworld.lostworldbackend.user.additionalResources.photo.Photo;
import pl.lostworld.lostworldbackend.user.additionalResources.report.Report;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@EqualsAndHashCode(callSuper = true)
@Entity
@Getter
@Setter
@Table(name = "albums")
public class Album extends UserResource {

    @ManyToOne
    private Report report;

    @OneToMany(mappedBy = "album")
    private List<Photo> photos = new ArrayList<>();
}

