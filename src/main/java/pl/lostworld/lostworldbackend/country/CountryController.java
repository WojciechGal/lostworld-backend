package pl.lostworld.lostworldbackend.country;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import pl.lostworld.lostworldbackend.continent.ContinentService;

import javax.validation.Valid;
import java.util.List;

@Controller
@RequestMapping("/countries")
public class CountryController {

    private ContinentService continentService;
    private CountryService countryService;

    public CountryController(ContinentService continentService, CountryService countryService) {
        this.continentService = continentService;
        this.countryService = countryService;
    }

    @GetMapping("/checkAll")
    @ResponseBody
    public List<Country> checkAllCountries() {
        return countryService.checkAll();
    }

    @GetMapping("/add")
    public String addCountry(Model model) {
        Country country = new Country();
        model.addAttribute("country", country);
        model.addAttribute("continents", continentService.checkAll());
        return "adminTemplates/addCountry";
    }

    @PostMapping("/add")
    public String addCountry(Model model, @Valid Country country, BindingResult result) {
        if (result.hasErrors()) {
            model.addAttribute("continents", continentService.checkAll());
            return "adminTemplates/addCountry";
        }
        countryService.addCountry(country);
        return "redirect:/countries/checkAll";
    }
}
