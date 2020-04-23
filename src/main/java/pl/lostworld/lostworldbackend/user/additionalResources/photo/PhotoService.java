package pl.lostworld.lostworldbackend.user.additionalResources.photo;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;
import pl.lostworld.lostworldbackend.exception.file.FileSaveException;
import pl.lostworld.lostworldbackend.templates.dbFile.DBFile;
import pl.lostworld.lostworldbackend.templates.dbFile.DBFileService;

import java.io.FileNotFoundException;
import java.io.IOException;

@Service
@Transactional
public class PhotoService implements DBFileService {

    private PhotoRepository photoRepository;

    public PhotoService(PhotoRepository photoRepository) {
        this.photoRepository = photoRepository;
    }

    @Override
    public DBFile save(MultipartFile file) {

        String fileName = StringUtils.cleanPath(file.getOriginalFilename());

        try {
            if (fileName.contains("..")) {
                throw new FileSaveException("Filename contains invalid path sequence - " + fileName);
            }

//            if (!file.matches(".+(\\.jpg|\\.png|\\.jpeg)")){
//                throw new FileSaveException("File is not a ")
//            }

            Photo photo = new Photo(fileName, file.getContentType(), file.getBytes());

            return photoRepository.save(photo);

        } catch (IOException ex) {
            throw new FileSaveException("Could not store file - " + fileName, ex);
        }
    }

    @Override
    public DBFile checkById(Long id) throws FileNotFoundException {

        return photoRepository.findById(id).orElseThrow(() -> new FileNotFoundException("File not found with id - " + id));
    }
}
