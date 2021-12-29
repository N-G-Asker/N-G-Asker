public interface Game
{
	public boolean isValidMove(String move);
	
	public void executeMove(String move, Player player);

	public boolean checkWinner();
}

