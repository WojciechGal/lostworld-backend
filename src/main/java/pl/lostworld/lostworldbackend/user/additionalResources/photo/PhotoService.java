package pl.lostworld.lostworldbackend.user.additionalResources.photo;

import lombok.extern.java.Log;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;
import pl.lostworld.lostworldbackend.templates.Pair;

import javax.validation.ConstraintViolation;
import javax.validation.Validator;
import java.io.IOException;
import java.util.*;
import java.util.stream.Collectors;

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
                HttpStatus status = HttpStatus.BAD_REQUEST;
                Map<String, Object> body = new LinkedHashMap<>();
                body.put("timestamp", new Date());
                body.put("status", status.value());

                List<Pair<String, String>> errors = violations
                        .stream()
                        .map(violation -> new Pair<>(violation.getPropertyPath().toString(), violation.getMessage()))
                        .collect(Collectors.toList());

                body.put("errors", errors);

                return new ResponseEntity<>(body, status);
            } else {
                Long generatedId = photoRepository.save(dbPhotoFile).getId();

                HttpStatus status = HttpStatus.ACCEPTED;
                Map<String, Object> body = new LinkedHashMap<>();
                body.put("timestamp", new Date());
                body.put("status", status.value());
                body.put("message", "Successfull, generated ID: " + generatedId);

                return new ResponseEntity<>(body, status);
            }

        } catch (IOException e) {
            log.warning("Error during processing files bytes!");
            e.printStackTrace();


            HttpStatus status = HttpStatus.I_AM_A_TEAPOT;
            Map<String, Object> body = new LinkedHashMap<>();
            body.put("timestamp", new Date());
            body.put("status", status.value());
            body.put("message", "Error during processing files bytes!");

            return new ResponseEntity<>(body, status);
        }
    }

}
