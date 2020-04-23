package pl.lostworld.lostworldbackend.user.additionalResources.photo;

import lombok.extern.java.Log;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
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
    public UploadFileResponse uploadPhoto(@RequestParam("file") MultipartFile photo) {
        DBFile savedPhoto = photoService.save(photo);

        String fileDownloadUri = ServletUriComponentsBuilder.fromCurrentContextPath()
                .path("/photos/download/")
                .path(savedPhoto.getId().toString())
                .toUriString();

        //return ResponseEntity.ok(new UploadFileResponse(savedPhoto.getFileName(), photo.getContentType(), photo.getSize()));
        return new UploadFileResponse(savedPhoto.getFileName(), fileDownloadUri,
                photo.getContentType(), photo.getSize());
    }

    @PostMapping("/multipleUpload")
    public List<UploadFileResponse> uploadMultiplePhotos(@RequestParam("files") MultipartFile[] photos) {
        return Arrays.stream(photos).map(this::uploadPhoto).collect(Collectors.toList());
    }

    @GetMapping("/download/{photoId}")
    public ResponseEntity<Resource> downloadFile(@PathVariable String photoId) throws FileNotFoundException {
        DBFile photo = photoService.checkById(Long.parseLong(photoId));

        return ResponseEntity.ok()
                .contentType(MediaType.parseMediaType(photo.getFileType()))
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + photo.getFileName() + "\"")
                .body(new ByteArrayResource(photo.getData()));
    }

    //TEST
    @GetMapping("/test")
    public ModelAndView test() {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("file/photoTester");
        return modelAndView;
    }

}
