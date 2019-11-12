package menus;
import database.Database;

public class LogInMenu {
	protected Database database = new Database();
	
	public void run()
	{
		MainMenu mainMenu = new MainMenu();
		
		InputHandler input = new InputHandler();
		
		String username = input.getString("\ninput USERNAME for dba connection");
		String password = input.getString("\ninput PASSWORD for dba connection");
		
		database.createConnection(username, password);
		
		if ( database.isConnected() )
		{
			mainMenu.setDatabase( database );
			mainMenu.run();
		}
	}
	
	protected void setDatabase( Database newDatabase )
	{
		database = newDatabase;
	}
}
