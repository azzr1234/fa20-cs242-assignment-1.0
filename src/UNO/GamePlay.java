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
        boolean firstTurn = true;
        UnoGUI gui = new UnoGUI(game);
        while(true){
            gui.printPlayersOrder();
            if(!firstTurn){
                System.out.println("Previous player has played "+
                        game.currentCard.getColor() + " " + game.currentCard.getValue());
            }
            if(game.currentPlayer == game.players.get(0)){
                System.out.println("Base line AI turn to play. Press Enter to Acknowledge");

                gui.acknowledgeChoice();
                game.baseAIPlayer.playTurn();
                if(game.checkForWinner() != null){
                    break;
                }
            }else if ( game.currentPlayer == game.players.get(1)){
                System.out.println("Strategic AI turn to play. Press Enter to Acknowledge");
                gui.acknowledgeChoice();
                game.stratAIPlayer.playTurn();
                if(game.checkForWinner() != null){
                    break;
                }
            }else{
                gui.printCurrentGameState();
                gui.printCurrentPlayerCards();
                game.playTurn(game.currentPlayer);
                if(game.checkForWinner() != null){
                    break;
                }
            }

            firstTurn = false;
        }
        System.out.println("Winner is Player " + game.players.indexOf(game.checkForWinner()));
    }
}
