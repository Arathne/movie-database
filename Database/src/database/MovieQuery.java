package database;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;

import types.Movie;

public class MovieQuery extends Query {
	
	/* list all movies */
	public static void listMovies( Database database )
	{
		ResultSet table = simpleQuery( database, "SELECT movie_id, movie_title, category_name(movie_cat_id) AS \"movie_cat_id\", movie_value, movie_qty FROM MM_MOVIE" ); //get table info
		
		if( table != null )
		{
			try {
			 	System.out.println();
				while ( table.next() ) //print movie info
				{
					int id = table.getInt( "movie_id" );
					String title = table.getString( "movie_title" );
					float value = table.getFloat( "movie_value" );
					int quantity = table.getInt( "movie_qty" );
					String category = table.getString( "movie_cat_id" );
					Movie current_movie = new Movie( id, title, category, value, quantity );
					System.out.println( "\t"+ current_movie );
				}
			} catch (SQLException e) {
				System.out.println("no movies found");
			}
		}
	}
	
	/* list all categories */
	public static void listCategories( Database database )
	{
		ResultSet table = simpleQuery( database, "SELECT * FROM MM_MOVIE_TYPE" );
		
		if( table != null )
		{
			try {
			 	System.out.println();
				while ( table.next() ) //print movie info
				{
					int id = table.getInt( "movie_cat_id" );
					String category = table.getString( "movie_category" );
					String output = String.format( "%-3s %-13s", id, category );
					System.out.println( "\t" + output );
				}
			} catch (SQLException e) {
				System.out.println("no movies found");
			}
		}
	}
	
	/* inserts a movie from given specifications */
	public static void insertMovie( Database database, String title, int category_id, float value, int quantity )
	{
		PreparedStatement statement;
		
		try
		{
			String query = "INSERT INTO MM_MOVIE( movie_title, movie_cat_id, movie_value, movie_qty ) VALUES( ?, ?, ?, ? )";
			Connection connection = database.getConnection();
			statement = connection.prepareStatement( query ); 
			statement.setString( 1, title );
			statement.setInt( 2, category_id );
			statement.setFloat( 3, value );
			statement.setInt( 4, quantity );
			
			statement.executeUpdate();
		} catch ( SQLException e ) {
			System.out.println( "\nFailed to insert data into table" + "\n" + e.getMessage() );
		}
	}
	
	/* removes movie from given ID */
	public static void removeMovie( Database database, int movieId )
	{
		PreparedStatement statement;
		
		try
		{
			String query = "DELETE FROM mm_movie WHERE movie_id = ?";
			Connection connection = database.getConnection();
			statement = connection.prepareStatement( query ); 
			statement.setInt( 1, movieId );
			statement.execute();
		} catch ( SQLException e ) {
			System.out.println( "\nFailed to delete data" + "\n" + e.getMessage() );
		}
	}
}
