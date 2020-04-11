package pl.lostworld.lostworldbackend.rating.country;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class CountryRatingService {

    private CountryRatingRepository countryRatingRepository;

    public CountryRatingService(CountryRatingRepository countryRatingRepository) {
        this.countryRatingRepository = countryRatingRepository;
    }
}
