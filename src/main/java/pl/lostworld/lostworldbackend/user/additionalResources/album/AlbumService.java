package pl.lostworld.lostworldbackend.user.additionalResources.album;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pl.lostworld.lostworldbackend.user.User;
import pl.lostworld.lostworldbackend.utils.HibernateUtils;

import javax.validation.ConstraintViolation;
import javax.validation.Validator;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service
@Transactional
public class AlbumService {

    private AlbumRepository albumRepository;

    private Validator validator;

    public AlbumService(AlbumRepository albumRepository, Validator validator) {
        this.albumRepository = albumRepository;
        this.validator = validator;
    }

    public List<Album> checkAll() {
        return albumRepository.findAll();
    }

    public Optional<Album> checkById(Long id) {
        return albumRepository.findById(id);
    }

    public List<Album> checkAllByUserId(Long id) {
        return albumRepository.findAllByUserId(id);
    }

    public Album save(Album album) {
        return albumRepository.save(album);
    }

    public Album setUserAndSave(Album album, User user) {
        album.setUser(user);
        return albumRepository.save(album);
    }

    public Set<ConstraintViolation<Album>> setUserAndValidate(Album album, User user) {
        album.setUser(user);
        return validator.validate(album);
    }

    public Optional<Album> deleteById(Long id) {
        Optional<Album> album = albumRepository.findById(id);
        if (album.isPresent()) {
            //dociągnięcie danych jest wymagane przed usunięciem obiektu z DB
            HibernateUtils.initializeSequenceOfTerritoryEntities(album);
            albumRepository.deleteById(id);
            return album;
        } else {
            return album;
        }
    }

    public boolean checkIfExists(Long id) {
        return albumRepository.existsById(id);
    }
}
