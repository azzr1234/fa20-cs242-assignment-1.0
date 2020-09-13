package uno;
import java.util.ArrayList;
import java.util.Map;

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
	
	public void remove_card_from_hand(int cardIndex) {
		this.hand.remove(cardIndex);
		this.total_cards -= 1;
	}
	
	
	//show player hand
	public ArrayList<Card> convert_to_readable_hand(ArrayList<Card> hand){
		ArrayList<Card> hand_to_convert = this.getHand();
		//ArrayList<Card> hand_readable = new HashMap<Integer, String>();
		
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