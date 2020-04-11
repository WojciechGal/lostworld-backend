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

    public Continent addContinent(Continent continent) {
        return continentRepository.save(continent);
    }

}
