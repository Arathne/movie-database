package database;

import java.sql.ResultSet;
import java.sql.SQLException;

public class RentalQuery extends Query 
{
	/* list all available payment methods */
	public static void listAllPayment( Database database )
	{
		ResultSet table = simpleQuery( database, "SELECT * FROM MM_PAY_TYPE" );
		
		if( table != null )
		{
			try {
			 	System.out.println();
				while ( table.next() )
				{
					int id = table.getInt( "payment_methods_id" );
					String payment = table.getString( "payment_methods" );
					String output = String.format( "%-3s %-15s", id, payment );
					System.out.println( "\t" + output );
				}
			} catch (SQLException e) {
				System.out.println("no payment methods found");
			}
		}
	}
	
	/* list all available payment methods */
	public static void listRentals( Database database, int memberId )
	{
		String query = String.format("SELECT rental_id, movie_name( movie_id ) AS movie_id, checkout_date, checkin_date FROM mm_rental WHERE member_id = %s", memberId );
		ResultSet table = simpleQuery( database, query );
		
		if( table != null )
		{
			try {
			 	System.out.println();
				while ( table.next() )
				{
					int id = table.getInt( "rental_id" );
					String movie = table.getString( "movie_id" );
					String checkOut = table.getString( "checkout_date" );
					String checkIn = table.getString( "checkin_date" );
					String output = String.format( "%-5s %-41s %-25s %-25s", id, movie, checkOut, checkIn );
					System.out.println( "\t" + output );
				}
			} catch (SQLException e) {
				System.out.println("nothing rented");
			}
		}
	}
	
	public static void rentMovie( Database database, int memberId, int movieId, int paymentId )
	{
		String query = String.format( "INSERT INTO mm_rental ( member_id, movie_id, payment_methods_id ) VALUES( %s, %s, %s )", memberId, movieId, paymentId );
		simpleQuery( database, query );
	}
	
	public static void returnMovie( Database database, int memberId, int rentalId )
	{
		String query = String.format( "DELETE FROM mm_rental WHERE member_id = %s and rental_id = %s", memberId, rentalId );
		simpleQuery( database, query );
	}
	
}
