package types;

public class Movie {
	private int id;
	private String title;
	private String category;
	private double value;
	private int quantity;
	
	public Movie( int id, String title, String category, double value, int quantity )
	{
		this.id = id;
		this.title = title;
		this.category = category;
		this.value = value;
		this.quantity = quantity;
	}
	
	public String toString()
	{
		String output = String.format("%3s %-41s %-14s $%-6s %-3s", id, title, category, value, quantity);
		return output;
	}
}
