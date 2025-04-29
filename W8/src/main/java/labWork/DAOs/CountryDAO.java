package labWork.DAOs;

import labWork.DBConnection;
import labWork.models.Continents;
import labWork.models.Country;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CountryDAO
{
    public void create(String name, String code, int continentId) throws SQLException
    {
        Connection connection = DBConnection.getInstance().getConnection();
        String sql = "INSERT INTO countries (name, code, continent_id) VALUES (?, ?, ?)";

        try(PreparedStatement preparedStatement = connection.prepareStatement(sql))
        {
            preparedStatement.setString(1, name);
            preparedStatement.setString(2, code);
            preparedStatement.setInt(3, continentId);
            preparedStatement.executeUpdate();
        }
    }


    public Country findById(int id) throws SQLException
    {
        Connection connection = DBConnection.getInstance().getConnection();
        String sql = "SELECT c.id, c.name, c.code, cont.id as continent_id, cont.name as continent_name FROM countries c JOIN continents cont ON c.continent_id = cont.id WHERE c.id = ?";
        try(PreparedStatement preparedStatement = connection.prepareStatement(sql))
        {
            preparedStatement.setInt(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            if(resultSet.next())
            {
                Continents continent = new Continents(resultSet.getInt("continent_id"), resultSet.getString("continent_name"));
                return new Country(resultSet.getInt("id"), resultSet.getString("name"), resultSet.getString("code"), continent);
            }
        }

        return null;
    }

    public Country findByName(String name) throws SQLException {
        Connection connection = DBConnection.getInstance().getConnection();
        String sql = "SELECT c.id, c.name, c.code, cont.id AS continent_id, cont.name as continent_name FROM countries c JOIN continents cont ON c.continent_id = cont.id WHERE c.name = ?";
        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setString(1, name);
            ResultSet resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                Continents continent = new Continents(resultSet.getInt("continent_id"), resultSet.getString("continent_name"));
                return new Country(resultSet.getInt("id"), resultSet.getString("name"), resultSet.getString("code"), continent);
            }
        }
        return null;
    }


    public List<Country> findAll() throws SQLException
    {
        List<Country> countries = new ArrayList<>();
        Connection connection = DBConnection.getInstance().getConnection();
        String sql = "SELECT c.id, c.name, c.code, cont.id as continent_id, cont.name as continent_name FROM countries c JOIN continents cont ON c.continent_id=cont.id";

        try(Statement statement = connection.createStatement(); ResultSet resultSet = statement.executeQuery(sql))
        {
            while(resultSet.next())
            {
                Continents continent = new Continents(resultSet.getInt("continent_id"), resultSet.getString("continent_name"));
                countries.add(new Country(resultSet.getInt("id"), resultSet.getString("name"), resultSet.getString("code"), continent));
            }
        }
        return countries;
    }
}
