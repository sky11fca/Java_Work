package homework.Controller;

import homework.Entities.City;
import homework.Services.CityService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/cities")
@Tag(name="Cities API", description = "Operations for managing cities")
public class CityController {

    private final CityService cityService;

    public CityController(CityService cityService) {
        this.cityService = cityService;
    }

    @GetMapping
    @Operation(summary = "Get all cities")
    public ResponseEntity<List<City>> getAllCities() {
        return ResponseEntity.ok(cityService.getAllCities());
    }

    @PostMapping
    @Operation(summary = "Add a city")
    public ResponseEntity<City> addCity(@RequestBody City city){
        return ResponseEntity.ok(cityService.saveCity(city));
    }
    @PutMapping("/{id}")
    @Operation(summary = "Updates city by ID")
    public ResponseEntity<City> updateCityName(
            @PathVariable Long id,
            @RequestBody Map<String, String> request){
        return ResponseEntity.ok(cityService.updateCityByName(id, request.get("newName")));
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Deletes city by ID")
    public ResponseEntity<City> deleteCity(@PathVariable Long id){
        cityService.deleteCity(id);
        return ResponseEntity.noContent().build();
    }
}
