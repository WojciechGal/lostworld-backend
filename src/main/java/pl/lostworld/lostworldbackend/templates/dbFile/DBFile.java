package pl.lostworld.lostworldbackend.templates.dbFile;

import lombok.*;

import javax.persistence.*;

@Data
@MappedSuperclass
public class DBFile {

    public DBFile(String fileName, String fileType, byte[] data) {
        this.fileName = fileName;
        this.fileType = fileType;
        this.data = data;
    }

    public DBFile() {
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String fileName;

    private String fileType;

    @Lob
    private byte[] data;
}
