import java.util.Random;
import java.util.Scanner;
import java.util.concurrent.TimeUnit;

public class Player
{
	// Instance Variables
	private String name;
	private boolean color;
	
	// Constructor
	public Player(String name, boolean color)
	{
		this.name = name;
		this.color = color;
	}

	public String getName()
	{
		return name;
	}
	
	public boolean getColor()
	{
		return color;
	}

	public String toString()
	{
		return name;
	}

	public String promptMove()
    {
		System.out.println("\n" + "~~~ " + name + "'s turn ~~~");

		/* Computer player slides a piece into a random column if possible */
		if(name.equals("Computer"))
		{
			Random r = new Random();

			int columnInt = r.nextInt(7);

			String columnStr = String.valueOf(columnInt);

			System.out.println("...Computer is thinking...");

			wait(2000);

			System.out.print("\n> Computer places piece in column " + (int)(columnInt+1) + "\n ");

			return columnStr;

		}
		else
		{
			Scanner s = new Scanner(System.in);

			System.out.print("\nIn which column do you want to place a token? "+
							   "(Options: 1-7)\n > ");
	
			int move = s.nextInt() - 1;
			
			return String.valueOf(move);
		}

    }

	public void wait(int ms)
    {    
        try
        {
            TimeUnit.MILLISECONDS.sleep(ms);
        }
        catch(InterruptedException ie)
        {
            System.out.println("\n" + "Error while trying to create suspense "+
                                ie.getMessage());
        }
    }


}
