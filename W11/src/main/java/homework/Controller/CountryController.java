package homework.Controller;


import homework.Entities.Country;
import homework.Repos.CountryRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/country")
public class CountryController {
    private final CountryRepository countryRepository;
    public CountryController(CountryRepository countryRepository) {
        this.countryRepository = countryRepository;
    }

    @GetMapping
    public ResponseEntity<List<Country>> getAllCountries() {
        List<Country> countries = countryRepository.findAll();
        return ResponseEntity.ok(countries);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Country> getCountryById(@PathVariable Long id) {
        return countryRepository.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<Country> createCountry(@RequestBody Country country) {
        Country savedCountry = countryRepository.save(country);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedCountry);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Country> updateCountry(@PathVariable Long id, @RequestBody Country countryDetails) {
        return countryRepository.findById(id)
                .map(country -> {
                    country.setName(countryDetails.getName());
                    country.setCode(countryDetails.getCode());
                    country.setContinent(countryDetails.getContinent());
                    Country updatedCountry = countryRepository.save(country);
                    return ResponseEntity.ok(updatedCountry);
                })
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteCountry(@PathVariable Long id) {
        return countryRepository.findById(id)
                .map(country -> {
                    countryRepository.delete(country);
                    return ResponseEntity.noContent().build();
                })
                .orElse(ResponseEntity.notFound().build());
    }
}
