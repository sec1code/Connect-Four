package ui;

import java.util.Scanner;

import logic.GameBoard;

public class UserInterface {
	
	public void run() {
		GameBoard gb = new GameBoard();
		
		int iteration = 1;
		int player = 0;
		
		printTitle();

		Scanner scanner = new Scanner(System.in);
		while(true) {
			
			System.out.println("Aktueller Spielstand:");
			System.out.println();
			gb.printBoard();
			System.out.println();
			
			if(iteration%2==0) {
				player = 2;
			} else {
				player = 1;
			}
			
			System.out.println("Spieler " + player + " ist dran.");
			System.out.println();
			System.out.println("Wo soll der Spielstein platziert werden?");
			System.out.println();
			int xCoord = 0;
			int yCoord = -1;
			boolean gameEnded = false;
			while(true) {
				System.out.println("Bitte X Koordinate eingeben:");
				xCoord = Integer.parseInt(scanner.nextLine());
				System.out.println();
				
				if(xCoord == -1) {
					gameInterrupted(gb);
					gameEnded = true;
					break;
				}
				
				if(gb.placeToken(player, xCoord)) {
					yCoord = gb.getLatestYCoord();
					break;
				}
				
			}
			
			if(gb.win(player, xCoord, yCoord)) {
				if(win(player, gb, scanner)) {
					gb.setupBoard();
					for(int i = 0; i < 100; i++) {
						System.out.println();
						iteration = player+1;
					}
					printTitle();
				} else {
					break;	
				}
			} else {
				for(int i = 0; i < 15; i++) {
					System.out.println();
				}
			}
			if(gameEnded) {
				break;
			}
			

			
			iteration++;
		}
		scanner.close();
	}
	
	public void printTitle() {
		System.out.println();
		System.out.println();
		System.out.println();
		System.out.println("                           \\ \\    / (_)            / ____|             (_)           | |                                \r\n"
				+ "  ______ ______ ______ _____\\ \\  / / _  ___ _ __  | |  __  _____      ___ _ __  _ __ | |_   ______ ______ ______ ______ \r\n"
				+ " |______|______|______|______\\ \\/ / | |/ _ \\ '__| | | |_ |/ _ \\ \\ /\\ / / | '_ \\| '_ \\| __| |______|______|______|______|\r\n"
				+ "                              \\  /  | |  __/ |    | |__| |  __/\\ V  V /| | | | | | | | |_                               \r\n"
				+ "                               \\/   |_|\\___|_|     \\_____|\\___| \\_/\\_/ |_|_| |_|_| |_|\\__|                              \r\n"
				+ "");
		System.out.println();
		System.out.println();
		System.out.println();
		System.out.println();
	}
	
	public boolean win(int player, GameBoard gb, Scanner scanner) {
		for(int i = 0; i < 100; i++) {
			System.out.println();
		}
		gb.printBoard();
		System.out.println();
		System.out.println();
		System.out.println("Spieler " + player + " hat gewonnen!");
		System.out.println("GG");
		System.out.println();
		System.out.println("Schreibe \"ja\", wenn das Spiel erneut gestartet werden soll.");
		String playAgain = scanner.nextLine();
		if(playAgain.equalsIgnoreCase("ja")) {
			return true;
		}
		return false;
	}
	
	public void gameInterrupted(GameBoard gb) {
		for(int i = 0; i < 100; i++) {
			System.out.println();
		}
		gb.printBoard();
		System.out.println();
		System.out.println();
		System.out.println("SPIEL ABGEBROCHEN	!");
	}
}
