public class PlayGame
{
    public static void main(String[] args)
    {
        try
        {
            String playerName = args[0];
            String playerSymbol = args[1];

            CuatroEnRaya c4;
            
            if(args.length == 2)
            {
                c4 = new CuatroEnRaya(playerName, playerSymbol);
            }
            else
            {
                String player2Name = args[2];
                c4 = new CuatroEnRaya(playerName, playerSymbol, player2Name);
            }

            c4.play();
        }
        catch(IllegalArgumentException e)
        {
            System.err.println("Error: Failed to properly enter the letter "+
                               "'X' or 'O' as the second command-line "+
                               "argument.");

            printSyntax();
            
            System.exit(-1);
        }
        catch(ArrayIndexOutOfBoundsException err)
        {
            System.err.println("Error: Failed to provide required"+
            " command-line arguments.");
            
            printSyntax();

            System.exit(-1);
        }
    }

    public static void printSyntax()
    {
        System.err.println("Please ensure you are following proper syntax.");

        System.err.println("\n" + "USAGE" + "\n" + "\n" +
        "Single Player vs. Computer:" + "\n" + "\n" +
            "\t`java PlayGame <Player Name> <X or O>`" + "\n"+
            "\n" +
           "    e.g." + "\n" +
            "\t$ java PlayGame Sandy X" + "\n" + 
            "\t$ java PlayGame Tom O" + "\n" + "\n" +
        "Two Player: " + "\n" + "\n" +
            "\t`java PlayGame <Player Name> <X or O> <Player2 Name>` \n" + 
            "\n" +
           "    e.g." + "\n"+
            "\t$ java PlayGame Sandy X Tom" + "\n" + 
            "\t$ java PlayGame Cindy O Chris" + "\n");
    }
}