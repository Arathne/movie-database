package database;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import types.Member;

public class MemberQuery extends Query
{
	public static void listAllMembers( Database database )
	{
		String query = "SELECT member_id, last, first, license_no, license_st, credit_card, suspension, mailing_list FROM MM_MEMBER";
		ResultSet table = simpleQuery( database, query ); //get table info
		
		if( table != null )
		{
			try {
			 	System.out.println();
				while ( table.next() )
				{
					int id = table.getInt( "member_id" );
					String firstName = table.getString( "first" );
					String lastName = table.getString( "last" );
					String licenseNo  = table.getString( "license_no" );
					String licenseSt = table.getString( "license_st" );
					String creditCard = table.getString( "credit_card" );
					String suspension = table.getString( "suspension" );
					String mailingList = table.getString( "mailing_list" );
					Member current_member = new Member( id, firstName, lastName, licenseNo, licenseSt, creditCard, suspension, mailingList );
					System.out.println( "\t"+ current_member );
				}
			} catch (SQLException e) {
				System.out.println("no movies found");
			}
		}
	}
	
	public static void insertMember( Database database, String firstName, String lastName, String licenseNo, String licenseSt, String creditCard, String suspension, String mailingList )
	{
		String query = String.format( "INSERT INTO mm_member ( last, first, license_no, license_st, credit_card, suspension, mailing_list ) VALUES( '%s', '%s', '%s', '%s', '%s', '%s', '%s' )", lastName, firstName, licenseNo, licenseSt, creditCard, suspension, mailingList );
		simpleQuery( database, query );
	}
	
	public static void removeMember( Database database, int memberId )
	{
		String query = String.format( "call force_delete( %s )", memberId );
		simpleQuery( database, query );
	}
	
	public static void updateMemberName( Database database, int memberId, String firstName, String lastName )
	{
		String query = String.format( "UPDATE mm_member SET first = '%s', last = '%s' WHERE member_id = %s", firstName, lastName, memberId );
		simpleQuery( database, query );
	}
	
	public static void updateMemberLicense( Database database, int memberId, String licenseNo, String licenseSt )
	{
		String query = String.format( "UPDATE mm_member SET license_no = '%s',  license_st = '%s' WHERE member_id = %s", licenseNo, licenseSt, memberId );
		simpleQuery( database, query );
	}
	
	public static void updateMemberCreditCard( Database database, int memberId, String creditCard )
	{
		String query = String.format( "UPDATE mm_member SET credit_card = '%s' WHERE member_id = %s", creditCard, memberId );
		simpleQuery( database, query );
	}
	
	public static void updateMemberSuspension( Database database, int memberId, String suspension )
	{
		String query = String.format( "UPDATE mm_member SET suspension = '%s' WHERE member_id = %s", suspension, memberId );
		simpleQuery( database, query );
	}
	
	public static void updateMemberMailingList( Database database, int memberId, String mailingList )
	{
		String query = String.format( "UPDATE mm_member SET mailing_list = '%s' WHERE member_id = %s", mailingList, memberId );
		simpleQuery( database, query );
	}
}

