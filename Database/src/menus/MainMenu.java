package menus;

public class MainMenu extends LogInMenu{
	public void run() 
	{
		InputHandler input = new InputHandler();
		AdminMenu adminMenu = new AdminMenu();
		MemberMenu memberMenu = new MemberMenu();
		
		adminMenu.setDatabase( database );
		
		boolean run = true;
		while ( run )
		{
			System.out.println( "\nMain Menu:" + "\n" +
					"1) Admin" + "\n" +
					"2) Member" + "\n" +
					"3) Exit" + "\n" );
			
			String answer = input.getString( "**Choose an option**" );
			if ( answer.equals("1") ){
				adminMenu.run();
			}
			else if ( answer.equals("2") ) {
				memberMenu.run();
			}
			else if ( answer.equals("3") ) {
				System.out.println("\n\nTERMINATED");
				run = false;
			}
		}
	};
}
