package menus;

import database.MemberQuery;

public class MainMenu extends LogInMenu{
	public void run() 
	{
		InputHandler input = new InputHandler();
		AdminMenu adminMenu = new AdminMenu();
		CurrentMemberMenu memberMenu = new CurrentMemberMenu();
		
		adminMenu.setDatabase( database );
		memberMenu.setDatabase( database );
		
		boolean run = true;
		while ( run )
		{
			System.out.println( "\nMain Menu:" + "\n" +
					"1) Sign In as Admin" + "\n" +
					"2) Sign In as Member" + "\n" +
					"3) Exit" + "\n" );
			
			String answer = input.getString( "**Choose an option**" );
			if ( answer.equals("1") ){
				adminMenu.run();
			}
			else if ( answer.equals("2") ) {
				MemberQuery.listAllMembers( database );
				int memberId = input.getInteger( "\nInput an ID of an existing member" );
				memberMenu.setMemberId( memberId );
				memberMenu.run();
			}
			else if ( answer.equals("3") ) {
				System.out.println("\n\nTERMINATED");
				run = false;
			}
		}
	}
}
