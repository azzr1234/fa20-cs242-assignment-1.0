import java.io.*;
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
}

// Reverse the direction of play
class ReverseCard extends Card{
	public ReverseCard(int colorOfCard, int valueOfCard) {
		super(colorOfCard,valueOfCard);

	};
	void changeDirectionofPlay() {
		
	}
}

//The next person is skipped in turn
class SkipCard extends Card{
	public SkipCard(int colorOfCard, int valueOfCard) {
		super(colorOfCard,valueOfCard);
	};
	void skipNextPlayer() {
		
	}
}

//The next person draws 2 cards from the playable deck and misses turn
class DrawTwoCard extends Card{
	public DrawTwoCard(int colorOfCard, int valueOfCard) {
		super(colorOfCard,valueOfCard);
	};
	void drawTwo(){
	
	}
 }

//New color is chosen by the player. New color can be the same as old color
class WildCard extends Card{
	public WildCard(int colorOfCard, int valueOfCard) {
		super(colorOfCard,valueOfCard);

	};
	void changeColor() {
		
	}
}

//New color is chosen by the player who places it on discard pile. The next player must also draw 4 from playable deck and miss a turn
//Bonus point: +0.5 if implement the following rule:
//�Wild Draw Four� card can only be played if the player has no cards matching the current color
class WildDrawFourCard extends Card{
	public WildDrawFourCard(int colorOfCard, int valueOfCard) {
		super(colorOfCard,valueOfCard);
	};
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

class Deck extends Card {
	private Stack<Card> deck;

	public Deck() {
		deck = new Stack<Card>();

		//or
		
		createRegularCards(deck, 76); // 19 for each color * 4 for each color = 76 cards
		
		createReverseCards(deck, 8); // 2 for each color * 4 for each color = 8 cards
		
		createSkipCards(deck, 8); // 2 for each color * 4 for each color = 8 cards
		
		createDrawTwoCards(deck, 8);
		
		createWildCards(deck, 8); // 2 for each color * 4 for each color = 8 cards
		
		createWildDrawFourCards(deck, 8); // 2 for each color * 4 for each color = 8 cards
		
	}
	
	
	public void createReverseCards(Stack<Card> d, int count) {
		for (int colorofCard = 0; colorofCard < 4; colorofCard++) {
			for (int numReverseCards = 0; numReverseCards < count / 4; numReverseCards++) {
				ReverseCard revCard = new ReverseCard(colorofCard, 10);
				d.push(revCard);
			}
		}
	}
	
	public void createSkipCards(Stack<Card> d, int count) {
		for (int colorofCard = 0; colorofCard < 4; colorofCard++) {
			for (int numSkipCards = 0; numSkipCards < count / 4; numSkipCards++) {
				SkipCard skipCard = new SkipCard(colorofCard, 10);
				d.push(skipCard);
			}
		}
	}
	
	public void createDrawTwoCards(Stack<Card> d, int count) {
		for (int colorofCard = 0; colorofCard < 4; colorofCard++) {
			for (int numSkipCards = 0; numSkipCards < count / 4 ; numSkipCards++) {
				DrawTwoCard skipCard = new DrawTwoCard(colorofCard, 12);
				d.push(skipCard);
			}
		}
	}
	
	public void createWildCards(Stack<Card> d, int count) {
		for (int numWildCards = 0; numWildCards < count; numWildCards++ ) {
			WildCard wildCard = new WildCard(4, 13);
			d.push(wildCard);
		}
	}
	
	public void createWildDrawFourCards(Stack<Card> d, int count) {
		for (int numWild4Cards = 0; numWild4Cards < count; numWild4Cards++) {
			WildDrawFourCard wild4Card = new WildDrawFourCard(4,13);
			d.push(wild4Card);
		}
	}
	
	public void createRegularCards(Stack<Card> d, int num_cards_needed) {
		for (int colorofCard = 1; colorofCard < 5; colorofCard++) {
			for (int numRegCards = 1; numRegCards <= 19; numRegCards++ ) {
				Card regCard = new Card(colorofCard, numRegCards % 10);
				d.push(regCard);
			}
		}

	}
	
	public Stack<Card> getDeck(){
		return this.deck;
	}
	
	public Card peek() {
		return this.getDeck().peek();
	}
	
	public Card pop() {
		return this.getDeck().pop();
	}
	
	public void push(Card card_to_push) {
		this.getDeck().push(card_to_push);
	}
}


class Hand extends Card {
	Map<Integer, Card> hand;

	int total_cards;
	public Hand() {
		hand = new HashMap<Integer, Card>();
		total_cards = hand.size();
	}
	
	
	public Map<Integer, Card> getHand() {
		return this.hand;
	}
	//add a card to player hand
	public void insert_card_to_hand(Card newCard) {
		hand.put(total_cards, newCard);
		this.total_cards += 1;
	}
	
	public void remove_card_from_hand(int cardIndex) {
		this.hand.remove(cardIndex);
		this.total_cards -= 1;
	}
	
	
	//show player hand
	public Map<Integer, String> convert_to_readable_hand(Map<Integer, Card> hand){
		Map<Integer, Card> hand_to_convert = this.getHand();
		Map<Integer, String> hand_readable = new HashMap<Integer, String>();
		for (Map.Entry<Integer, Card> entry : hand_to_convert.entrySet()) {
		    Integer cardIndex = entry.getKey();
		    Card cardValue = entry.getValue();
		    hand_readable.put(cardIndex, color_to_string(cardValue) + value_to_string(cardValue));
		}
		return hand_readable;
	}
	
	public ArrayList<Integer> check_for_valid_card(Card card_to_compare) {
		ArrayList<Integer> allValidCards = new ArrayList<Integer>();
		Map<Integer, Card> handToCheck = this.getHand();
		int color_to_check = card_to_compare.card_color;
		int value_to_check = card_to_compare.card_value;
		
		for (Map.Entry<Integer, Card> entry : handToCheck.entrySet()) {
		    Integer cardIndex = entry.getKey();
		    Card cardValue = entry.getValue();
		    if(cardValue.card_color == color_to_check || cardValue.card_value == value_to_check) {
		    	allValidCards.add(cardIndex);		    }
		}
		return allValidCards;
	}
	
	
}















