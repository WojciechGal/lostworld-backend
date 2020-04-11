package pl.lostworld.lostworldbackend.rating.city;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class CityRatingService {

    private CityRatingRepository cityRatingRepository;

    public CityRatingService(CityRatingRepository cityRatingRepository) {
        this.cityRatingRepository = cityRatingRepository;
    }

    public List<CityRating> checkAll() {
        return cityRatingRepository.findAll();
    }
}
