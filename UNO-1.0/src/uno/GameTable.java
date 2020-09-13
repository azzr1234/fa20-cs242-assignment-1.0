package uno;
import java.util.*;

public class GameTable {
	public PlayableDeck playableDeck;
	public DiscardDeck discardDeck;
	public GameState newGame;
	public ArrayList<Player> gamePlayers;
	public Actions playingActions;
	//public static GameTable table = null;
	public GameTable() {
		playableDeck = new PlayableDeck();
		discardDeck = new DiscardDeck();
		gamePlayers = create_players(2);
		newGame = new GameState(2, gamePlayers, playableDeck, discardDeck);
		playingActions = new Actions(newGame);
	}
	
	//Creates the hand for the players here
	public Hand create_hand_for_player() {
		Hand playerHand = new Hand();
		for(int startingHandCount = 0; startingHandCount < 7; startingHandCount++) {
			playerHand.insert_card_to_hand(playableDeck.pop());
		}
		return playerHand;
	}
	
	//Creates the players for the game here
	public ArrayList<Player> create_players(int numPlayers) {
		ArrayList<Player> gameMembers = new ArrayList<Player>();
		for(int playerIndex = 0; playerIndex < numPlayers; playerIndex++) {
			Hand playerHand = create_hand_for_player();
			gameMembers.add(new Player(playerIndex, playerHand, newGame, null));
		}
		return gameMembers;
	}
	
	//After gamestate creates players and gives hand. The Playable deck should give first card to discard
	//The first player needs to instantiated.
	public void start_game_set_up() {
		this.discardDeck.push(this.playableDeck.pop());
		this.newGame.currentPlayer = gamePlayers.get(0);
	}
	
}