package pl.lostworld.lostworldbackend.user.additionalResources.album;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

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

    //todo podobnie jak w innych klasach tutaj trzeba dodac metode z authentication principal z reportController
    //todo PRAWDOPODOBNIE wymagana będzie modyfikacja w związku z wysyłaniem null'owych obiektów przez JsonIgnore
    //todo lub odpowiednie ustawienie operacji kaskadowych
    //todo do decyzji i dyskusji z frontem

    @PostMapping("/add")
    @ResponseStatus(HttpStatus.CREATED)
    public Album addAlbum (@Valid @RequestBody Album album) {
        return albumService.save(album);
    }
}
