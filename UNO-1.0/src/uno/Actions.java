package uno;


public class Actions  {
	GameState currentGame;
	public Actions(GameState game) {
		currentGame = game;
	};
	
	public Player getFirstPlayer() {
		this.currentGame.currentPlayer = this.currentGame.players.get(0);
		return this.currentGame.currentPlayer;
	}
	
	public void skip_player() {
		determine_nextPlayer();
		rotate_turn();
	}
	
	
	//switch value to opposite in game state to signify reverse. 
	public void reverse_card_Played() {
		this.currentGame.current_direction *= -1;
		determine_nextPlayer();
		rotate_turn();
	}
	
	//call determine_nextPlayer twice to set next player as second away from current player and skip the following person
	public void skip_card_Played() {
		determine_nextPlayer();
		determine_nextPlayer();
		rotate_turn();
	}
	
	//Draw 2 into the next persons hand, then skip their turn
	public void draw_two_card_played() {
		this.currentGame.nextPlayer.playerHand.insert_card_to_hand(this.currentGame.playableDeck.pop());
		this.currentGame.nextPlayer.playerHand.insert_card_to_hand(this.currentGame.playableDeck.pop());
		skip_player();
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
	
	//Checks current_direction, which is changed by a reverse card and determines which way to rotate
	public void determine_nextPlayer() {
		if(this.currentGame.current_direction == 1) {
			if(this.currentGame.players.indexOf(this.currentGame.currentPlayer) + 1 == this.currentGame.players.size()) {
				this.currentGame.nextPlayer = this.currentGame.players.get(0);
			}else {
				this.currentGame.nextPlayer = this.currentGame.players.get(this.currentGame.players.indexOf(this.currentGame.currentPlayer) + 1);
			}
		}else if(this.currentGame.current_direction == -1) {
			if(this.currentGame.players.indexOf(this.currentGame.currentPlayer) - 1 < 0) {
				this.currentGame.nextPlayer = this.currentGame.players.get(this.currentGame.players.size() - 1);
			}else {
				this.currentGame.nextPlayer = this.currentGame.players.get(this.currentGame.players.indexOf(this.currentGame.currentPlayer) - 1);
			}
		}
	}
	
	public Player rotate_turn() {
		this.currentGame.currentPlayer = this.currentGame.nextPlayer;
		return this.currentGame.currentPlayer;
	}
	
	
	
	public Card play_turn() {
		if(this.has_valid_card()) {
			//player chose card
			int index = 0; //chosen index
			//System.out.println("Please choose card to play by selecting index of card with the first card being index 0");
			this.currentGame.currentPlayer.remove_card_from_hand(index);
		}else {
			this.draw_card_from_playable_deck();
		}
		return null;
	}
	
	//Check to see if the player has a valid card to play from the current color and number of the game state
	//check_for_valid_card returns an array of all valid cards, so it checks if empty
	public boolean has_valid_card() {
		return this.currentGame.currentPlayer.playerHand.check_for_valid_card(this.currentGame.currentPlayer.last_played_card_in_game).isEmpty();
	}
	
	//if a valid card is played then it has to be played. If not, the player turn is skipped
	public boolean is_draw_card_valid(Card cardToCheck) {
		if(this.currentGame.currentPlayer.last_played_card_in_game.card_color == cardToCheck.card_color 
				|| this.currentGame.currentPlayer.last_played_card_in_game.card_value == cardToCheck.card_value) {
			return true;
		}
		return false;
	}
	
	//Drawn card is added to hand
	public void add_card_to_hand(Card drawnCard) {
		this.currentGame.currentPlayer.playerHand.insert_card_to_hand(drawnCard);
		this.currentGame.currentPlayer.update_card_count();
	}

	public void draw_card_from_playable_deck() {
		Card pulledCard = this.pull_from_playable_deck();
		if(is_draw_card_valid(pulledCard)) {
			this.currentGame.currentPlayer.play_card_or_skip_turn();
		}else {
			this.add_card_to_hand(pulledCard);
			
		}
	}

	
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
