/** The board is a two-deimensional, 6x6 array ("Matrix") initially composed of blank/empty cells
 * (null values) but which can be filled with player pieces/tokens.
 *
 *
 *
 *
 */


import java.util.ArrayList;

public class Board
{
	// Instance Variables
	private Piece[][] gameboard;
	
	public Board()
	{
		gameboard = new Piece[6][7];
	}
	
	public Piece[][] getBoard()
	{
		return gameboard;
	}
	
	/**
	 * gameboard[0].length == number of columns in row 0 of the 2D Array,
	 * which is equivalent to the number of columns in the entire array, 
	 * since it's the same for every row.
	 * 
	 * Relies on toString defined for piece, which prints an X or and O
	 * depending on the color binary variable.
	 *	
	 */
	public void drawBoard()
	{
		System.out.print("\n\t    "); /* Necessary to position the column
					    numbering properly*/
		
		for(int column = 1; column <= gameboard[0].length; column++) // prints out column number on border
		{
			System.out.print( column + "   ");
		}
		
		System.out.print("\n"); 

		for(int row = 0; row < gameboard.length; row++)
		{
		
			System.out.print( "\t" + (6-row) + " |");	// prints out row number on border
			
			for(int column = 0; column < gameboard[0].length; column++)
			{
				if(gameboard[row][column] == null)
				{
					System.out.print( " " + "_" + " |");
				}
				else
				{
					System.out.print(" " + gameboard[row][column] + " |");
				}
			}
			
			System.out.print( " " + (6-row) + "\n");
		} 

		System.out.print("\t    "); /* Necessary to position the column
		numbering properly*/

		for(int column = 1; column <= gameboard[0].length; column++) // prints out column number on border
		{
			System.out.print("~   ");
		}

		System.out.print("\n");

		System.out.print("\t    "); /* Necessary to position the column
					    numbering properly*/
		
		for(int column = 1; column <= gameboard[0].length; column++) // prints out column number on border
		{
			System.out.print( column + "   ");
		}

		System.out.print("\n");
	}
	/**checkColumnFull() method should always precede this method call. */
	public void addPiece(Player player, int column)
	{
		for(int row = gameboard.length -1 ; row >= 0; row--)
		{
			if(gameboard[row][column] == null) // "if slot open"
			{
				gameboard[row][column] = new Piece(player.getColor());

				System.out.printf("\nPiece falls to position (%d, %d) on grid\n", (column+1), (6-row));

				break;
			}
		}
	}

	public boolean checkColumnFull(int column)
	{
		if(gameboard[0][column] != null) // if top slot taken
		{
			return true;
		}
		else
		{
			return false;
		}
	}
	
	public boolean checkGridFull()
	{
		int columnFull = 0; // running tally of number of full columns

		/* for every column, if we encounter one that is not full, we know
			the grid is not full*/
		for(int column = 0; column < gameboard[0].length; column++)
		{
			if(checkColumnFull(column) == false)
			{
				return false;
			}
		}

		return true;
	}

	public int cellAsInt(Piece cell)
	{
		if(cell == null)
		{
			return 0;
		}
		else
		{
			if(cell.getColor()==true)
			{
				return 1;
			}
			else // color is false
			{
				return 10;
			}
		}
	}

	////////// ROWS - checking for win condition ///////////////


	public int sumRow(int row)
	{
		int tally = 0;
		for(int column = 0; column < gameboard[0].length; column++)
		{
			tally += cellAsInt(gameboard[row][column]);
		}

		return tally;
	}

	public int[] rowAsIntArray(int row)
	{
		int[] intR = new int[7];

		for(int column = 0; column < gameboard[0].length; column++)
		{
			intR[column] = cellAsInt(gameboard[row][column]);
		}
		return intR;
	}

	public boolean checkWinRow(int row)
	{
		int rowSum = sumRow(row);

		if(rowSum % 10 >= 4) // means there are 4 or more tokens with color=true
		{
			/* We only go through the trouble of creating the rowAsArray
			   -- and thus introducing additional overhead/time cost -- once we
			   are sure we have a possible win condition */
			int[] intR = rowAsIntArray(row);

			int streak = 0;

			for(int num : intR)
			{
				if(num == 1)
				{
					streak++;
				}
				else
				{
					streak = 0;
				}

				if(streak >= 4)
					{
						return true;
					}
			}
		}
		/* else if chosen here because we can't have a win condition for one
		   if the other has one */
		else if(rowSum/10 >= 4) // only when 4 or more tokens have color==false
		{
			int[] intR = rowAsIntArray(row);

			int streak = 0;

			for(int num : intR)
			{
				if(num == 10)
				{
					streak++;
				}
				else
				{
					streak = 0;
				}

				if(streak >= 4)
				{
					return true;
				}
			}
		}
		return false;
	}


	////////// COLUMNS - checking for win condition ///////////////

