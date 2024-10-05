package application;

import java.util.ArrayList;
import java.util.Scanner;

import entities.BlackJackGame;

public class BlackJackGameTest {

	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		BlackJackGame game;
		
		System.out.println("\t========== Welcome to Blackjack! ==========");
		System.out.print("\n\tEnter the number of players: ");
		int numberOfPlayers = scanner.nextInt();
		scanner.nextLine();
		
		System.out.print("\n\n\tDo you want to enter custom player names? (yes/no)" + "\n\t\tR:");
        String responseNames = scanner.nextLine().trim().toLowerCase();
        
        
        if(responseNames.equals("yes")) {
        	ArrayList<String> playersName = new ArrayList<>();
        	for(int i = 1; i <= numberOfPlayers; i++) {
        		System.out.print("Enter the name of player" + i + ": ");
        		playersName.add(scanner.nextLine());
        	}
        	game = new BlackJackGame(playersName);
        } else {
        	game = new BlackJackGame(numberOfPlayers);
        }
		
		game.startGame();
		
		scanner.close();	
	}

}
