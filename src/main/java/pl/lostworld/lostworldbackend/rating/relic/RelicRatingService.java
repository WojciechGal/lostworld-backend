package pl.lostworld.lostworldbackend.rating.relic;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class RelicRatingService {

    private RelicRatingRepository relicRatingRepository;

    public RelicRatingService(RelicRatingRepository relicRatingRepository) {
        this.relicRatingRepository = relicRatingRepository;
    }

    public List<RelicRating> checkAll() {
        return relicRatingRepository.findAll();
    }
}
