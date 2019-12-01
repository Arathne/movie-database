package menus;

import database.MovieQuery;
import database.RentalQuery;

public class CurrentMemberMenu extends MainMenu
{
	private int memberId = -1;
	
	public void run()
	{
		InputHandler input = new InputHandler();

		boolean run = true;
		while ( run )
		{
			System.out.println( "\nCurrent Member Menu:" + "\n" +
					"1) List Movies" + "\n" +
					"2) List Rentals" + "\n" +
					"3) Rent Movie" + "\n" +
					"4) Return Movie" + "\n" +
					"5) Go Back" + "\n" );
			
			String answer = input.getString( "**Choose an option**" );
			if ( answer.equals("1") ){
				MovieQuery.listAllMovies( database );
			}
			else if ( answer.equals("2") ) {
				RentalQuery.listRentals( database, memberId );
			}
			else if ( answer.equals("3") ) {
				MovieQuery.listAllMovies( database );
				int movieId = input.getInteger( "\nChoose a movie" );
				RentalQuery.listAllPayment( database );
				int paymentId = input.getInteger( "\nChoose a payment method" );
				RentalQuery.rentMovie( database, memberId, movieId, paymentId );
			}
			else if ( answer.equals("4") ) {
				RentalQuery.listRentals( database, memberId );
				int rentalId = input.getInteger( "\nWhich movie would you like to return?" );
				RentalQuery.returnMovie(database, memberId, rentalId);
			}
			else if ( answer.equals("5") ) {
				run = false;
			}
		}
	}
	
	public void setMemberId( int memberId )
	{
		this.memberId = memberId;
	}
}
