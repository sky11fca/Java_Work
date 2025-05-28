package homework.Services;

import homework.Entities.City;
import homework.Repos.CityRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CityService {
    private final CityRepository cityRepository;

    public CityService(CityRepository cityRepository) {
        this.cityRepository = cityRepository;
    }

    public List<City> getAllCities() {
        return cityRepository.findAll();
    }


    public City saveCity(City city) {
        return cityRepository.save(city);
    }

    public City updateCityByName(Long id, String name) {
       /* return cityRepository.findById(id)
                .map(city -> {
                    city.setName(name);
                    return cityRepository.save(city);
                })
                .orElseThrow(() -> new RuntimeException("City not found"));*/

        City city = cityRepository.findById(id).get();
        city.setName(name);

        return cityRepository.save(city);
    }

    public void deleteCity(Long id) {
        cityRepository.deleteById(id);
    }
}
