package entities;

import java.util.ArrayList;
import java.util.Scanner;

public class BlackJackGame {
	private DeckOfCards deck;
	private ArrayList<Player> players;
	private Dealer dealer;
	private Scanner scanner = new Scanner(System.in);
	public static String tab = "\t";
	
	public BlackJackGame(int numberOfPlayers) {			//Construtor para players sem nome, apenas numerados
		deck = new DeckOfCards();
		deck.shuffle();
		players = new ArrayList<>();
		dealer =  new Dealer();
		
		for (int i = 0 ; i < numberOfPlayers ; i++) {
			players.add(new Player("Player" + (i+1)));
		}
	}
	
	public BlackJackGame(ArrayList<String> playersNames) {			//Construtor para players sem nome, apenas numerados
		deck = new DeckOfCards();
		deck.shuffle();
		players = new ArrayList<>();
		dealer =  new Dealer();
		
		for (String name : playersNames) {
			players.add(new Player(name));
		}
	}
	
	public void startGame() {
		for (Player player : players) {
			player.receiveCard(deck.dealCard());
			player.receiveCard(deck.dealCard());
		}
		
		dealer.receiveCard(deck.dealCard());
		dealer.receiveCard(deck.dealCard());
		
		cleanTerminal();
		
		playRounds();
		
		showResults();
	}
	
	public void playRounds() {
		
		for(Player player : players) {
			System.out.println(player.getName() + "'s turn:");
			System.out.println(tab + player.getName() + "'s hand: " + player.getHand());
			while(!player.isStanding() && !player.isBusted()) {
                System.out.println(tab + player.getName() + ", your current hand value is: " + player.handValue());
                System.out.println(tab + "Do you want to (1) Hit or (2) Stand?");
                int choice = scanner.nextInt();
                
                System.out.println(choice);
                
                if(choice == 1) {
                	player.receiveCard(deck.dealCard());
                    System.out.println(player.getName() + " received a new card.");
                    if(player.handValue() > 21) {
                        System.out.println(player.getName() + " has busted");
                        player.busted();
                    }
                } else {
                	player.stand();
                }	
			}
			scanner.nextLine();
			cleanTerminal();
		}

		if(!allPlayersBusted()) {
			System.out.print("Dealer's turn: \n\n" + tab);
			dealer.playTurn(deck);
		}
		
		scanner.nextLine();
		cleanTerminal();
	}
	
	public boolean allPlayersBusted() {
		for(Player player : players) {
			if(!player.isBusted())	return false;
		}
		return true;
	}
	
	private void showResults() {
	    int dealerValue = dealer.handValue();
	    
	    System.out.print("\tDealer's final hand value: " + dealerValue + "\n\n\n");

	    // Verifica se o dealer estourou
	    if (dealer.isBusted()) {
	        System.out.println("\tDealer busted! All players who didn't bust win.");
	        for (Player player : players) {
	            if (!player.isBusted()) {
	                System.out.println(tab + player.getName() + " wins with hand value: " + player.handValue());
	            } else {
	                System.out.println(tab + player.getName() + " busted and lost.");
	            }
	        }
	    } else {
	        // Verifica a pontuação dos jogadores em relação ao dealer
	        for (Player player : players) {
	            if (player.isBusted()) {
	                System.out.println(tab + player.getName() + " busted and lost.");
	            } else if (player.handValue() > dealerValue) {
	                System.out.println(tab + player.getName() + " wins with hand value: " + player.handValue());
	            } else if (player.handValue() == dealerValue) {
	                System.out.println(tab + player.getName() + " ties with the dealer at " + player.handValue());
	            } else {
	                System.out.println(tab + player.getName() + " loses with hand value: " + player.handValue());
	            }
	        }
	    }
	}
	
	public static void cleanTerminal() {
		for (int i=0; i<50; i++) {
			System.out.println();
		}
	}
}
