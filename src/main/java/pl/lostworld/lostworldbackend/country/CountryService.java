package pl.lostworld.lostworldbackend.country;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class CountryService {

    private CountryRepository countryRepository;

    public CountryService(CountryRepository countryRepository) {
        this.countryRepository = countryRepository;
    }

    public List<Country> checkAll() {
        return countryRepository.findAll();
    }

    public Country save(Country country) {
        return countryRepository.save(country);
    }

    public Country checkById(Long id) {
        return countryRepository.findById(id).orElse(null);
    }

    public boolean checkIfExists(Long id) {
        return countryRepository.existsById(id);
    }
}
