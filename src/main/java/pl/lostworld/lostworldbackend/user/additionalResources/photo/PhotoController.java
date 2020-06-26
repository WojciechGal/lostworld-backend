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
    public ResponseEntity<?> uploadPhoto(@RequestParam("photo") MultipartFile multipartPhoto) {
        log.info("Photo uploading controller engaged");
        return photoService.convertToPhotoValidateAndSave(multipartPhoto);
    }

    @PostMapping("/multipleUpload")
    public List<ResponseEntity<?>> uploadMultiplePhotos(@RequestParam("photos") MultipartFile[] multipartPhotos) {
        //zawsze zwraca status 200 - dokładne statusy odpowiedzi znajdują wewnątrz obiektów
        return Arrays.stream(multipartPhotos).map(this::uploadPhoto).collect(Collectors.toList());
    }

    @GetMapping("/get/{photoId}")
    public Photo getPhoto(@PathVariable Long photoId) {
        //podczas produkcji JSON'a przez REST Controller dochodzi do automatycznej konwersji byte[] do String'a (base64)
        return photoService.checkById(photoId);
    }

}
