package pl.lostworld.lostworldbackend.user.additionalResources.photo;

import lombok.Data;
import lombok.EqualsAndHashCode;
import pl.lostworld.lostworldbackend.templates.DBFile;

import javax.persistence.Entity;
import javax.persistence.Table;
import javax.validation.constraints.Pattern;

@EqualsAndHashCode(callSuper = true)
@Entity
@Table(name = "photos")
@Data
public class Photo extends DBFile {

    //przykrycie pola z klasy rodzicielskiej
    @Pattern(regexp = ".+(\\.jpg|\\.jpeg|\\.bmp|\\.png)")
    private String fileName;
}
