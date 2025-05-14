package homework;

import homework.persistence.PersistenceManager;
import homework.model.City;
import homework.model.Continents;
import homework.model.Country;
import homework.persistence.CityRepo;
import homework.persistence.ContinentRepo;
import homework.persistence.CountryRepo;
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;

import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Main
{
    public static void main(String[] args)
    {
        try {
            ContinentRepo continentRepo = new ContinentRepo();
            CountryRepo countryRepo = new CountryRepo();
            CityRepo cityRepo = new CityRepo();

            Continents europe = new Continents("Europe");
            continentRepo.create(europe);

            Country romania = new Country("Romania", "ROU", europe);
            countryRepo.create(romania);

            City bucharest = new City("Bucharest", romania, true, 44.4268, 26.1025);
            cityRepo.create(bucharest);

            City foundCity = cityRepo.findById(bucharest.getId());
            System.out.println(foundCity);

            List<City> cities = cityRepo.findByName("uch");
            System.out.println("Cities with pattern 'uch':");
            cities.forEach(System.out::println);
        }

        finally {
            PersistenceManager.closeEntityManagerFactory();
        }
    }
}
