package pl.lostworld.lostworldbackend.rating.continent;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class ContinentRatingService {

    private ContinentRatingRepository continentRatingRepository;

    public ContinentRatingService(ContinentRatingRepository continentRatingRepository) {
        this.continentRatingRepository = continentRatingRepository;
    }
}
