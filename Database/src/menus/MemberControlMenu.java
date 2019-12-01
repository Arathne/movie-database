package menus;

import database.MemberQuery;

public class MemberControlMenu extends MainMenu {
	public void run()
	{
		InputHandler input = new InputHandler();
		MemberUpdateMenu updateMenu = new MemberUpdateMenu();
		
		updateMenu.setDatabase( database );
		
		boolean run = true;
		while ( run )
		{
			System.out.println( "\nMember Control Menu:" + "\n" +
					"1) List Members" + "\n" +
					"2) Add Member" + "\n" +
					"3) Update Member" + "\n" +
					"4) Remove Member" + "\n" +
					"5) Go Back" + "\n" );
			  
			String answer = input.getString( "**Choose an option**" );
			if ( answer.equals("1") ){
				MemberQuery.listAllMembers( database );
			}
			else if ( answer.equals("2") ) {
				String firstName = input.getString( "\nInput first name" );
				String lastName = input.getString( "\nInput last name" );
				String licenseNo = input.getString( "\nInput license number (9 numbers)" );
				String licenseSt = input.getString( "\nInput license state (2 letters)" );
				String creditCard = input.getString( "\nInput credit card number (12 numbers)" );
				String suspension = input.getString( "\nWill this person be suspended from renting a movie? (Y/N)" );
				String mailingList = input.getString( "\nWill this person be in the mailing list? (Y/N)" );
				
				MemberQuery.insertMember( database, firstName, lastName, licenseNo, licenseSt, creditCard, suspension, mailingList );
			}
			else if ( answer.equals("3") ) {
				updateMenu.run();
			}
			else if ( answer.equals("4") ) {
				int memberId = input.getInteger( "\nInput ID of member you wish to remove" );
				MemberQuery.removeMember( database, memberId);
			}
			else if ( answer.equals("5") ) {
				run = false;
			}
		}
	}
}
