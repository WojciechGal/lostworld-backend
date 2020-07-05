package pl.lostworld.lostworldbackend.user.additionalResources.photo;

import lombok.extern.java.Log;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;
import pl.lostworld.lostworldbackend.templates.Pair;
import pl.lostworld.lostworldbackend.user.additionalResources.album.AlbumService;

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

    private AlbumService albumService;

    public PhotoService(Validator validator, PhotoRepository photoRepository, AlbumService albumService) {
        this.validator = validator;
        this.photoRepository = photoRepository;
        this.albumService = albumService;
    }

    public List<Photo> checkAll() {
        return photoRepository.findAll();
    }

    public Photo checkById(Long id) {
        return photoRepository.findById(id).orElse(null);
    }

    public ResponseEntity<?> convertToPhotoValidateAndSave(MultipartFile multipartPhoto) {

        try {
            Photo dbPhotoFile = new Photo(multipartPhoto.getBytes(), multipartPhoto.getSize(), multipartPhoto.getOriginalFilename(), multipartPhoto.getContentType(), albumService.checkById(1L));
            //todo KONIECZNA modyfikacja -> album jest hardcodowany

            Set<ConstraintViolation<Photo>> violations = validator.validate(dbPhotoFile);

            if (violations.isEmpty()) {
                Long generatedId = photoRepository.save(dbPhotoFile).getId();

                HttpStatus status = HttpStatus.CREATED;
                Map<String, Object> body = new LinkedHashMap<>();
                body.put("timestamp", new Date());
                body.put("status", status.value());
                body.put("id", generatedId);

                return new ResponseEntity<>(body, status);
            } else {
                HttpStatus status = HttpStatus.BAD_REQUEST;
                Map<String, Object> body = new LinkedHashMap<>();
                body.put("timestamp", new Date());
                body.put("status", status.value());
                body.put("errors", violations
                        .stream()
                        .map(violation -> new Pair<>(violation.getPropertyPath().toString(), violation.getMessage()))
                        .collect(Collectors.toList()));

                return new ResponseEntity<>(body, status);
            }

        } catch (IOException e) {
            String message = "Error during processing files bytes";

            log.warning(message);
            e.printStackTrace();

            HttpStatus status = HttpStatus.I_AM_A_TEAPOT;
            Map<String, Object> body = new LinkedHashMap<>();
            body.put("timestamp", new Date());
            body.put("status", status.value());
            body.put("message", message);

            return new ResponseEntity<>(body, status);
        }
    }

}
