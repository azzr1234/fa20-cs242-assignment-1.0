package uno;

public class PatternDemo {
	public static void main(String[] args) {
		
		GameTable table = new GameTable();
		
		table.start_game_set_up();
		
		Player firstPlayer = table.playingActions.getFirstPlayer();
		firstPlayer.doSomething.play_turn();
		
		Player next = table.playingActions.rotate_turn();
		next.doSomething.play_turn();
		
		return;
	}
}