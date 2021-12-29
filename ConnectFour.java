import java.util.Random;
import java.util.ArrayList;
import java.util.concurrent.TimeUnit;


public class ConnectFour implements Game
{
	/**
     * Instance Variables
     */
	private Board grid;
    private Player user;
    private Player guest;


	/** 
     * Constructor 1: Single Player vs. Computer
     */
	public ConnectFour(String playerName, boolean playerColor)
	{
		grid = new Board();

        user = new Player(playerName, playerColor);

        guest =  new Player("Computer", !user.getColor());
	}

    /** 
     * Constructor 2: Two Players
     */
    public ConnectFour(String playerName, boolean playerColor, String guestName)
	{
		grid = new Board();

        user = new Player(playerName, playerColor);

        guest =  new Player(guestName, !user.getColor());
	}

    public void play()
    {

        Player player1 = coinFlipWinner();

        Player player2 = setPlayer2(player1);

        displayGrid();
        while(true)
        {
            /* Player 1 move */
            String p1Move = "";
            
            while(p1Move.equals("") || isValidMove(p1Move)==false)
            {
                p1Move = player1.promptMove();
            }

            executeMove(p1Move, player1);

            displayGrid();

            if(checkWinner() == true)
            {
                winnerDecorator(player1);
                break;
            }
            /* Check 4: TIE  when all columns full AND there's no winner;
               thus, this check needs to come last -- a player could 
               play the last possible piece and win if a 4-in-a-row
               is achieved with the move. */
            else if(grid.checkGridFull() == true)
            {
                tieDecorator();
                break;
            }

            /* Player 2 move */

            String p2Move = "";

            while(p2Move.equals("") || isValidMove(p2Move)==false)
            {
                p2Move = player2.promptMove();
            }

            executeMove(p2Move, player2);

            displayGrid();

            if(checkWinner() == true)
            {
                winnerDecorator(player2);
                break;
            }
            else if(grid.checkGridFull() == true)
            {
                tieDecorator();
                break;
            }
        }

    }

	public boolean isValidMove(String move)
	{
		/* Process/parse the move into integer form.
           
           In Connect4, players decide which column (0-6, seven options) they
           want to slide their token down. 
        */
        int x = Integer.parseInt(move); // input should be an integer 0-6
        
        /* Check 1: Whether column already full */
        if(grid.checkColumnFull(x) == true)
        {
            System.out.println("Column is already full. Please select another column. "); 
            return false;
        }
        else
        {
            return true;
        }
	}
	
	public void executeMove(String move, Player player)
	{
        wait(1900);
        
        int x = Integer.parseInt(move); // input should be an integer 0-6
        
        grid.addPiece(player, x);
	}

    /* Check if board is full only after first checking for winner -- a player
       could play the last possible piece and win if a 4-in-a-row is achieved 
       with the move.*/
	public boolean checkWinner()
	{
        /* Check 1: ROWS for four in a row */
        for(int rowNum = 0; rowNum < grid.getBoard().length; rowNum++)
        {
            if(grid.checkWinRow(rowNum) == true)
            {
                return true;
            }
        }

        /* Check 2: COLUMNS for four in a row */

        for(int columnNum = 0; columnNum < grid.getBoard()[0].length; columnNum++)
        {
            if(grid.checkWinColumn(columnNum) == true)
            {
                return true;
            }
        }

        /* Check 3: DIAGONALS for four in a row */

        ArrayList<Integer> possibleUps = grid.findDiagCandidates(Board.diagUpStartingI, "Up");
        ArrayList<Integer> possibleDowns = grid.findDiagCandidates(Board.diagDownStartingI, "Down");

        if(grid.checkDiagWin(possibleUps, "Up") || grid.checkDiagWin(possibleDowns, "Down"))
        {
            return true;
        }

        return false;

	}

    public Player coinFlipWinner()
    {
        System.out.println("Flipping a coin to determine" +
                           " which player goes first...");

        // Injecting a brief wait time for game UX (creates suspense?)
        wait(2300);
        
        Random r = new Random();
        
        int coinFlip = r.nextInt(2);
        
        if(coinFlip == 0)
        {
            System.out.printf("\n... %s goes first this game!\n", user.getName());
            return user;
        }
        else
        {
            System.out.printf("\n... %s goes first this game!\n", guest.getName());
            return guest;
        }
    }

    public Player setPlayer2(Player player1)
    {
        if(player1 == user)
        {
            return guest;
        }
        else
        {
            return user;
        }
    }

    public void displayGrid()
    {
        grid.drawBoard();
    }


    public void winnerDecorator(Player winner)
    {
        System.out.println("\n*******************************");
        System.out.println("\t" + winner.getName() + " WINS!");
        System.out.println("*******************************");
    }

    public void tieDecorator()
    {
        System.out.println("\n*******************************");
        System.out.println("Grid is completely full now!"+
                            "\t Game ends in a tie.");
        System.out.println("*******************************");
    }


    // Injecting a brief wait time for game UX (creates suspense?)
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
