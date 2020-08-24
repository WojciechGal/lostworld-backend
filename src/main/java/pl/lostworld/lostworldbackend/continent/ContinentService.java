package pl.lostworld.lostworldbackend.continent;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class ContinentService {

    private ContinentRepository continentRepository;

    public ContinentService(ContinentRepository continentRepository) {
        this.continentRepository = continentRepository;
    }

    public List<Continent> checkAll() {
        return continentRepository.findAll();
    }

    public Continent save(Continent continent) {
        return continentRepository.save(continent);
    }

    public Continent checkById(Long id) {
        return continentRepository.findById(id).orElse(null);
    }

    public boolean checkIfExists(Long id) {
        return continentRepository.existsById(id);
    }
}
