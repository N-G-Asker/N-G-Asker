public class Connect4Tester
{
    public static void main(String[] args)
    {
        String playerName = args[0];
        boolean playerColor = Boolean.valueOf(args[1].toLowerCase());

        ConnectFour c4;
        
        if(args.length == 2)
        {
            c4 = new ConnectFour(playerName, playerColor);
        }
        else
        {
            String guestName = args[2];
            c4 = new ConnectFour(playerName, playerColor, guestName);
        }    
        c4.play();
    }
}