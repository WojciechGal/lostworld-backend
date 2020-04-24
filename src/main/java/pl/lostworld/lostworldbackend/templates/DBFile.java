package pl.lostworld.lostworldbackend.templates;

import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.Max;

@Data
@MappedSuperclass
public class DBFile {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String fileName;

    private String fileType;

    @Lob
    private byte[] data;

    //max 30 MB
    @Max(31457280)
    private long size;
}
