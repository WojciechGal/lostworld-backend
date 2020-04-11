package pl.lostworld.lostworldbackend.relic;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/relics")
public class RelicController {

    private RelicService relicService;

    public RelicController(RelicService relicService) {
        this.relicService = relicService;
    }

    @GetMapping("/checkAll")
    public List<Relic> checkAllRelics() {
        return relicService.checkAll();
    }
}
