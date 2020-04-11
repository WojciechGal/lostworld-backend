package pl.lostworld.lostworldbackend.rating.relic;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RelicRatingRepository extends JpaRepository<RelicRating, Long> {
}
