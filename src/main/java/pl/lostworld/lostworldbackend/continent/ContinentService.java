package pl.lostworld.lostworldbackend.continent;

import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ContinentService {

    private ContinentRepository continentRepository;

    public ContinentService(ContinentRepository continentRepository) {
        this.continentRepository = continentRepository;
    }

    public List<Continent> checkall() {
        return continentRepository.findAll();
    }

    public Continent addContinent(Continent continent) {
        return continentRepository.save(continent);
    }

}
