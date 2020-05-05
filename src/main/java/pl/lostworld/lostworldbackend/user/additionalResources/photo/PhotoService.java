package pl.lostworld.lostworldbackend.user.additionalResources.photo;

import lombok.extern.java.Log;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.ConstraintViolation;
import javax.validation.Validator;
import java.io.IOException;
import java.util.List;
import java.util.Set;

@Service
@Transactional
@Log
public class PhotoService {

    private Validator validator;

    private PhotoRepository photoRepository;

    public PhotoService(Validator validator, PhotoRepository photoRepository) {
        this.validator = validator;
        this.photoRepository = photoRepository;
    }

    public List<Photo> checkAll() {
        return photoRepository.findAll();
    }

    public Photo checkById(Long id) {
        return photoRepository.findById(id).orElse(null);
    }

    public ResponseEntity<Object> convertToPhotoValidateAndSave(MultipartFile multipartPhoto) {

        try {
            Photo dbPhotoFile = new Photo(multipartPhoto.getBytes(), multipartPhoto.getSize(), multipartPhoto.getOriginalFilename(), multipartPhoto.getContentType());

            Set<ConstraintViolation<Photo>> violations = validator.validate(dbPhotoFile);
            if (!violations.isEmpty()) {
                for (ConstraintViolation<Photo> constraintViolation : violations) {
                    System.out.println(constraintViolation.getPropertyPath() + " "
                            + constraintViolation.getMessage()); }
            } else {

            }
            dbPhotoFile.getFileType();
            photoRepository.save(dbPhotoFile);
            System.out.println("return");
            return new ResponseEntity<>(null);

        } catch (IOException e) {
            log.warning("Error during processing files bytes!");
            e.printStackTrace();


            return new ResponseEntity<>(null);
        }
    }

}
