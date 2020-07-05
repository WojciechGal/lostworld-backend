package pl.lostworld.lostworldbackend.user.additionalResources.photo;

import lombok.extern.java.Log;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;
import pl.lostworld.lostworldbackend.templates.Pair;
import pl.lostworld.lostworldbackend.user.additionalResources.album.AlbumService;
import pl.lostworld.lostworldbackend.utils.ResponseUtils;

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
                return ResponseUtils.designCreatedResponse(photoRepository.save(dbPhotoFile).getId());
            } else {
                return ResponseUtils.designBadRequestResponse(violations
                        .stream()
                        .map(violation -> new Pair<>(violation.getPropertyPath().toString(), violation.getMessage()))
                        .collect(Collectors.toList()));
            }

        } catch (IOException e) {
            String message = "Error during processing files bytes";

            log.warning(message);
            e.printStackTrace();

            return ResponseUtils.designIAmATeapotResponse(message);
        }
    }

}
