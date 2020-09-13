package uno;
import java.util.*;
/*
 * Class for UNO cards
 * @param card_color --> The color of the card is represented by an integer
 * --> Red = 0
 * --> Blue = 1
 * --> Green = 2
 * --> Yellow = 3
 * --> No Color = 4 == Wild Card/Wild Draw 4
 * @param card_val --> The number on the card; If the card value is a special card(Draw 2, reverse, Skip, Wild, Wild+4) It will have different values
 * --> 0-9 = 0-9
 * --> Skip Next = 10 
 * --> Reverse = 11
 * --> Draw 2 = 12
 * --> Wild Card = 13
 * --> Wild Card + 4 = 14
 */

public class Card {
	int card_color;
	int card_value;
	public Card() {};
	public Card(int color, int card_val) {
		card_color = color;
		card_value = card_val;
	};
	
	public enum Color {
        BLUE   { public String toString() { return "BLUE"; }},
        RED    { public String toString() { return "RED"; }},
        GREEN  { public String toString() { return "GREEN"; }},
        YELLOW { public String toString() { return "YELLOW"; }}
    }
	
	String color_to_string(Card card_to_convert) {
		int card_color_val = card_to_convert.card_color;
		String color_string = "";
		if(card_color_val == 0) {
			color_string = "Red";
		}else if(card_color_val == 1) {
			color_string =  "Blue";
		}else if(card_color_val == 2) {
			color_string = "Green";
		}else if(card_color_val == 3) {
			color_string = "Yellow";
		}
		return color_string;
	}
	
	String value_to_string(Card card_to_convert) {
		int card_value_int = card_to_convert.card_value;
		String value_string = "";
		if(card_value_int <= 9) {
			value_string = Integer.toString(card_value_int);
		}else if(card_value_int == 10) {
			value_string =  "Skip Card";
		}else if(card_value_int == 11) {
			value_string = "Reverse Card";
		}else if(card_value_int == 12) {
			value_string = "Draw 2 Card";
		}else if(card_value_int == 13) {
			value_string = "Wild Card";
		}else if(card_value_int == 14) {
			value_string = "Wild Draw 4 Card";
		}
		return value_string;
	}
}

class RegularCard extends Card{
	
	public RegularCard(int colorOfCard, int valueOfCard) {
		super(colorOfCard,valueOfCard);

	};
	
	 public enum Value {
	        ZERO    { public String toString() { return "0";}},
	        ONE     { public String toString() { return "1";}},
	        TWO     { public String toString() { return "2";}},
	        THREE   { public String toString() { return "3";}},
	        FOUR    { public String toString() { return "4";}},
	        FIVE    { public String toString() { return "5";}},
	        SIX     { public String toString() { return "6";}},
	        SEVEN   { public String toString() { return "7";}},
	        EIGHT   { public String toString() { return "8";}},
	        NINE    { public String toString() { return "9";}}
	    }
	 
	 
}




//Reverse the direction of play
class ReverseCard extends Card{
	public ReverseCard(int colorOfCard, int valueOfCard) {
		super(colorOfCard,valueOfCard);

	};
	public enum Value {
		ReverseCard { public String toString() {return "11";}}
	}
	
	void changeDirectionofPlay() {
		
	}
}


//The next person is skipped in turn
class SkipCard extends Card{
	public SkipCard(int colorOfCard, int valueOfCard) {
		super(colorOfCard,valueOfCard);
	};
	public enum Value {
		SkipCard { public String toString() {return "10";}}
	}
	
	void skipNextPlayer() {
		
	}
}

//The next person draws 2 cards from the playable deck and misses turn
class DrawTwoCard extends Card{
	public DrawTwoCard(int colorOfCard, int valueOfCard) {
		super(colorOfCard,valueOfCard);
	};
	
	public enum Value {
		DrawTwoCard { public String toString() {return "12";}}
	}
	
	void drawTwo(){
	
	}
 }

//New color is chosen by the player. New color can be the same as old color
class WildCard extends Card{
	public WildCard(int colorOfCard, int valueOfCard) {
		super(colorOfCard,valueOfCard);

	};
	
	public enum Value {
		WildCard { public String toString() {return "13";}}
	}
	
	void changeColor() {
		
	}
}

//New color is chosen by the player who places it on discard pile. The next player must also draw 4 from playable deck and miss a turn
//Bonus point: +0.5 if implement the following rule:
//“Wild Draw Four” card can only be played if the player has no cards matching the current color
class WildDrawFourCard extends Card{
	public WildDrawFourCard(int colorOfCard, int valueOfCard) {
		super(colorOfCard,valueOfCard);
	};
	
	public enum Value {
		WildDrawFourCard { public String toString() {return "14";}}
	}
	
	void changeColorPlusFour() {
		
	}
}


/*
 * There are two ways to make the cards for the deck. 
 * 1. Do all the cards by each color, so if red then make all the card types for red. if blue then make all the card types for blue and so on.
 * 2. Do all the cards by card type, so if wild card then make all the wild cards for each color, if reverse card then make all reverse cards for each color.
 * There needs to be 2 sets of 1-9 and 1 zero card for each color, which in total is 19 cards without special move cards
 * Each color also gets 2 reverse cards, 2 skip cards, 2 draw two cards, 2 wild card, 2 wild draw 4 card, which is an additional 8 cards.
 * This makes each color have a total of 27 cards
 */




















