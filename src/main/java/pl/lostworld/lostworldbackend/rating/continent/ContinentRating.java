package pl.lostworld.lostworldbackend.rating.continent;

import pl.lostworld.lostworldbackend.continent.Continent;
import pl.lostworld.lostworldbackend.user.User;

import javax.persistence.*;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import java.sql.Timestamp;

@Entity
public class ContinentRating {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Min(1)
    @Max(10)
    private int value;

    @ManyToOne
    private User user;

    @ManyToOne
    private Continent continent;

    @Column(name = "updated_on")
    private Timestamp updatedOn;

    @PreUpdate
    public void preUpdate() {
        updatedOn = new Timestamp(System.currentTimeMillis());
    }

    @Column(name = "created_on")
    private Timestamp createdOn;

    @PrePersist
    public void prePersist() {
        createdOn = new Timestamp(System.currentTimeMillis());
    }
}