	public int sumColumn(int column)
	{
		int tally = 0;
		for(int row = 0; row < gameboard.length; row++)
		{
			tally += cellAsInt(gameboard[row][column]);
		}

		return tally;
	}

	public int[] columnAsIntArray(int column)
	{
		int[] intR = new int[6];

		for(int row = 0; row < gameboard.length; row++)
		{
			intR[row] = cellAsInt(gameboard[row][column]);
		}
		return intR;
	}

	public boolean checkWinColumn(int column)
	{
		int columnSum = sumColumn(column);

		if(columnSum % 10 >= 4) // means there are 4 or more tokens with color=true
		{
			/* We only go through the trouble of creating the rowAsArray
			   -- and thus introducing additional overhead/time cost -- once we
			   are sure we have a possible win condition */
			int[] intR = columnAsIntArray(column);

			int streak = 0;

			for(int num : intR)
			{
				if(num == 1)
				{
					streak++;
				}
				else
				{
					streak = 0;
				}

				if(streak >= 4)
					{
						return true;
					}
			}
		}
		/* else if chosen here because we can't have a win condition for one
		   if the other has one */
		else if(columnSum/10 >= 4) // only when 4 or more tokens have color==false
		{
			int[] intR = columnAsIntArray(column);

			int streak = 0;

			for(int num : intR)
			{
				if(num == 10)
				{
					streak++;
				}
				else
				{
					streak = 0;
				}

				if(streak >= 4)
				{
					return true;
				}
			}
		}
		return false;
	}

	////////// DIAGONALS - checking for win condition ///////////////

	/* Increasing Diagonals: 
	 * 3 9 15 21; 4 10 16 22 28; 
	 * 5 11 17 23 29 35; 6 12 18 24 30 36;
	 * 13 19 25 31 37; 20 26 32 38
	 */
	public static int[] diagUpStartingI = {3, 4, 5, 6, 13, 20};

	/* Decreasing Diagonals: 
	 * 14 22 30 38; 7 15 23 31 39;
	 * 0 8 16 24 32 40; 1 9 17 25 33 41;
	 * 2 10 18 26 34; 3 11 19 27
	 */
	public static int[] diagDownStartingI = {0, 1, 2, 3, 7, 14};
	
	
	public ArrayList<Integer> findDiagCandidates(int[] diagUpOrDownStartingI, String UpOrDown)
	{

		ArrayList<Integer> candidatesDiag = new ArrayList<>();
		
		int increment = 1;
		
		if(UpOrDown.equals("Up"))
		{
			increment = 6;
		}
		else // UpOrDown = "Down"
		{
			increment = 8;
		}

		for(int start : diagUpOrDownStartingI)
		{

			int index = start;
			
			int tally = 0;
			
			while(index <= 41)
			{
				int rowIndex = index/7; // thanks to floor division
				int columnIndex = index % 7;
				
				tally += cellAsInt(gameboard[rowIndex][columnIndex]);
				
				index += increment; 
			}
			
			if(tally % 10 >= 4 || tally/10 >= 4)
			{
				candidatesDiag.add(start);
			}
		}
		
		return candidatesDiag;

	}
	
	public boolean checkDiagWin(ArrayList<Integer> candidatesDiag, String UpOrDown)
	{
		int increment = 0;
		
		if(UpOrDown.equals("Up"))
		{
			increment = 6;
		}
		else // UpOrDown = "Down"
		{
			increment = 8;
		}
		
		
		for(int start : candidatesDiag)
		{

			int index = start;
			
			ArrayList<Integer> targetAL = new ArrayList<>();
			
			while(index <= 41)
			{
				int rowIndex = index/7;      // thanks to floor division
				int columnIndex = index % 7;
				
				targetAL.add(cellAsInt(gameboard[rowIndex][columnIndex]));
				
				index += increment; 
			}
			
			
			int diagSum = 0;
			for(int num : targetAL)
			{
				diagSum += num;
			}

			if(diagSum % 10 >= 4) // means there are 4 or more tokens with color=true
			{
				int streak = 0;

				for(int num : targetAL)
				{
					if(num == 1)
					{
						streak++;
					}
					else
					{
						streak = 0;
					}

					if(streak >= 4)
					{
						return true;
					}
				}
			}
			/* else if chosen here because we can't have a win condition for one
			   if the other has one */
			else if(diagSum/10 >= 4) // only when 4 or more tokens have color==false
			{
				int streak = 0;

				for(int num : targetAL)
				{
					if(num == 10)
					{
						streak++;
					}
					else
					{
						streak = 0;
					}

					if(streak >= 4)
					{
						return true;
					}
				}
			}
		}
		return false;
	}

	public int[][] asIntMatrix()
	{
		int[][] intMatrix = new int[6][7];
				
		for(int row = 0; row < gameboard.length; row++)
		{			
			for(int column = 0; column < gameboard[0].length; column++)
			{
				intMatrix[row][column] = cellAsInt(gameboard[row][column]);
			}
		} 

		return intMatrix;
	}	
}
