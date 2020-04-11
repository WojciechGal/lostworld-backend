package pl.lostworld.lostworldbackend.rating.continent;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class ContinentRatingService {

    private ContinentRatingRepository continentRatingRepository;

    public ContinentRatingService(ContinentRatingRepository continentRatingRepository) {
        this.continentRatingRepository = continentRatingRepository;
    }

    public List<ContinentRating> checkAll() {
        return continentRatingRepository.findAll();
    }
}
