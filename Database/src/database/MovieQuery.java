package database;
import java.sql.ResultSet;
import java.sql.SQLException;

import types.Movie;

public class MovieQuery extends Query 
{	
	/* list all movies */
	public static void listAllMovies( Database database )
	{
		ResultSet table = simpleQuery( database, "SELECT movie_id, movie_title, category_name(movie_cat_id) AS \"movie_cat_id\", movie_value, movie_qty FROM MM_MOVIE" ); //get table info
		
		if( table != null )
		{
			try {
			 	System.out.println();
				while ( table.next() )
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
	public static void listAllCategories( Database database )
	{
		ResultSet table = simpleQuery( database, "SELECT * FROM MM_MOVIE_TYPE" );
		
		if( table != null )
		{
			try {
			 	System.out.println();
				while ( table.next() )
				{
					int id = table.getInt( "movie_cat_id" );
					String category = table.getString( "movie_category" );
					String output = String.format( "%-3s %-13s", id, category );
					System.out.println( "\t" + output );
				}
			} catch (SQLException e) {
				System.out.println("no movie categories found");
			}
		}
	}
	
	/* inserts a movie from given specifications */
	public static void insertMovie( Database database, String title, int category_id, float value, int quantity )
	{
		String query = String.format( "INSERT INTO MM_MOVIE( movie_title, movie_cat_id, movie_value, movie_qty ) VALUES( '%s', %s, %s, %s )", title, category_id, value, quantity );
		simpleQuery( database, query );
	}
	
	/* removes movie with given ID */
	public static void removeMovie( Database database, int movieId )
	{
		String query = "DELETE FROM mm_movie WHERE movie_id = " + movieId;
		simpleQuery( database, query );
	}
	
	/* update movie -- title */
	public static void updateMovieTitle( Database database, int movieId, String newTitle )
	{
		String query = String.format( "UPDATE MM_MOVIE SET movie_title = '%s' WHERE movie_id = %s", newTitle, movieId );
		simpleQuery( database, query );
	}
	
	/* update movie -- category */
	public static void updateMovieCategory( Database database, int movieId, int newCategory )
	{
		String query = String.format( "UPDATE MM_MOVIE SET movie_cat_id = %s WHERE movie_id = %s", newCategory, movieId );
		simpleQuery( database, query );
	}
	
	/* update movie -- value */
	public static void updateMovieValue( Database database, int movieId, double newValue )
	{
		String query = String.format( "UPDATE MM_MOVIE SET movie_value = %s WHERE movie_id = %s", newValue, movieId );
		simpleQuery( database, query );
	}
	
	/* update movie -- quantity */
	public static void updateMovieQuantity( Database database, int movieId, int newQuantity )
	{
		String query = String.format( "UPDATE MM_MOVIE SET movie_qty = %s WHERE movie_id = %s", newQuantity, movieId );
		simpleQuery( database, query );
	}
}
