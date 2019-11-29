package database;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Query 
{
	/* used to retrieve tables -- doesn't work if parameters are needed */
	public static ResultSet simpleQuery( Database database, String query )
	{
		PreparedStatement statement;
		ResultSet results = null;
		
		try {
			Connection connection = database.getConnection();
			statement = connection.prepareStatement( query ); 
			results = statement.executeQuery();
		}
		catch (SQLException e) {
			System.out.println( "\nSomething went wrong with sqlQuery()" );
		}
		
		return results;
	}
}







