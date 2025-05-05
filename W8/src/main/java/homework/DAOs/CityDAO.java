package homework.DAOs;

import homework.DBConnection;
import homework.models.City;
import homework.models.Continents;
import homework.models.Country;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CityDAO
{
    public void create (City city) throws SQLException
    {
        String sql = "INSERT INTO city (name, country_id, capital, latitude, longidude) " + "Values(?, ?, ?, ?, ?)";

        try (Connection conn = DBConnection.getConnection(); PreparedStatement ps = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS))
        {
            ps.setString(1, city.getName());
            ps.setInt(2, city.getCountry().getId());
            ps.setBoolean(3, city.isCapital());
            ps.setDouble(4, city.getLatitude());
            ps.setDouble(5, city.getLongitude());
            ps.executeUpdate();

            try(ResultSet rs = ps.getGeneratedKeys())
            {
                if(rs.next())
                {
                    city.setId(rs.getInt(1));
                }
            }
        }
    }


    public City findById(int id) throws SQLException
    {
        String sql = "SELECT c.*, co.id as country_id, co.name as country_name, " +
                "co.code as country_code, cont.id as continent_id, cont.name as continent_name " +
                "FROM city c " +
                "JOIN countries co ON c.country_id = co.id " +
                "JOIN continents cont ON co.continent_id = cont.id " +
                "WHERE c.id = ?";
        try (Connection conn = DBConnection.getConnection(); PreparedStatement ps = conn.prepareStatement(sql))
        {
            ps.setInt(1, id);
            try(ResultSet rs = ps.executeQuery())
            {
                if(rs.next())
                {
                    return mapCity(rs);
                }
            }
        }

        return null;
    }

    public List<City> findByCountry(Country country) throws SQLException
    {
        List<City> cities = new ArrayList<>();

        String sql = "SELECT c.*, co.id as country_id, co.name as country_name, " +
                "co.code as country_code, cont.id as continent_id, cont.name as continent_name " +
                "FROM city c " +
                "JOIN countries co ON c.country_id = co.id " +
                "JOIN continents cont ON co.continent_id = cont.id " +
                "WHERE c.country_id = ?";

        try (Connection conn = DBConnection.getConnection(); PreparedStatement ps = conn.prepareStatement(sql))
        {
            ps.setInt(1, country.getId());
            try(ResultSet rs = ps.executeQuery())
            {
                while(rs.next())
                {
                    cities.add(mapCity(rs));
                }
            }
        }

        return cities;
    }

    private City mapCity(ResultSet rs) throws SQLException
    {
        Continents continents = new Continents(
                rs.getInt("continent_id"),
                rs.getString("continent_name")
        );

        Country country = new Country(
                rs.getInt("country_id"),
                rs.getString("country_name"),
                rs.getString("country_code"),
                continents
        );

        return new City(
                rs.getInt("id"),
                rs.getString("name"),
                country,
                rs.getBoolean("capital"),
                rs.getDouble("latitude"),
                rs.getDouble("longidude")
        );
    }
}
