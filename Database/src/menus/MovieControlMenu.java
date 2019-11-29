package menus;
import database.MovieQuery;

public class MovieControlMenu extends AdminMenu 
{
	InputHandler input = new InputHandler();
	
	public void run()
	{	
		boolean run = true;
		while ( run )
		{
			System.out.println( "\nMovie Control Menu" + "\n" +
					"1) List Movies" + "\n" +
					"2) Add Movie" + "\n" + 
					"3) Update Movie" + "\n" +
					"4) Remove Movie" + "\n" +
					"5) Go Back" + "\n" );
			
			String answer = input.getString( "**Choose an option**" );
			
			if ( answer.equals("1") ){
				MovieQuery.listMovies( database );
			}
			else if ( answer.equals("2") ) {
				addMovieOption();
			}
			else if ( answer.equals("3") ) {
				updateMovieOption();
			}
			else if ( answer.equals("4") ) {
				removeMovieOption();
			}
			else if ( answer.equals("5") ) {
				run = false;
			}
		}
	}
	
	private void addMovieOption()
	{
		String title = input.getString( "\nTitle of movie?" );
		MovieQuery.listCategories( database );
		int category = input.getInteger( "\nCategory? (pick number)" );
		float value = input.getFloat("\nValue? (5-100)");
		int quantity = input.getInteger( "\nQuantity?" );
		MovieQuery.insertMovie( database, title, category, value, quantity );
	}
	
	private void removeMovieOption()
	{
		int id = input.getInteger( "\nEnter ID number of the movie you wish to delete" );
		MovieQuery.removeMovie(database, id);
	}
	
	private void updateMovieOption()
	{
		
	}
}