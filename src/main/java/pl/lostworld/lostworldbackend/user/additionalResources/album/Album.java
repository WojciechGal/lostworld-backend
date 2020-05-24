package pl.lostworld.lostworldbackend.user.additionalResources.album;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import pl.lostworld.lostworldbackend.user.User;
import pl.lostworld.lostworldbackend.user.additionalResources.photo.Photo;
import pl.lostworld.lostworldbackend.user.additionalResources.report.Report;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@Table(name = "albums")
public class Album {

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

    @ManyToOne
    private Report report;

    private String title;

    private String description;

    @OneToMany(mappedBy = "album")
    private List<Photo> photos = new ArrayList<>();

    //todo czy jesli album nie ma reportażu to powinien mieć encje terytorialne?
    //todo może encje terytorialne osobno?
}

