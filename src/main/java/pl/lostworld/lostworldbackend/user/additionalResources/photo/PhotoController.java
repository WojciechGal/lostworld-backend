package pl.lostworld.lostworldbackend.user.additionalResources.photo;

import lombok.extern.java.Log;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import pl.lostworld.lostworldbackend.templates.dbFile.DBFile;
import pl.lostworld.lostworldbackend.templates.dbFile.DBFileService;
import pl.lostworld.lostworldbackend.templates.dbFile.UploadFileResponse;

import java.io.FileNotFoundException;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@Log
@RequestMapping("/photos")
public class PhotoController {

   private DBFileService photoService;

    public PhotoController(DBFileService photoService) {
        this.photoService = photoService;
    }

    @PostMapping("/upload")
    public ResponseEntity uploadPhoto(@RequestParam("photo") MultipartFile photo) {
        DBFile savedPhoto = photoService.save(photo);

//        String fileDownloadUri = ServletUriComponentsBuilder.fromCurrentContextPath()
//                .path("/downloadFile/")
//                .path(dbFile.getId())
//                .toUriString();

        return ResponseEntity.ok(new UploadFileResponse(savedPhoto.getFileName(), photo.getContentType(), photo.getSize()));
    }

    @PostMapping("/multipleUpload")
    public List<ResponseEntity> uploadMultiplePhotos(@RequestParam("files") MultipartFile[] photos) {
        return Arrays.stream(photos).map(this::uploadPhoto).collect(Collectors.toList());
    }

    @GetMapping("/download/{photoId}")
    public ResponseEntity downloadPhoto(@PathVariable Long photoId) {

        try {
            DBFile photo = photoService.checkById(photoId);

            return ResponseEntity.ok()
                    .contentType(MediaType.parseMediaType(photo.getFileType()))
                    .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + photo.getFileName() + "\"")
                    .body(new ByteArrayResource(photo.getData()));

        } catch (FileNotFoundException e) {
            e.printStackTrace();
            return (ResponseEntity) ResponseEntity.notFound();
        }
    }

}
