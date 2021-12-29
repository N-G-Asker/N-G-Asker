public class Piece
{
	// Instance Variable(s)
	private boolean color; /* The game settings will determine the colors
	 			   to which true and false values correspond. */
	
	// Constructor
	public Piece(boolean color)
	{
		this.color = color;
	}

	// Accessor Method(s)
	public boolean getColor()
	{
		return color;
	}
	
	// toString()
	public String toString()
	{
		if(color == true)
		{
			return "X";
		}
		else
		{
			return "O";
		}
	}
	
}
