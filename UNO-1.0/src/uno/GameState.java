package uno;
import java.util.*;
/*
 * Class representing the state of the game. Inherits Player and Card class
 * 
 * @param totalPlayers
 * The total # of players in the game.
 * 
 * @param current_direction
 * Keeps track of the direction the game is flowing. This variable changes if a reverse card is ever used.
 * 1 means clockwise; -1 means counter clockwise
 * 
 * @param closest_wineer 
 * This is the player with the least amount of cards, closest to winning at any given time.
 * 
 * @param current_color
 * This is the color the next card should match. Any card played next much match this card, or the number, or a wild/wilddraw4 card must be played
 * 
 * @param current_number
 * This is the number the next card should match, if not, it must be the same color as the card or a wild/wilddraw4 card must be played
 * 
 * @param playableDeck
 * This is the deck that will be used to pull cards from if needed. This deck is the left over after distributing 7 cards to each player
 * 
 * @param discardDeck
 * This is the deck the players will put their cards into and later reused and shuffled to replace playableDeck if the playableDeck is empty
 * 
 * @param playableDeckCount
 * The count of how many cards are available in the playable deck
 * 
 * @param discardDeckCount
 * The count of how many cards are in the discard deck 
 *  
 */
public class GameState {
	int totalPlayers;
	int current_direction;
	
	int closest_winner;
	int current_color;
	int current_number;
	
	PlayableDeck playableDeck;
	DiscardDeck discardDeck;
	int playableDeckCount;

	ArrayList<Player> players;
	Player currentPlayer;
	Player nextPlayer;
	
	public GameState(int total_players, ArrayList<Player> gamePlayers ){
		players = gamePlayers;
		
		totalPlayers = total_players;
		
	}
	
	public void nextPlayer() {
		
	}
	
	public void current_leader() {
		
		ListIterator<Player> listIterator = players.listIterator();
		int numOfCards = listIterator.next().card_count;
		while (listIterator.hasNext()) {
			if(listIterator.next().card_count <= numOfCards) {
				closest_winner = listIterator.next().player_id;
			}
		}
	}
	
	//If a wild card is played, it should alert players or new color
	public void alert_new_color_and_card_num_value() {
		ListIterator<Player> listIterator = players.listIterator();
		while (listIterator.hasNext()) {
			listIterator.next().last_played_card_in_game = discardDeck.peek();
		}
	}
	
	//When a card is played it should display the card to all other players
	public void display_most_recent_card() {
		Card currentCard = this.discardDeck.peek();
		String curr_color = currentCard.color_to_string(currentCard);
		String curr_value = currentCard.value_to_string(currentCard);
		System.out.println("Most Recent Card:" + curr_color + " " + curr_value);
	}
	
	//GameState needs to update current color and number after each new played card
	public void update_current_color_and_number() {
		this.current_color = this.discardDeck.peek().card_color;
		this.current_number = this.discardDeck.peek().card_value;
	}
	
	

}