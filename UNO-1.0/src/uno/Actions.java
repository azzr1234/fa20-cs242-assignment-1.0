package uno;

public class Actions  {
	GameState currentGame;
	public Actions(GameState game) {
		currentGame = game;
	};
	
	
	public void reverse_card_Played() {
		this.currentGame.current_direction *= -1;
	}
	
	public void skip_card_Played() {
		this.currentGame.nextPlayer = null;
	}
	
	public void draw_two_card_played() {
		
	}
	
	public void wild_card_played() {
		
	}
	
	public void wild_draw_4_played() {
		
	}

	public void create_and_distribute_cards() {
		
	}
	
	public Deck create_playableDeck() {
		return null;
	}
	
	public void next_player() {
		if(currentGame.current_direction == 1) {
			
		}else if(currentGame.current_direction == -1) {
			
		}
	}
	

	
	
	//Shuffle the discard pile to be used as the playable deck when the playable deck runs out
	
	
	//Replace the playable deck with the shuffled discard pile
	public PlayableDeck discard_to_playable(DiscardDeck previous_discard_deck) {
		previous_discard_deck.shuffle_deck(previous_discard_deck);
		return new PlayableDeck(previous_discard_deck);
	}
	
	//Any time a card is taken from the deck, it need to be popped and into the players hand
	public void subtract_count_from_playable_deck() {
		this.currentGame.playableDeckCount -= 1;
		//check if the playableDeck is empty, if yes, then make discard deck new playable deck
		if(this.currentGame.playableDeckCount == 0) {
			this.currentGame.playableDeck = discard_to_playable(this.currentGame.discardDeck);
		}
	}
	
	//playable deck should be updated after a card is drawn. card should be taken out. count should be decreased
	public Card pull_from_playable_deck() {
		Card current = this.currentGame.playableDeck.pop();
		this.subtract_count_from_playable_deck();
		return current;
	}
	
	//discard deck should be updated after a card is added. card should be added. count should be increased
	public void add_to_discard_deck_and_update_current_card(Card card_to_add) {
		this.currentGame.discardDeck.push(card_to_add);
		this.currentGame.update_current_color_and_number();
	}
}