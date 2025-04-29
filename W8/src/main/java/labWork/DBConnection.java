package labWork;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnection
{
    private static DBConnection instance;
    private Connection connection;

    private static final String URL = "jdbc:mariadb://localhost:3306/worldCities";
    private static final String USER = "bobby";
    private static final String PASSWORD = "bobbydb3002";

    public DBConnection() {
        try
        {
            this.connection = DriverManager.getConnection(URL, USER, PASSWORD);
        }
        catch(SQLException e)
        {
            System.err.println("DB connection failed: "+ e.getMessage());
        }
    }

    public static DBConnection getInstance()
    {
        if(instance == null)
        {
            instance = new DBConnection();
        }
        return instance;
    }

    public Connection getConnection() {
        return connection;
    }

    public void closeConnection()
    {
        try
        {
            if(connection != null && !connection.isClosed())
            {
                connection.close();
            }
        } catch (SQLException e) {
            System.err.println("ERROR: closing connection: "+e.getMessage());
        }
    }
}
