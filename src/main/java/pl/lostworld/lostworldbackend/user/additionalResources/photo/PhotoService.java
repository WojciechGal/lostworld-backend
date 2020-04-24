package pl.lostworld.lostworldbackend.user.additionalResources.photo;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class PhotoService {

    private PhotoRepository photoRepository;

    public PhotoService(PhotoRepository photoRepository) {
        this.photoRepository = photoRepository;
    }

    public List<Photo> checkAll() {
        return photoRepository.findAll();
    }

    public Photo checkById(Long id) {
        return photoRepository.findById(id).orElse(null);
    }

    public Photo save(Photo photo) {
        return photoRepository.save(photo);
    }

}
