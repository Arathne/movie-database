package menus;

import database.MovieQuery;

public class MovieUpdateMenu extends MovieControlMenu
{
	private InputHandler input = new InputHandler();
	private int movieId = -1;
	
	public void run()
	{	
		boolean run = true;
		while ( run )
		{
			System.out.println( "\n*** CURRENT ID IS " + movieId + " ***\n" +
					"\nMovie Update Menu" + "\n" +
					"1) Pick another ID" + "\n" +
					"2) List Movies" + "\n" + 
					"3) Update Title" + "\n" +
					"4) Update Category" + "\n" +
					"5) Update Value" + "\n" +
					"6) Update Quantity" + "\n" +
					"7) Go Back" + "\n" );
			
			String answer = input.getString( "**Choose an option**" );
			
			if ( answer.equals("1") ){
				movieId = input.getInteger( "\nInput an existing movie ID" );
				if( movieId == -1 )
					System.out.println( "\nInvalid Input :: ID reset to -1 " );
			}
			else if ( answer.equals("2") ) {
				MovieQuery.listAllMovies( database );
			}
			else if ( answer.equals("3") ) {
				String newTitle = input.getString( "\nInput new title of movie" );
				MovieQuery.updateMovieTitle( database, movieId, newTitle );
			}
			else if ( answer.equals("4") ) {
				MovieQuery.listAllCategories( database );
				int newCategoryId = input.getInteger( "\nInput the ID of the category you would like" );
				MovieQuery.updateMovieCategory( database, movieId, newCategoryId );
			}
			else if ( answer.equals("5") ) {
				float newValue = input.getFloat( "\nInput the new value" );
				MovieQuery.updateMovieValue( database, movieId, newValue );
			}
			else if ( answer.equals("6") ) {
				int newQuantity = input.getInteger( "\nInput the new quantity" );
				MovieQuery.updateMovieQuantity( database, movieId, newQuantity );
			}
			else if ( answer.equals("7") ) {
				run = false;
			}
		}
	}
	
	public void setMovieId( int movieId )
	{
		this.movieId = movieId;
	}
}