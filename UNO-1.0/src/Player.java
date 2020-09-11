/*
 * Player class to represent players in the game
 * 
 * @param name
 * Name of the Player
 * 
 * @param starting_card_count
 * Each player must start with 7 cards
 * 
 * @param hand
 * all the cards in hand
 * 
 * @param nextPlayerRotation
 * Is the rotation going clockwise or has there been a reverse card to change the direction of play
 * == True is going the way it began, false means it has reversed
 * GameState will update this for all players
 */
public class Player {
	String name;
	int card_count;
	Hand  playerHand;
	Card last_played_card_in_game;

	public Player(String player_name, Hand cards){
		name = player_name;
		card_count = playerHand.total_cards;
		playerHand = cards;
	}
	
	Card play_card() {
		
		return null;
	}
	
	//Add 1 to card count when an invalid card is drawn and cannot be played. The card should also be added to players hand
	//Subtract card count when a card is played to playable pile
	void update_card_count() {
		card_count = playerHand.total_cards;
	}
	
	//Drawn card is added to hand
	void add_card_to_hand(Card drawnCard) {
		this.playerHand.add_card_to_hand(drawnCard);
		update_card_count();
	}
	
	//When a card is played it must be removed from players hand
	void remove_card_from_hand(int cardIndex) {
		this.playerHand.remove_card_from_hand(cardIndex);
		update_card_count();
	}
	
	//if a valid card is played then it has to be played. If not, the player turn is skipped
	boolean is_draw_card_valid() {
		
		return true;
	}
	
	boolean is_chosen_card_valid(Card chosenCard) {
		//if(chosenCard.card_color == )
		return true;
	}
	
	//Check to see if the player has a valid card to play from the current color and number of the game state
	boolean has_valid_card() {
		return true;
	}
	
	//Player has choice to play a card or draw a new card even if there is a valid card to be played
	boolean play_card_or_draw_new_card() {
		return true;
	}
	
	
	
}