package pl.lostworld.lostworldbackend.user.additionalResources.album;

import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;
import pl.lostworld.lostworldbackend.user.CurrentUser;
import pl.lostworld.lostworldbackend.utils.ResponseUtils;
import pl.lostworld.lostworldbackend.utils.ValidationUtils;

import javax.validation.ConstraintViolation;
import javax.validation.Valid;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@RestController
@RequestMapping("/albums")
public class AlbumController {

    private AlbumService albumService;

    public AlbumController(AlbumService albumService) {
        this.albumService = albumService;
    }

    @GetMapping("/checkAll")
    public List<Album> checkAllAlbums() {
        return albumService.checkAll();
    }

    @GetMapping("/checkAllForLoggedUser")
    public ResponseEntity<?> checkAllAlbumsForLoggedUser(@AuthenticationPrincipal CurrentUser currentUser) {
        return ResponseUtils.designOkResponse(albumService.checkAllByUserId(currentUser.getId()));
    }

    @GetMapping("/add")
    public ResponseEntity<?> addAlbum() {
        return ResponseUtils.designOkResponse(new Album());
    }

    @PostMapping("/addForLoggedUser")
    public ResponseEntity<?> addAlbumForLoggedUser(@RequestBody Album album, @AuthenticationPrincipal CurrentUser currentUser) {
        Set<ConstraintViolation<Album>> violations = albumService.setUserAndValidate(album, currentUser.getActualUser());
        if (violations.isEmpty()) {
            return ResponseUtils.designCreatedResponse(albumService.setUserAndSave(album, currentUser.getActualUser()));
        } else {
            return ResponseUtils.designBadRequestResponse(ValidationUtils.mapViolationsForResponse(violations));
        }
    }

    @PutMapping("/update")
    public ResponseEntity<?> updateAlbum (@Valid @RequestBody Album album) {
        return ResponseUtils.designOkResponse(albumService.save(album));
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> deleteAlbum(@PathVariable Long id) {
        Optional<Album> album = albumService.deleteById(id);
        if (album.isPresent()) {
            return ResponseUtils.designOkResponse(album.get());
        } else {
            return ResponseUtils.designBadRequestSingletonResponse("No such element");
        }
    }
}
