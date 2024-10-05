package entities;

import java.util.ArrayList;


public class Player {
	private String name;
	private ArrayList<Card> hand;
	private boolean isStanding;
	private boolean busted;
	
	public Player(String name) {
		this.name = name;
		hand = new ArrayList<>();
		isStanding = false;
		busted = false;
	}
	
	public String getName() {
		return name;
	}
	
	public ArrayList<Card> getHand() {
		return hand;
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
	
	public void receiveCard(Card card) {
		hand.add(card);
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
	
	public void printCards() {
		for(int i=0; i < hand.size(); i++) {
			System.out.printf(
				"┌─────────┐\n" +
				"│ %2s      │\n" +
				"|┌─────────┐\n" +
				"|│ %2s      │\n" +
				"|│         │\n" +
				"|│    %s    │\n" +
				"└|         │\n" +
				" │      %-2s │\n" +
				" └─────────┘\n",
				hand.get(i).getFace(), hand.get(i+1).getFace(), hand.get(i+1).getSuitSymbol(), hand.get(i+1).getFace() );
			i++;
		}	
	}
}
