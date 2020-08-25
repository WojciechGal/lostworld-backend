package pl.lostworld.lostworldbackend.user.additionalResources.album;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIdentityReference;
import com.fasterxml.jackson.annotation.JsonSetter;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import pl.lostworld.lostworldbackend.templates.UserResource;
import pl.lostworld.lostworldbackend.user.additionalResources.photo.Photo;
import pl.lostworld.lostworldbackend.user.additionalResources.report.Report;
import pl.lostworld.lostworldbackend.validator.Exists;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@EqualsAndHashCode(callSuper = true)
@Entity
@Getter
@Setter
@NoArgsConstructor
@Table(name = "albums")
public class Album extends UserResource {

    public Album(Long id) {
        super(id);
    }

    @ManyToOne
    @JsonIdentityInfo(generator= ObjectIdGenerators.PropertyGenerator.class, property="id")
    @JsonIdentityReference(alwaysAsId=true)
    @Exists
    private Report report;

    @OneToMany(mappedBy = "album")
    private List<Photo> photos = new ArrayList<>();

    @JsonSetter("report")
    public void setReportById(Long id) {
        if (id != null) {
            this.report = new Report(id);
        } else {
            this.report = null;
        }
    }
}

