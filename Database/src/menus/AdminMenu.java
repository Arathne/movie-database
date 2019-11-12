package menus;

import database.Query;

public class AdminMenu extends MainMenu{
	public void run()
	{
		InputHandler input = new InputHandler();
		
		
		
		boolean run = true;
		while ( run )
		{
			System.out.println( "\nMain Menu:" + "\n" +
					"1) List Movies" + "\n" +
					"2) Add Movie" + "\n" +
					"3) Update Movie" + "\n" +
					"4) Delete Movie" + "\n" +
					"5) Go Back" + "\n");
			
			String answer = input.getString( "**Choose an option**" );
			if ( answer.equals("1") ){
				System.out.println();
				Query.printAllMembers( database );
			}
			else if ( answer.equals("2") ) {
				System.out.println( "\nADD MOVIE" );
			}
			else if ( answer.equals("3") ) {
				System.out.println( "\nUPDATE MOVIE" );
			}
			else if ( answer.equals("4") ) {
				System.out.println( "\nDELETE MOVIE" );
			}
			else if ( answer.equals("5") ) {
				run = false;
			}
		}
	}
}
