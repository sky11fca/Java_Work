package labWork.DAOs;

import labWork.DBConnection;
import labWork.models.Continents;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ContinentDAO
{
    public void create(String name) throws SQLException
    {
        Connection connection = DBConnection.getInstance().getConnection();
        String querry = "INSERT INTO continents (name) VALUES (?)";

        try(PreparedStatement preparedStatement = connection.prepareStatement(querry))
        {
               preparedStatement.setString(1, name);
               preparedStatement.executeUpdate();
        }
    }

    public Continents findByID(int id) throws SQLException
    {
        Connection connection = DBConnection.getInstance().getConnection();
        String querry = "SELECT * FROM continents WHERE id = ?";

        try(PreparedStatement preparedStatement = connection.prepareStatement(querry))
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
        Connection connection = DBConnection.getInstance().getConnection();
        String querry = "SELECT * FROM continents WHERE name = ?";

        try(PreparedStatement preparedStatement = connection.prepareStatement(querry))
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
        Connection connection = DBConnection.getInstance().getConnection();
        String querry = "SELECT * FROM continents";

        try(Statement statement = connection.createStatement(); ResultSet resultSet = statement.executeQuery(querry))
        {
            while(resultSet.next())
            {
                continents.add(new Continents(resultSet.getInt("id"), resultSet.getString("name")));
            }
        }

        return continents;
    }
}
