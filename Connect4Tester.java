public class Connect4Tester
{
    public static void main(String[] args)
    {
        String playerName = args[0];
        String playerSymbol = args[1];

        ConnectFour c4;
        
        if(args.length == 2)
        {
            c4 = new ConnectFour(playerName, playerSymbol);
        }
        else
        {
            String player2Name = args[2];
            c4 = new ConnectFour(playerName, playerSymbol, player2Name);
        }  

        c4.play();
    }
}