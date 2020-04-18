package pl.lostworld.lostworldbackend.user.resources.plan;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class TravelPlanService {

    private TravelPlanRepository travelPlanRepository;

    public TravelPlanService(TravelPlanRepository travelPlanRepository) {
        this.travelPlanRepository = travelPlanRepository;
    }

    public List<TravelPlan> checkAll() {
        return travelPlanRepository.findAll();
    }

    public TravelPlan add(TravelPlan travelPlan) {
        return travelPlanRepository.save(travelPlan);
    }
}
