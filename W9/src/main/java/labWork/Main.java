package labWork;

import labWork.model.City;
import labWork.model.Continents;
import labWork.model.Country;
import labWork.persistence.CityRepo;
import labWork.persistence.ContinentRepo;
import labWork.persistence.CountryRepo;
import labWork.persistence.PersistenceManager;

public class Main
{
    public static void main(String[] args)
    {
        try
        {
            ContinentRepo continentRepo = new ContinentRepo();
            CountryRepo countryRepo = new CountryRepo();
            CityRepo cityRepo = new CityRepo();

            Continents europe = new Continents("Europe");
            continentRepo.create(europe);

            Country romania = new Country("Romania", "ROU", europe);
            countryRepo.create(romania);

            City bucharest = new City("Bucharest", romania, true, 44.4268, 26.1025);
            cityRepo.create(bucharest);


            System.out.println("Find continent by name: ");
            continentRepo.findByName("Eur").forEach(System.out::println);

            System.out.println("\nFind country by name: ");
            countryRepo.findByName("Roma").forEach(System.out::println);

            System.out.println("\nFind city by name: ");
            cityRepo.findByName("Buch").forEach(System.out::println);

            System.out.println("\nAll Capitals:");
            cityRepo.findCapitals().forEach(System.out::println);
        }
        finally
        {
            PersistenceManager.closeEntityManagerFactory();
        }

    }

}
