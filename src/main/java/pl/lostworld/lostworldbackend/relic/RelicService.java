package pl.lostworld.lostworldbackend.relic;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class RelicService {

    private RelicRepository relicRepository;

    public RelicService(RelicRepository relicRepository) {
        this.relicRepository = relicRepository;
    }

    public List<Relic> checkAll() {
        return relicRepository.findAll();
    }

    public boolean checkIfExists(Long id) {
        return relicRepository.existsById(id);
    }
}
