package pl.lostworld.lostworldbackend.templates.dbFile;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class UploadFileResponse {

    private String fileName;

    private String fileType;

    private long size;
}
