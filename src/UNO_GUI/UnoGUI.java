package UNO_GUI;
import javax.swing.*;
import java.awt.*;
import java.io.IOException;

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
        /*for(int i = 0; i < 15; i++){
            System.out.println("\n");
        }*/
        System.out.println("The current card is: " + this.game.currentCard.getColor() + " " + this.game.currentCard.getValue());
        System.out.println("It is Player " + this.game.players.indexOf(game.currentPlayer) + "'s Turn To Play");
        //System.out.println();
    }

    public void printPlayersTurn(){
        Card recentCard = this.game.currentCard;
        System.out.println("Player " + this.game.players.indexOf(this.game.currentPlayer) + " Has Played " + recentCard.getColor() + " 7");
    }

    public void printWinner(){
        System.out.println("The UNO Game has ended! The Winner is ... Player 3!");
    }

    public void askForColorChoice(){
        System.out.println("Please choose the new color. Red, Green, Blue or Yellow ");
    }

    public void printChosenColor(){
        System.out.println("Player has chosen color: Blue" + " for the the Wild Card");
    }

    public void printPlayersOrder(){
        System.out.println("The current player is: " + this.game.players.indexOf(this.game.currentPlayer));
        System.out.println("The next player is: " + this.game.players.indexOf(this.game.nextPlayer));

    }

    public void printPlayedCard(){
        System.out.println("You have played Red DrawTwo");
    }

    public void noValidCardsDrawCards(){
        System.out.println("You do not have any valid cards to play");
        System.out.println("Drawing cards from draw deck...");
        System.out.println("You have drawn a valid card and has been played");
        System.out.println("Your turn has ended. Next Player turn.");
    }

    public void printCurrentPlayerCards(){
       System.out.println("Your Cards: ");
       int cardIndex = 0;
       for(Card currentCard: this.game.currentPlayer.playerHand.getHand()){
           System.out.println(cardIndex + ": " + currentCard.getColor() + " " + currentCard.getValue());
           cardIndex++;
       }

    }

    public void printPlayerOptions(){
        System.out.println("Would you like to utilize more than one valid card? If yes, seperate index values by a comma. Requirements: Card has to be the same " +
                "color or number value to be stacked");
    }

    public void printInvalid(){
        System.out.println("You Tried playing an invalid card. Please choose again by inputing the index of the card.");

    }

    public void printTurnEndNextPlayerTurn(){
        System.out.println("Your turn has ended. It is now Player 4's turn");
    }



}
