import java.util.Random;
import java.util.Scanner;
import java.lang.IllegalArgumentException;

public class Player
{
	// Instance Variables
	private String name;
	private String symbol;
	private boolean symbolAsBool;
	
	// Constructor
	public Player(String name, String symbol) throws IllegalArgumentException
	{
		char symbolAsChar = symbol.toUpperCase().charAt(0);

		if(symbolAsChar != 'X' && symbolAsChar != 'O')
		{
			throw new IllegalArgumentException();
		}

		this.name = name;
		this.symbol = String.valueOf(symbolAsChar);

		if(symbol.equals("X"))
		{
			this.symbolAsBool = true;
		}
		else
		{
			this.symbolAsBool = false;
		}
	}

	public String getName()
	{
		return name;
	}

	public String getSymbol()
	{
		return symbol;
	}
	
	public boolean getSymbolAsBool()
	{
		return symbolAsBool;
	}

	public String toString()
	{
		return name;
	}

	public String promptMove()
    {
		/* Computer player slides a piece into a random column if possible */
		if(name.equals("Computer"))
		{
			Random r = new Random();

			int columnInt = r.nextInt(7);

			String columnStr = String.valueOf(columnInt);

			System.out.println("...Computer is thinking...");

			CuatroEnRaya.wait(2000);

			System.out.print("\n> Computer places piece in column " + (int)(columnInt+1) + "\n ");

			return columnStr;
		}
		else
		{
			Scanner s = new Scanner(System.in);

			System.out.print("\nIn which column do you want to place a token?"+
							   " (Options: 1-7)\n > ");

			/* Basic Input Validation to preclude InputMismatchException 
			   (in a manner that is less clunky than with Try-Catch blocks)
			 */
			int input = 0;

			while(input <= 0 || input > 7)
			{
				while(!s.hasNextInt()) // triggered if user input != int
				{
					System.err.println("\n\t!Input provided is not a number!\n");
					System.err.print("Please enter a positive integer" +
									   " between 1 and 7. > ");
					s.next();
				}

				input = s.nextInt();

				if(input <= 0 || input > 7)
				{
					System.err.printf("\n\t!Input is out of range! Column" +
					" %d does not exist on the board.\n" +
					"\n Please enter a number" +
					" within the range 1-7. > ", input);
				}
			}

			int move = input - 1;
			
			return String.valueOf(move);
		}
    }
}
