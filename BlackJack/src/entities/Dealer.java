package entities;

import java.util.ArrayList;

public class Dealer {
	private ArrayList<Card> hand;
    private boolean isStanding;
    private boolean busted;
    private static final int dealer_threshold = 17;
	
	public Dealer() {
		hand = new ArrayList<>();
		isStanding = false;
		busted = false;
	}
	
	public ArrayList<Card> getHand() {
		return hand;
	}

	public void receiveCard(Card card) {
		hand.add(card);
	}

	public int handValue() {
		int total = 0;
		int aces = 0;
		for(Card card : hand)
		{
			String face = card.getFace();
			
			switch(face) {
				case "A":
					aces++;
					total+=11;
					break;
				case "J":
				case "Q":
				case "K":
					total += 10;
					break;
				default:
					total += Integer.parseInt(face);
					break;
			}
			
			while (total > 21 && aces>0) {
				total += 10;
				aces--;
			}
		}
		return total;
	}
	
	
	public boolean isStanding() {
		return isStanding;
	}
	
	public void stand() {
		isStanding = true;
	}
	
	public boolean isBusted() {
		return busted;
	}
	
	public void busted() {
		busted = true;
	}
	
	public void playTurn(DeckOfCards deck) {
		System.out.println("\tDealer's hand: " + hand);
		while(handValue() < dealer_threshold) {
			receiveCard(deck.dealCard());
            System.out.println("\n\tDealer hits and receives a card. \t Dealer's hand: " + hand);
		}
		
		if(handValue() > 21) {
			System.out.println("\n\nDealer's busted");
		} else {
            System.out.println("\n\nDealer stands with a hand value of: " + handValue());
		}
	}
	
	
}
