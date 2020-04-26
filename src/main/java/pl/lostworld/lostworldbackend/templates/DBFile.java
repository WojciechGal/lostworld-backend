package pl.lostworld.lostworldbackend.templates;

import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import javax.validation.constraints.Max;
import java.time.LocalDateTime;

@Data
@MappedSuperclass
public class DBFile {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @CreationTimestamp
    private LocalDateTime createDateTime;

    @UpdateTimestamp
    private LocalDateTime updateDateTime;

    private String fileName;

    private String fileType;

    @Lob
    private byte[] data;

    //max 30 MB
    @Max(31457280)
    private long size;
}
