package homework;

import homework.Client.CityClient;
import homework.Entities.City;
import homework.Entities.Continent;
import homework.Entities.Country;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.Arrays;

@SpringBootApplication
public class Main{

    public static void main(String[] args) {
        SpringApplication.run(Main.class, args);
    }

    @Bean
    public CommandLineRunner run(CityClient cityClient){
        return args -> {
            try {

                Country country = new Country();
                country.setId(1L); // Assuming country with ID 1 exists

                City newCity = new City();
                newCity.setName("Berlin");
                newCity.setCapital(true);
                newCity.setLatitude(52.5200);
                newCity.setLongitude(13.4050);
                newCity.setCountry(country);

                // 2. Add the city
                City createdCity = cityClient.addCity(newCity);
                System.out.println("Added city: " + createdCity);

                // 3. Get all cities
                City[] cities = cityClient.getAllCities();
                System.out.println("All cities: " + Arrays.toString(cities));

                // 4. Update city name (if we got a valid ID)
                if (createdCity != null && createdCity.getId() != 0L) {
                    cityClient.updateCityName(createdCity.getId(), "Berlin Updated");
                    System.out.println("City updated");
                }

                // 5. Get cities again to see changes
                cities = cityClient.getAllCities();
                System.out.println("After update: " + Arrays.toString(cities));

            } catch (Exception e) {
                System.err.println("Error executing CommandLineRunner: " + e.getMessage());
                e.printStackTrace();
            }
        };
    }
}
