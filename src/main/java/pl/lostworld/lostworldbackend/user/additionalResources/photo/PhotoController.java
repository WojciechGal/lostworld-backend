package pl.lostworld.lostworldbackend.user.additionalResources.photo;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/photos")
public class PhotoController {

    private PhotoService photoService;

    public PhotoController(PhotoService photoService) {
        this.photoService = photoService;
    }

    @GetMapping("/upload")
    public Photo uploadPhoto() {
        return new Photo();
    }

    @PostMapping("/upload")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public Photo uploadPhoto(@Valid @RequestBody Photo photo) {
        System.out.println("Saving...");
        return photoService.save(photo);
    }

    @GetMapping("/multipleUpload")
    public Photo uploadMultiplePhotos() {
        return new Photo();
    }

    @PostMapping("/multipleUpload")
    public List<Photo> uploadMultiplePhotos(@RequestBody List<Photo> photos) {
        return photos.stream().map(this::uploadPhoto).collect(Collectors.toList());
    }

    @GetMapping("/get/{photoId}")
    public Photo getPhoto(@PathVariable Long photoId) {
        return photoService.checkById(photoId);
    }

}
