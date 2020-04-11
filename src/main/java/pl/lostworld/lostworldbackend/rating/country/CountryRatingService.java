package pl.lostworld.lostworldbackend.rating.country;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class CountryRatingService {

    private CountryRatingRepository countryRatingRepository;

    public CountryRatingService(CountryRatingRepository countryRatingRepository) {
        this.countryRatingRepository = countryRatingRepository;
    }

    public List<CountryRating> checkAll() {
        return countryRatingRepository.findAll();
    }
}
