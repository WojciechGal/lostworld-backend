package pl.lostworld.lostworldbackend.rating.country;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CountryRatingRepository extends JpaRepository<CountryRating, Long> {
}
