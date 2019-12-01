package menus;
import database.MemberQuery;

public class MemberUpdateMenu extends MemberControlMenu
{
	private InputHandler input = new InputHandler();
	private int memberId = -1;
	
	public void run()
	{	
		boolean run = true;
		while ( run )
		{
			System.out.println( "\n*** CURRENT ID IS " + memberId + " ***\n" +
					"\nMovie Update Menu" + "\n" +
					"1) Pick another ID" + "\n" +
					"2) List Members" + "\n" + 
					"3) Update Name" + "\n" +
					"4) Update License" + "\n" +
					"5) Update Credit Card" + "\n" +
					"6) Update Suspension" + "\n" +
					"7) Update Mailing List" + "\n" +
					"8) Go Back" + "\n" );
			
			String answer = input.getString( "**Choose an option**" );
			
			if ( answer.equals("1") ){
				memberId = input.getInteger( "\nInput an existing member ID" );
				if( memberId == -1 )
					System.out.println( "\nInvalid Input :: ID reset to -1" );
			}
			else if ( answer.equals("2") ) {
				MemberQuery.listAllMembers( database );
			}
			else if ( answer.equals("3") ) {
				String firstName = input.getString( "\nInput first name" );
				String lastName = input.getString( "\nInput last name" );
				MemberQuery.updateMemberName( database, memberId, firstName, lastName );
			}
			else if ( answer.equals("4") ) {
				String licenseNo = input.getString( "\nInput license number (9 numbers)" );
				String licenseSt = input.getString( "\nInput license state (2 letters)" );
				MemberQuery.updateMemberLicense( database, memberId, licenseNo, licenseSt );
			}
			else if ( answer.equals("5") ) {
				String creditCard = input.getString( "\nInput new credit card number (12 numbers)" );
				MemberQuery.updateMemberCreditCard( database, memberId, creditCard );
			}
			else if ( answer.equals("6") ) {
				String suspension = input.getString( "\nIs this member be suspended? (Y/N)" );
				MemberQuery.updateMemberSuspension( database, memberId, suspension );
			}
			else if ( answer.equals("7") ) {
				String mailingList = input.getString( "\nIs this member in the mailing list? (Y/N)" );
				MemberQuery.updateMemberMailingList( database, memberId, mailingList );
			}
			else if ( answer.equals("8") ) {
				run = false;
			}
		}
	}
}
