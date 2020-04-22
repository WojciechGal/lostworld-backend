package pl.lostworld.lostworldbackend.templates.dbFile;

import org.springframework.web.multipart.MultipartFile;

import java.io.FileNotFoundException;

public interface DBFileService {

    public DBFile save(MultipartFile file);

    public DBFile checkById(Long id) throws FileNotFoundException;
}
