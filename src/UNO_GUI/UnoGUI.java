package UNO_GUI;
import javax.swing.*;
import java.awt.*;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import UNO.*;

public class UnoGUI {
    //public static GameState game = new GameState(3);
    GameState game;
    public UnoGUI(GameState game){
        this.game = game;
    };

    public void printStartingWelcome(){
        System.out.println("Welcome To UNO!");
        System.out.println("Please input the number(integer) of players! Must be larger than 2");
    }

    public void printCurrentGameState(){

        //System.out.println("It is Player " + this.game.players.indexOf(game.currentPlayer) + "'s Turn To Play");
        System.out.println("The current card is: " + this.game.currentCard.getColor() + " " + this.game.currentCard.getValue());
        String direction = "";
        if(this.game.directionClockwise == true){
            direction = "Clockwise";
        }else{
            direction = "Counter Clockwise";
        }
        System.out.println("The game rotation is: " + direction);
        System.out.println("Please enter any key, and then enter, if you are ready to see your cards.");
        acknowledgeChoice();

        //System.out.println();
    }

    public void acknowledgeChoice(){
        BufferedReader rd = new BufferedReader(new InputStreamReader(System.in));
        String input = "";
        try {
            input = rd.readLine();
        } catch (IOException e){
            e.printStackTrace();
        }
    }



    public void printPlayersTurn(){
        Card recentCard = this.game.currentCard;
        System.out.println("Player " + this.game.players.indexOf(this.game.currentPlayer) + " Has Played " + recentCard.getColor() + recentCard.getValue());
    }

    public void printWinner(){
        System.out.println("The UNO Game has ended! The Winner is ... Player 3!");
    }


    public void printPlayersOrder(){
        for(int i = 0; i < 15; i++){
            System.out.println("\n");
        }
        System.out.println("The current player is: Player " + this.game.players.indexOf(this.game.currentPlayer));
        System.out.println("The next player is: Player " + this.game.players.indexOf(this.game.nextPlayer));
        displayUno();

    }

    public void printPlayedCard(){
        System.out.println("You have played Red DrawTwo");
    }

    public void noValidCardsDrawCards(){
        System.out.println("You do not have any valid cards to play");
        System.out.println("Press any key and then enter to acknowledge you will be drawing cards until a valid card is played." +
                "Your turn will finish after a valid card is played.");
        BufferedReader rd = new BufferedReader(new InputStreamReader(System.in));
        String input = "";
        try {
            input = rd.readLine();
        } catch (IOException e){
            e.printStackTrace();
        }
        System.out.println("Drawing cards from draw deck...");
        System.out.println("You have drawn a valid card and has been played");
        System.out.println("Your turn has ended. Next Player's turn.");
    }

    public void printCurrentPlayerCards(){
       System.out.println("Your Cards: ");
       int cardIndex = 0;
       for(Card currentCard: this.game.currentPlayer.playerHand.getHand()){
           System.out.println(cardIndex + ": " + currentCard.getColor() + " " + currentCard.getValue());
           cardIndex++;
       }

    }

   public void displayUno(){
        if(this.game.unoSaid){
            System.out.println("Player has Uno: " + this.game.players.indexOf(this.game.currentPlayer));
        }
        System.out.println("Player has Uno: None");
   }


}
