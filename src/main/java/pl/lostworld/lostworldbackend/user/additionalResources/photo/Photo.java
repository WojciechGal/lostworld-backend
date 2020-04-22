package pl.lostworld.lostworldbackend.user.additionalResources.photo;

import pl.lostworld.lostworldbackend.templates.dbFile.DBFile;

import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "photos")
public class Photo extends DBFile {

    public Photo(String fileName, String contentType, byte[] bytes) {
        super(fileName, contentType, bytes);
    }

    public Photo() {
        super();
    }
}
