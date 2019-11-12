package database;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;

public class Query 
{
	//returns all members
	public static void printAllMembers( Database database )
	{
		PreparedStatement statement;
		
		try {
			Connection connection = database.getConnection();
			statement = connection.prepareStatement( "select * from mm_member" ); 
			ResultSet results = statement.executeQuery();
			
			while ( results.next() ) {
				String first = results.getString("first");
				String last = results.getString("last");
				int id = results.getInt("member_id");
				System.out.println( id + " " + first + " " + last);
			}
			
			results.close();
		}
		catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	
}
