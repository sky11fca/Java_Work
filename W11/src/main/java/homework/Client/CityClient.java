package homework.Client;

import homework.Entities.City;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Component
public class CityClient {

    private final RestTemplate restTemplate;
    private final String baseUrl = "http://localhost:8080/api/cities";


    public CityClient(RestTemplateBuilder restTemplateBuilder) {
        this.restTemplate = restTemplateBuilder.build();
    }

    public City[] getAllCities() {
        return restTemplate.getForObject(baseUrl, City[].class);
    }

    public City addCity(City city) {
        return restTemplate.postForObject(baseUrl, city, City.class);
    }

    public void updateCityName(Long id, String newName){
        restTemplate.put(baseUrl + "/" + id + "?newName=" + newName, null);
    }
    public void deleteCity(Long id){
        restTemplate.delete(baseUrl + "/" + id);
    }

}
