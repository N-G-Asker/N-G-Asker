public class Piece
{
	// Instance Variable(s)
	private boolean asBool;
	
	private String asString;

	private int asInt;
	
	/**
	 * Constructor
	 * 
	 * @param eitherXorO capital letter "X" or "O" which will set the instance
	 *  variable `asString` and indirectly set the instance variables `asBool`
	 *  and `asInt`
	 * 
	 */
	public Piece(String eitherXorO)
	{
		this.asString = eitherXorO;

		if(asString.equals("X"))
		{
			this.asBool = true;
			this.asInt = 1;
		}
		else
		{
			this.asBool = false;
			this.asInt = 10;
		}
	}

	// Accessor Method(s)
	public boolean getAsBool()
	{
		return asBool;
	}

	public String getAsString()
	{
		return asString;
	}

	public int getAsInt()
	{
		return asInt;
	}
	
	// toString()
	public String toString()
	{
		return asString;
	}
	

	// Static Method / "Helper function"
	public static String mapToString(boolean boolValue)
	{
		if(boolValue==true)
		{
			return "X";
		}
		else
		{
			return "O";
		}
	}
	

}
