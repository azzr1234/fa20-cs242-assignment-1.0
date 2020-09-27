package UNO;
import UNO_GUI.*;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class GamePlay {
    public GamePlay(){};

    public int getNumPlayers(){
        String num_input = "";
        BufferedReader rd = new BufferedReader(new InputStreamReader(System.in));
        try {
            num_input = rd.readLine();
        } catch (IOException e){
            e.printStackTrace();
        }
        int numPlayers = Integer.parseInt(num_input);
        return numPlayers;
    }

    public static void main(String[] args){
        System.out.println("Welcome to UNO! Please input the number of Players with an integer greater than 2");
        GamePlay play = new GamePlay();
        GameState game =  new GameState(play.getNumPlayers());
        UnoGUI gui = new UnoGUI(game);
        while(true){
            gui.printPlayersOrder();
            gui.printCurrentGameState();
            gui.printCurrentPlayerCards();
            game.playTurn(game.currentPlayer);
            if(game.checkForWinner() != null){
                break;
            }
        }
        System.out.println("Winner is Player " + game.players.indexOf(game.checkForWinner()));
    }
}
