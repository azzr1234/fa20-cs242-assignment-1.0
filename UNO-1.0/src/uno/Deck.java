package uno;
import java.util.Collections;
import java.util.Stack;



class Deck extends Card {
	public Stack<Card> deck;
	public Deck() {
		deck = new Stack<Card>();
	}
	
	public void shuffleDeck(Stack<Card> d) {
		Collections.shuffle(d);
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
	
	public void shuffle_deck(Deck deck_to_shuffle) {
		Collections.shuffle(deck_to_shuffle.getDeck());
	}
}


class DiscardDeck extends Deck {
	public DiscardDeck() {
		super();
	}
}


class PlayableDeck extends Deck {
	//private Stack<Card> deck;

	public PlayableDeck() {
		super();

		//or
		
		createRegularCards(deck, 76); // 19 for each color * 4 for each color = 76 cards
		
		createReverseCards(deck, 8); // 2 for each color * 4 for each color = 8 cards
		
		createSkipCards(deck, 8); // 2 for each color * 4 for each color = 8 cards
		
		createDrawTwoCards(deck, 8);
		
		createWildCards(deck, 8); // 2 for each color * 4 for each color = 8 cards
		
		createWildDrawFourCards(deck, 8); // 2 for each color * 4 for each color = 8 cards
		
		shuffleDeck(deck);
		
	}
	
	public PlayableDeck(DiscardDeck d) {
		super();
		Card last_card_played = d.pop();
		for(int cardIndex = 0; cardIndex < d.getDeck().size(); cardIndex++) {
			deck.push(d.pop());
		}
		d.push(last_card_played);
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
	
	
}