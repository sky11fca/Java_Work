package labWork.Repos;

import labWork.Entities.City;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CityRepository extends JpaRepository<City, Long> {
    List<City> findByCountryId(Long countryId);
    List<City> findByCapitalTrueAndCountryId(Long countryId);
}
