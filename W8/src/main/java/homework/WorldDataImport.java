package homework;

import homework.DAOs.CityDAO;
import homework.DAOs.ContinentDAO;
import homework.DAOs.CountryDAO;
import homework.models.City;
import homework.models.Continents;
import homework.models.Country;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;

import java.io.FileReader;
import java.io.Reader;

public class WorldDataImport
{
    public static void importWorldCapitals(String csvFilePath) throws Exception
    {
        CountryDAO countryDAO = new CountryDAO();
        CityDAO cityDAO = new CityDAO();

        try (Reader reader = new FileReader(csvFilePath); CSVParser parser = new CSVParser(reader, CSVFormat.DEFAULT.withFirstRecordAsHeader().withIgnoreHeaderCase().withTrim()))
        {
            for(CSVRecord record : parser)
            {
                String countryName = record.get("Country");
                String capitalName = record.get("Capital");

                double latitude = Double.parseDouble(record.get("Latitude"));
                double longitude = Double.parseDouble(record.get("Longitude"));

                String continentName = record.get("Continent");

                Continents continent = new ContinentDAO().findByName(continentName);

                if(continent==null)
                {
                    new ContinentDAO().create(continentName);
                    continent = new ContinentDAO().findByName(continentName);
                }

                Country country = new CountryDAO().findByName(countryName);

                if(country==null)
                {
                    String countryCode = countryName.substring(0, Math.min(3, countryName.length())).toUpperCase();
                    countryDAO.create(countryName, countryCode, continent.getId());
                    country = countryDAO.findByName(countryName);
                }

                City capital = new City(0, capitalName, country, true, latitude, longitude);
                cityDAO.create(capital);
            }
        }
    }
}
