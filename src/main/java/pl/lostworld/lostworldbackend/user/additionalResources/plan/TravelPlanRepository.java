package pl.lostworld.lostworldbackend.user.additionalResources.plan;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TravelPlanRepository extends JpaRepository<TravelPlan, Long> {

    List<TravelPlan> findAllByUserId(Long userId);
}
