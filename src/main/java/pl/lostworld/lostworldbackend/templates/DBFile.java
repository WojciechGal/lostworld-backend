package pl.lostworld.lostworldbackend.templates;

import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import javax.validation.constraints.Max;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

@Data
@MappedSuperclass
public class DBFile {

    public DBFile(byte[] bytes, long size) {
        this.bytes = bytes;
        this.size = size;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @CreationTimestamp
    private LocalDateTime createDateTime;

    @UpdateTimestamp
    private LocalDateTime updateDateTime;

    @NotNull
    private String fileName;

    @NotNull
    private String fileType;

    @Lob
    @NotNull
    private byte[] bytes;

    //max 10 MB
    @Max(10485760)
    @NotNull
    private long size;
}
