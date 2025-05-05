package homework.DAOs;

import homework.DBConnection;
import homework.models.Continents;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ContinentDAO
{
    public void create(String name) throws SQLException
    {

        String querry = "INSERT INTO continents (name) VALUES (?)";

        try(Connection connection = DBConnection.getConnection(); PreparedStatement preparedStatement = connection.prepareStatement(querry))
        {
               preparedStatement.setString(1, name);
               preparedStatement.executeUpdate();
        }
    }

    public Continents findByID(int id) throws SQLException
    {

        String querry = "SELECT * FROM continents WHERE id = ?";

        try(Connection connection = DBConnection.getConnection(); PreparedStatement preparedStatement = connection.prepareStatement(querry))
        {
            preparedStatement.setInt(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();

            if(resultSet.next())
            {
                return new Continents(resultSet.getInt("id"), resultSet.getString("name"));
            }
        }

        return null;
    }


    public Continents findByName(String name) throws SQLException
    {

        String querry = "SELECT * FROM continents WHERE name = ?";

        try(Connection connection = DBConnection.getConnection(); PreparedStatement preparedStatement = connection.prepareStatement(querry))
        {
            preparedStatement.setString(1, name);
            ResultSet resultSet = preparedStatement.executeQuery();

            if(resultSet.next())
            {
                return new Continents(resultSet.getInt("id"), resultSet.getString("name"));
            }
        }

        return null;
    }

    public List<Continents> findAll() throws SQLException
    {
        List<Continents> continents = new ArrayList<>();

        String querry = "SELECT * FROM continents";

        try(Connection connection = DBConnection.getConnection(); Statement statement = connection.createStatement(); ResultSet resultSet = statement.executeQuery(querry))
        {
            while(resultSet.next())
            {
                continents.add(new Continents(resultSet.getInt("id"), resultSet.getString("name")));
            }
        }

        return continents;
    }
}
