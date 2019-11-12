package database;
import java.sql.Connection;
import java.sql.DriverManager;

public class Database
{
	private Connection dbConnection;
	private boolean connected = false;
	
	//creates a connection to database with the username and password provided
	public void createConnection( String username, String password )
	{
		try {
			dbConnection = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe", username, password);
			connected = true;
		} 
		catch (Exception e) {
			System.err.println(e.getMessage());
			connected = false;
		}
	}
	
	//returns true if it is currently connected to a database
	public boolean isConnected()
	{
		return connected;
	}
	
	//returns database connection
	public Connection getConnection()
	{
		return dbConnection;
	}
}
