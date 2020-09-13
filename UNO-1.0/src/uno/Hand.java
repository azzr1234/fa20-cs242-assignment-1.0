package uno;
import java.util.ArrayList;


class Hand extends Card {
	ArrayList<Card> hand;
	//Map<Integer, Card> hand;

	int total_cards;
	public Hand() {
		hand = new ArrayList<Card>();
		total_cards = hand.size();
	}
	
	
	public ArrayList<Card> getHand() {
		return this.hand;
	}
	//add a card to player hand
	public void insert_card_to_hand(Card newCard) {
		this.hand.add(total_cards, newCard);
		this.total_cards += 1;
	}
	
	public Card remove_card_from_hand(int cardIndex) {
		Card playCard = this.hand.remove(cardIndex);
		this.total_cards -= 1;
		return playCard;
	}
	
	
	//show player hand
	public ArrayList<String> convert_to_readable_hand(ArrayList<Card> hand){
		ArrayList<Card> handToConvert = this.getHand();
		ArrayList<String> hand_readable = new ArrayList<String>();
		
		for(int cardIndex = 0; cardIndex < handToConvert.size(); cardIndex++) {
			hand_readable.add(color_to_string(handToConvert.get(cardIndex)) + value_to_string(handToConvert.get(cardIndex)));
		
		}
		
		return hand_readable;
	}
	
	//When checking for valid cards, it returns all valid card in a arraylist to player
	public ArrayList<Integer> check_for_valid_card(Card card_to_compare) {
		ArrayList<Integer> allValidCards = new ArrayList<Integer>();
		ArrayList<Card> handToCheck = this.getHand();
		int color_to_check = card_to_compare.card_color;
		int value_to_check = card_to_compare.card_value;
		
		for(int cardIndex = 0; cardIndex < handToCheck.size(); cardIndex++) {
			if(handToCheck.get(cardIndex).card_color == color_to_check || handToCheck.get(cardIndex).card_color == value_to_check) {
				allValidCards.add(cardIndex);
			}
		}
		
		return allValidCards;
	}
	
	
}