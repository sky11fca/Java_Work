package homework;

import homework.DAOs.CityDAO;
import homework.DAOs.ContinentDAO;
import homework.DAOs.CountryDAO;
import homework.models.City;
import homework.models.Continents;
import homework.models.Country;

import java.sql.SQLException;
import java.util.List;
import java.util.stream.Collectors;

public class Main
{
    public static void main(String[] args)
    {
        try
        {
            WorldDataImport.importWorldCapitals("world_capitals.csv");
            CountryDAO countryDAO = new CountryDAO();
            CityDAO cityDAO = new CityDAO();

            Country romania = countryDAO.findByName("Romania");
            Country japan = countryDAO.findByName("Japan");

            City bucharest = cityDAO.findByCountry(romania).stream()
                    .filter(City::isCapital)
                    .findFirst()
                    .orElse(null);
            City tokyo = cityDAO.findByCountry(japan).stream()
                    .filter(City::isCapital)
                    .findFirst()
                    .orElse(null);

            if(bucharest != null && tokyo != null)
            {
                System.out.println(ComputeDistance.getFormattedDistance(bucharest, tokyo));
            }

            Continents europe = new ContinentDAO().findByName("Europe");
            List<Country> europeanCountries = new CountryDAO().findAll().stream()
                    .filter(country -> country.getContinent().getId()==europe.getId())
                    .toList();

            System.out.println("\nEUROPEAN CAPITALS:");
            for(Country country : europeanCountries)
            {
                cityDAO.findByCountry(country).stream()
                        .filter(City::isCapital)
                        .forEach(System.out::println);
            }
        }
        catch(Exception e)
        {
            System.err.println("DB error: " + e.getMessage());
        }
        finally
        {
            DBConnection.closePool();
        }
    }

}
