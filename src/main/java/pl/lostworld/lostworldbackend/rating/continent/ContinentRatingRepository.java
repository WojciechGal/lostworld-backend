package pl.lostworld.lostworldbackend.rating.continent;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ContinentRatingRepository extends JpaRepository<ContinentRating, Long> {
}
