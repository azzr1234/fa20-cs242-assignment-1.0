package uno;
import java.util.*;

public class GameTable {
	public PlayableDeck playableDeck;
	public DiscardDeck discardDeck;
	public GameState newGame;
	
	public static GameTable table = null;
	public GameTable() {
		playableDeck = new PlayableDeck();
		discardDeck = new DiscardDeck();
		ArrayList<Player> gamePlayers = create_players(2);
		newGame = new GameState(2, gamePlayers);
	}
	
	public Hand create_hand_for_player() {
		Hand playerHand = new Hand();
		for(int startingHandCount = 0; startingHandCount < 7; startingHandCount++) {
			playerHand.insert_card_to_hand(playableDeck.pop());
		}
		return playerHand;
	}
	
	public ArrayList<Player> create_players(int numPlayers) {
		ArrayList<Player> gameMembers = new ArrayList<Player>();
		for(int playerIndex = 0; playerIndex < numPlayers; playerIndex++) {
			Hand playerHand = create_hand_for_player();
			gameMembers.add(new Player(playerIndex, playerHand, newGame, null));
		}
		return gameMembers;
	}
}