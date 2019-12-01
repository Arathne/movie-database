package types;

public class Member
{
	private int id;
	private String firstName;
	private String lastName;
	private String licenseNo;
	private String licenseSt;
	private String creditCard;
	private String suspension;
	private String mailingList;
	
	public Member( int id, String firstName, String lastName, String licenseNo, String licenseSt, String creditCard, String suspension, String mailingList )
	{
		this.id = id;
		this.firstName = firstName;
		this.lastName = lastName;
		this.licenseNo = licenseNo;
		this.licenseSt = licenseSt;
		this.creditCard = creditCard;
		this.suspension = suspension;
		this.mailingList = mailingList;
	}
	
	public String toString()
	{
		String output = String.format( "%4s %13s %9s %10s %3s %13s %2s %5s" , id, firstName, lastName, licenseNo, licenseSt, creditCard, suspension, mailingList );
		return output;
	}
}
