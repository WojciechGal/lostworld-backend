package pl.lostworld.lostworldbackend.user.additionalResources.photo;

import lombok.extern.java.Log;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@Service
@Transactional
@Log
public class PhotoService {

    private PhotoRepository photoRepository;

    public PhotoService(PhotoRepository photoRepository) {
        this.photoRepository = photoRepository;
    }

    public List<Photo> checkAll() {
        return photoRepository.findAll();
    }

    public Photo checkById(Long id) {
        return photoRepository.findById(id).orElse(null);
    }

    public ResponseEntity<Object> validateMultipartConvertToPhotoAndSave(MultipartFile multipartPhoto) {

        log.warning("Service...");

        Photo dbPhotoFile = new Photo();

        dbPhotoFile.setFileName(multipartPhoto.getOriginalFilename());
        dbPhotoFile.setFileType(multipartPhoto.getContentType());
        dbPhotoFile.setSize(multipartPhoto.getSize());

        try {
            dbPhotoFile.setData(multipartPhoto.getBytes());
        } catch (IOException e) {
            log.warning("Error during setting array of bites!");
            e.printStackTrace();
        }

        log.warning("Repo...");

        return photoRepository.save(dbPhotoFile);
    }

}
