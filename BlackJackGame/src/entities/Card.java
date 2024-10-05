package entities;

public class Card 
{
   private final String face; // face of card ("1", "2", ...)
   private final String suit; // suit of card ("Hearts", "Diamonds", ...)

   // two-argument constructor initializes card's face and suit
   public Card(String face, String suit)
   {
      this.face = face;
      this.suit = suit; 
   } 

   // return String representation of Card
   public String getFace() 
   { 
      return face;
   } 
   
   public String getSuitSymbol() {
	   switch(suit.toLowerCase()) {
	   		case "hearts":
	   			return "\u2665"; // ♥
	   			
	   		case "diamonds":
                return "\u2666"; // ♦
                
            case "clubs":
                return "\u2663"; // ♣
                
            case "spades":
                return "\u2660"; // ♠
                
            default:
                return "?";
	   }
   }
   
   public String printCard() {
       String suitSymbol = getSuitSymbol();
       String faceValue = face.length() == 1 ? face + " " : face; // to ensure proper spacing

       return String.format(
           "┌─────────┐\n" +
           "│ %2s      │\n" +   // Top-left face and symbol
           "│         │\n" +
           "│    %s    │\n" +    // Center symbol
           "│         │\n" +
           "│      %-2s │\n" +   // Bottom-right face and symbol
           "└─────────┘", 
           faceValue, suitSymbol, faceValue);
   }
   
   public String toString() {
       return face + getSuitSymbol();
   }
   
} // end class Card