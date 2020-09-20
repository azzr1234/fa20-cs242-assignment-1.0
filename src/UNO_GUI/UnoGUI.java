package UNO_GUI;
import javax.swing.*;
import java.awt.*;
import java.io.IOException;

import UNO.*;

public class UnoGUI {
    public static GameState game = new GameState(3);


    public static void printStartingWelcome(){
        System.out.println("Welcome To UNO!");
        System.out.println("Please input the number(integer) of players! Must be larger than 2");
    }

    public static void printCurrentGameState(){
        for(int i = 0; i < 15; i++){
            System.out.println("\n");
        }
        System.out.println("The current card is: " + game.currentCard.getColor() + " " + game.currentCard.getValue());
        System.out.println("It is Player " + game.players.indexOf(game.currentPlayer) + "'s Turn To Play");
        //System.out.println();
    }

    public static void printPlayersTurn(){
        Card recentCard = game.currentCard;
        System.out.println("Player " + game.players.indexOf(game.currentPlayer) + " Has Played " + recentCard.getColor() + " 7");
    }

    public static void printWinner(){
        System.out.println("The UNO Game has ended! The Winner is ... Player 3!");
    }

    public static void askForColorChoice(){
        System.out.println("Please choose the new color. Red, Green, Blue or Yellow ");
    }

    public static void printChosenColor(){
        System.out.println("Player has chosen color: Blue" + " for the the Wild Card");
    }

    public static void printPlayersOrder(){
        System.out.println("The current player is: " + game.players.indexOf(game.currentPlayer));
        System.out.println("The next player is: " + game.players.indexOf(game.nextPlayer));

    }

    public static void printPlayedCard(){
        System.out.println("You have played Red DrawTwo");
    }

    public static void noValidCardsDrawCards(){
        System.out.println("You do not have any valid cards to play");
        System.out.println("Drawing cards from draw deck...");
        System.out.println("You have drawn a valid card and has been played");
        System.out.println("Your turn has ended. Next Player turn.");
    }

    public static void printCurrentPlayerCards(){
       Player newPlayer = new Player();
       newPlayer.playerHand.createHand(game.drawDeck, 7);
       System.out.println("Your Cards: ");
       int cardIndex = 1;
       for(Card currentCard: newPlayer.playerHand.getHand()){
           System.out.println(cardIndex + ": " + currentCard.getColor() + " " + currentCard.getValue());
           cardIndex++;
       }

    }

    public static void printPlayerOptions(){
        System.out.println("Would you like to utilize more than one valid card? If yes, seperate index values by a comma. Requirements: Card has to be the same " +
                "color or number value to be stacked");
    }

    public static void printInvalid(){
        System.out.println("You Tried playing an invalid card. Please choose again by inputing the index of the card.");

    }

    public static void printTurnEndNextPlayerTurn(){
        System.out.println("Your turn has ended. It is now Player 4's turn");
    }

    public static void main(String[] args){

        printPlayedCard();
        printTurnEndNextPlayerTurn();

    }


}
