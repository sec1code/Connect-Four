package logic;

public class GameBoard {
	// 6 -> höhe (y) | 7 -> breite (x)
	private final int height = 6;
	private final int width = 7;
	private int[][] gameBoard = new int[height][width];
	private int latestYCoord;
	
	public GameBoard() {
		latestYCoord = -1;
		setupBoard();
	}
	
	public void setupBoard() {
		for(int i = 0; i < this.height; i++) {
			for(int j = 0; j < this.width; j++) {
				gameBoard[i][j] = 0;
			}
		}
	}
	
	private void setLatestYCoord(int y)  {
		latestYCoord = y;
	}
	
	public int getLatestYCoord() {
		return latestYCoord;
	}
	
	
	//places token on the board. Returns true if token was placed, returns false if token couldn't be places.
	public boolean placeToken(int player, int x) {
		for(int i = this.height; i > 0; i--) {
			if(isCoordAvailable(x, i)) {
				editBoard(player, x, i);
				setLatestYCoord(i);
				return true;
			}
		}
		System.out.println("ERROR: Spalte ist voll oder Koordinaten sind unmöglich, versuche es erneut.");
		System.out.println();
		return false;
	}
	
	//places an 1 for player 1 | places an 2 for player 2
	private void editBoard(int player, int x, int y) {
		gameBoard[y-1][x-1] = player;
	}
	
	//checks if a coordinate is already occupied and if a coordinate is inbounds
	public boolean isCoordAvailable(int x, int y) {
		if(isCoordInBounds(x, y)&& gameBoard[y-1][x-1] == 0) {
			return true;
		}
		return false;
	}
	
	//checks if a coordinate is inbounds. Returns true if coordinate is inbounds
	 private boolean isCoordInBounds(int x, int y) {
		if(x > 0 && x < 8 && y > 0 && y  < 7 ) {
			return true;
		}
		return false;
	}
	
	
	//fuses all of the checkMethods and returns true if any of them are true
	public boolean win(int player, int x, int y) {
		if(checkHorizontals(player, x ,y) || checkVerticals(player, x, y) || checkDiagonals(player, x, y)) {
			return true;
		}
		return false;
	}
	
	
	//return true if a player has won. Input Coords are the Coords of the token placed
	private boolean checkHorizontals(int player, int x, int y) {
		//if at the end of the method, this variable hits 4 or more(five ar theoritically possible) this method returns true.
		int countTokens = 1;
	
		//first checks right of the coord until checkIfCoordIdentical returns false, then checks left of the coord until false is returned
		int index = 1;
		while(true) {
			if(checkIfCoordIdentical(player, x + index, y)) {
				countTokens++;
			}
			if(checkIfCoordIdentical(player, x - index, y)) {
				countTokens++;
			}
			
			if(checkIfCoordIdentical(player, x + index, y) == false && checkIfCoordIdentical(player, x - index, y) == false) {
				break;
			}
			index++;
		}
		
		if(countTokens >= 4) {
			return true;
		}
		return false;
	}
	
	//basically the same like checkHorizontals
	private boolean checkVerticals(int player, int x, int y) {
		int countTokens = 1;
		
		int index = 1;
		while(true) {
			if(checkIfCoordIdentical(player, x, y + index)) {
				countTokens++;
			}
			if(checkIfCoordIdentical(player, x, y - index)) {
				countTokens++;
			}
			
			if(checkIfCoordIdentical(player, x, y + index) == false && checkIfCoordIdentical(player, x, y - index) == false) {
				break;
			}
			index++;
		}
		
		if(countTokens >= 4) {
			return true;
		}
		
		return false;
	}
	
	//so there are two possible diagonals bottom-left to top-right and top-left to bottom-right
	//and tbh I was to lazy to put em in one method, so I made two methods that are very similar to the other "check" methods
	//and this method combines both of the others. If either one is true, this method returns true.
	private boolean checkDiagonals(int player, int x, int y) {
		if(checkDiagonalBLTR(player, x, y) || checkDiagonalTLBR(player, x, y)) {
			return true;
		}
		return false;
	}
	
	private boolean checkDiagonalBLTR(int player, int x, int y) {
		int countTokens = 1;
		
		int index = 1;
		while(true) {
			if(checkIfCoordIdentical(player, x - index, y + index)) {
				countTokens++;
			}
			if(checkIfCoordIdentical(player, x + index, y - index)) {
				countTokens++;
			}
			
			if(checkIfCoordIdentical(player, x - index, y + index) == false && checkIfCoordIdentical(player, x + index, y - index) == false) {
				break;
			}
			index++;
		}
		
		if(countTokens >= 4) {
			return true;
		}
		
		return false;
	}
	
	private boolean checkDiagonalTLBR(int player, int x, int y) {
		int countTokens = 1;
		
		int index = 1;
		while(true) {
			if(checkIfCoordIdentical(player, x - index, y - index)) {
				countTokens++;
			}
			if(checkIfCoordIdentical(player, x + index, y + index)) {
				countTokens++;
			}
			
			if(checkIfCoordIdentical(player, x - index, y - index) == false && checkIfCoordIdentical(player, x + index, y + index) == false) {
				break;
			}
			index++;
		}
		
		if(countTokens >= 4) {
			return true;
		}
		
		return false;
	}
	
	//checks if a given coordinate has the same token as the token given as input
	private boolean checkIfCoordIdentical(int player, int x, int y) {
		if(isCoordInBounds(x, y)) {
			if(gameBoard[y-1][x-1]==player) {
				return true;
			}
		}
		return false;
	}
	
	public void printBoard() {
		System.out.println("     ║  #  ║#### ║#####║#   #║ ####║#####║#####║\r\n"
				         + "     ║ ##  ║    #║    #║#   #║#    ║#    ║    #║\r\n"
				         + "     ║# #  ║ ### ║ ####║#####║ ### ║#####║   # ║\r\n"
				         + "     ║  #  ║#    ║    #║    #║    #║#   #║  #  ║\r\n"
				         + "     ║  #  ║ ####║#####║    #║#### ║#####║ #   ║\r\n"
				         + "     ╠═════╬═════╬═════╬═════╬═════╬═════╬═════╣");
		for(int i = 0; i < this.height; i++) {
			//System.out.print(i+1 + " ");
			for(int k = 0; k < 2; k++) {
				System.out.print("     ║ ");
				for(int j = 0; j < this.width; j++) {
					for(int l = 0; l<3; l++) {
						if(gameBoard[i][j] == 0) {
							System.out.print(" ");
						} else if(gameBoard[i][j] == 1) {
							System.out.print("▓");
						} else if(gameBoard[i][j] == 2) {
							System.out.print("░");
						}
					}
					System.out.print(" ║ ");
				}
				System.out.println();
			}
			if(i == this.height - 1) {
				System.out.println("     ╚═════╩═════╩═════╩═════╩═════╩═════╩═════╝");
			} else {
				System.out.println("     ╠═════╬═════╬═════╬═════╬═════╬═════╬═════╣");
			}

		}
	}
}
