package uno;
/*
 * Player class to represent players in the game
 * 
 * @param name
 * Name of the Player
 * 
 * @param starting_card_count
 * Each player must start with 7 cards
 * 
 * @param playerHand
 * all the cards in hand
 * 
 * @param last_played_card_in_game
 * The last card played in the game that is on the discard pile
 * 
 * @param currentGame
 * The game players are currently playing
 * 
 * @param turn_finished
 * true if is finished, false if not. This is for the game state to move on to the next player
 * 
 * @param nextPlayerRotation
 * Is the rotation going clockwise or has there been a reverse card to change the direction of play
 * == True is going the way it began, false means it has reversed
 * GameState will update this for all players
 * 
 * 
 */
public class Player {
	int player_id;
	int card_count;
	Hand  playerHand;
	Card last_played_card_in_game;
	GameState currentGame;
	Actions doSomething;


	public Player(int playerID, Hand cards, GameState game, Card last_played){
		player_id = playerID;
		card_count = 7;
		playerHand = cards;
		currentGame = game;
		last_played_card_in_game = last_played;
	}


	
	//Add 1 to card count when an invalid card is drawn and cannot be played. The card should also be added to players hand
	//Subtract card count when a card is played to playable pile
	public void update_card_count() {
		this.card_count = playerHand.total_cards;
	}
	
	
	//When a card is played it must be removed from players hand
	public void remove_card(int cardIndex) {
		this.playerHand.remove_card_from_hand(cardIndex);
		update_card_count();
	}
	
	
	public boolean is_chosen_card_valid(int cardIndex) {		
		return this.playerHand.check_for_valid_card(this.last_played_card_in_game).contains(cardIndex);
	}
	
	
	//Player has choice to play a card or draw a new card even if there is a valid card to be played
	public boolean play_card_or_skip_turn() {
		
		return true;
	}
	
	
	
}