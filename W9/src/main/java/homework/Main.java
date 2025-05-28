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
            // Initialize repositories
            ContinentRepo continentsRepo = new ContinentRepo();
            CountryRepo countryRepo = new CountryRepo();
            CityRepo cityRepo = new CityRepo();

            // Create and persist a continent
            Continents europe = new Continents("Europe");
            continentsRepo.create(europe);

            // Create and persist a country
            Country romania = new Country("Romania", "RO", europe);
            countryRepo.create(romania);

            // Create and persist a capital city
            City bucharest = new City("Bucharest", romania, true, 44.4268, 26.1025);
            cityRepo.create(bucharest);

            // Create and persist another city
            City cluj = new City("Cluj-Napoca", romania, false, 46.7712, 23.6236);
            cityRepo.create(cluj);

            // Query examples
            System.out.println("\nAll continents:");
            continentsRepo.findAll().forEach(System.out::println);

            System.out.println("\nCountries in Europe:");
            countryRepo.findByContinentName("Europe").forEach(System.out::println);

            System.out.println("\nCities in Romania:");
            cityRepo.findByCountryName("Romania").forEach(System.out::println);

            System.out.println("\nCapital cities:");
            cityRepo.findByCapital().forEach(System.out::println);
        }

        finally {
            PersistenceManager.closeEntityManagerFactory();
        }
    }
}
