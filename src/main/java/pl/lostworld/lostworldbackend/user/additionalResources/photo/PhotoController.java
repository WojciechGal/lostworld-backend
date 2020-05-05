package pl.lostworld.lostworldbackend.user.additionalResources.photo;

import lombok.extern.java.Log;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/photos")
@Log
public class PhotoController {

    private PhotoService photoService;

    public PhotoController(PhotoService photoService) {
        this.photoService = photoService;
    }

    @PostMapping("/upload")
    public ResponseEntity<Object> uploadPhoto(@RequestParam("photo") MultipartFile multipartPhoto) {

        log.warning("Controller...");

        return photoService.validateMultipartConvertToPhotoAndSave(multipartPhoto);
    }

    @PostMapping("/multipleUpload")
    public List<ResponseEntity<Object>> uploadMultiplePhotos(@RequestParam("photos") MultipartFile[] photos) {
        return Arrays.stream(photos).map(this::uploadPhoto).collect(Collectors.toList());
    }

    @GetMapping("/get/{photoId}")
    public Photo getPhoto(@PathVariable Long photoId) {
        return photoService.checkById(photoId);
    }

}
