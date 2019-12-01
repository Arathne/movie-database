package menus;
import java.util.InputMismatchException;
import java.util.Scanner;

public class InputHandler 
{
	private Scanner scan;
	
	public InputHandler()
	{
		scan = new Scanner( System.in );
	}
	
	private String outputFormat( String msg )
	{
		String output = String.format("%s\n?: ", msg);
		return output;
	}
	
	public String getString( String msg )
	{
		System.out.print( outputFormat( msg ) );
		String output = scan.nextLine();
		scan = new Scanner( System.in );
		return output;
	}
	
	public int getInteger( String msg )
	{
		System.out.print( outputFormat( msg ) );
		int output = -1;
		
		try
		{
			output = scan.nextInt();
		} catch ( InputMismatchException e ) {
			output = -1;
		}
		
		scan = new Scanner( System.in );
		return output;
	}
	
	public float getFloat( String msg )
	{
		System.out.print( outputFormat( msg ) );
		float output = -1;
		
		try
		{
			output = scan.nextFloat();
		} catch ( InputMismatchException e ) {
			output = -1;
		}
		
		scan = new Scanner( System.in );
		return output;
	}
}