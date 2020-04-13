package pl.lostworld.lostworldbackend.continent;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/continents")
public class ContinentController {

    private ContinentService continentService;

    public ContinentController(ContinentService continentService) {
        this.continentService = continentService;
    }

    @GetMapping("/checkAll")
    public List<Continent> checkAllContinents() {
        return continentService.checkAll();
    }

    @GetMapping("/add")
    public Continent addContinent() {
        return new Continent();
    }

    @PostMapping("/add")
    @ResponseStatus(HttpStatus.CREATED)
    public Continent addContinent(@Valid @RequestBody Continent continent) {
        return continentService.addContinent(continent);
    }

    /////////////DEPRECATED/////////////
//    @GetMapping("/add")
//    public String addContinent(Model model) {
//        model.addAttribute("continent", new Continent());
//        return "adminTemplates/addContinent";
//    }
//
//    @PostMapping("/add")
//    public String addContinent(@Valid Continent continent, BindingResult result) {
//        if (result.hasErrors()) {
//            return "adminTemplates/addContinent";
//        }
//        continentService.addContinent(continent);
//        return "redirect:/continents/checkAll";
//    }

}
