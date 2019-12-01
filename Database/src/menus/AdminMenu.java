package menus;

public class AdminMenu extends MainMenu{
	public void run()
	{
		InputHandler input = new InputHandler();
		MovieControlMenu movieMenu = new MovieControlMenu();
		MemberControlMenu memberMenu = new MemberControlMenu();
		
		movieMenu.setDatabase( database );
		memberMenu.setDatabase( database );
		
		boolean run = true;
		while ( run )
		{
			System.out.println( "\nAdmin Menu" + "\n" +
					"1) Edit Movies" + "\n" +
					"2) Edit Members" + "\n" + 
					"3) Go Back" + "\n" );
			
			String answer = input.getString( "**Choose an option**" );
			
			if ( answer.equals("1") ){
				movieMenu.run();
			}
			else if ( answer.equals("2") ) {
				memberMenu.run();
			}
			else if ( answer.equals("3") ) {
				run = false;
			}
		}
	}
}
