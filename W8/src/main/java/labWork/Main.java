package labWork;

import labWork.DAOs.ContinentDAO;
import labWork.DAOs.CountryDAO;

import java.sql.SQLException;

public class Main
{
    public static void main(String[] args)
    {
        //TODO: Add methods to continent and country DAO to drop tables when running the program
        try
        {
            ContinentDAO continentDAO = new ContinentDAO();
            CountryDAO countryDAO = new CountryDAO();

            continentDAO.create("Europe");
            continentDAO.create("America");
            continentDAO.create("Africa");
            continentDAO.create("Asia");
            continentDAO.create("Oceania");

            countryDAO.create("Romania", "ROU", 1);
            countryDAO.create("Germany", "DEU", 1);
            countryDAO.create("United States", "USA", 2);
            countryDAO.create("Canada", "CDN", 2);
            countryDAO.create("Greenland", "GRN", 2);
            countryDAO.create("Egypt", "EGY", 3);
            countryDAO.create("Japan", "JPN", 4);
            countryDAO.create("Australia", "AU", 5);

            System.out.println("ALL CONTINENTS:");
            continentDAO.findAll().forEach(System.out::println);

            System.out.println("\nFind Eurpoe by name:");
            System.out.println(continentDAO.findByName("Europe"));


            System.out.println("\nEuropean Countries:");
            countryDAO.findAll().stream().filter(country -> country.getContinent().getName().equals("Europe")).forEach(System.out::println);


            System.out.println("\nFind Romania by name:");
            System.out.println(countryDAO.findByName("Romania"));
        }
        catch(SQLException e)
        {
            System.err.println("DB error: " + e.getMessage());
        }
        finally
        {
            DBConnection.getInstance().closeConnection();
        }
    }

}
