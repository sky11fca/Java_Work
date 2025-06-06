package homework.Repos;

import homework.Entities.Country;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CountryRepository extends JpaRepository<Country, Long> {
    List<Country> findByContinentId(Long continentId);
}
