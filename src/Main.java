import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;

import java.sql.Connection;
import java.sql.DriverManager;
//import java.sql.SQLException;

public class Main {
	public static void main(String[] args)
	{
		Connection connection;
		PreparedStatement ps;
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			connection = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","gremlin","and1loser2");
			System.out.println("CONNECTION SUCCESS");
			
			ps = connection.prepareStatement("SELECT * FROM student");
			ResultSet rs = ps.executeQuery();
			
			while ( rs.next() )
			{
				System.out.println( rs.getString( "student_name" ) );
			}
			
			
		} catch( Exception e ) {
			
			System.out.println( e );
			System.out.println("CONNECTION FAILED");
		}
	}
}
