package pl.lostworld.lostworldbackend.rating.city;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CityRatingRepository extends JpaRepository<CityRating, Long> {
}
