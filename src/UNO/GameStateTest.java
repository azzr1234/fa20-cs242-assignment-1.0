package UNO;

import org.ietf.jgss.GSSManager;
import org.junit.jupiter.api.Test;

import javax.rmi.ssl.SslRMIClientSocketFactory;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Scanner;

import static org.junit.jupiter.api.Assertions.*;

class GameStateTest {

    @Test
    void createPlayers() {
        GameState newGame = new GameState(8);

        assertEquals(newGame.players.size(), 8);
        for(int playerIndex = 0; playerIndex < 7; playerIndex++){
            assertEquals(7, newGame.players.get(playerIndex).playerHand.getHand().size());
        }
    }

    @Test
    void initDecks() {
        GameState newGame = new GameState(8);
        newGame.initDecks();

        assertNotEquals(newGame.discardDeck.peek().getValue(), "WildCard");
        assertNotEquals(newGame.discardDeck.peek().getValue(), "WildDrawFourCard");
    }

    @Test
    void playTurn() {
        GameState game = new GameState(8);
        game.initDecks();

        game.playTurn(game.currentPlayer, false);

        switch (game.currentCard.getValue()) {
            case "Reverse":
                System.out.println("Reverse");
                assertEquals(false, game.directionClockwise);
                assertEquals(game.players.get(game.players.size() - 1), game.nextPlayer);
                break;
            case "Skip":
                System.out.println("Skip");
                assertEquals(game.players.get(2), game.nextPlayer);
                break;
            case "DrawTwo":
                System.out.println("DrawTwo");
                assertEquals(game.players.get(2), game.nextPlayer);
                assertEquals(9,game.players.get(1).cardCount);
                break;
            case "WildCard":
                System.out.println("WildCard");
                assertEquals("WildCard",game.currentCard.getValue());
                break;
            case "WildDrawFourCard":
                System.out.println("WildDrawFourCard");
                assertEquals(game.players.get(2), game.nextPlayer);
                assertEquals("WildDrawFourCard",game.currentCard.getValue());
                break;
        }


    }

    @Test
    void playCard() {
        GameState game = new GameState(8);
        game.initDecks();

        for(int playerIndex = 0; playerIndex < game.players.size(); playerIndex++){
            assertEquals(7, game.players.get(playerIndex).cardCount);
        }



        ArrayList<Integer> validCards = game.hasAnyValidCardCheck(game.currentPlayer);
        while(validCards.isEmpty()){
            validCards = game.hasAnyValidCardCheck(game.nextPlayer);
            game.currentPlayer = game.nextPlayer;
            game.determineNextPlayer();
        }
        Card topCard = game.discardDeck.peek();

        game.playCard(game.currentPlayer, validCards);

        assertTrue(topCard.getColor() == game.discardDeck.peek().getColor() ||
                topCard.getValue() == game.discardDeck.peek().getValue());
        assertEquals(6, game.currentPlayer.playerHand.getHand().size());

    }

    @Test
    void playTwoCards(){
        GameState game = new GameState(8);
        game.initDecks();
        ArrayList<Integer> validCards = game.hasAnyValidCardCheck(game.currentPlayer);
        Card topCard = game.discardDeck.peek();
        if(validCards.size() >=2){
            game.playTwoCards(game.currentPlayer, validCards);
            Card secondCardPlayed = game.discardDeck.pop();
            Card firstCardPlayed = game.discardDeck.peek();
            assertTrue(secondCardPlayed.getColor() == topCard.getColor() ||
                    secondCardPlayed.getValue() == topCard.getValue());
            assertTrue(firstCardPlayed.getColor() == topCard.getColor() ||
                    firstCardPlayed.getValue() == topCard.getValue());
            assertTrue(firstCardPlayed.getColor() == secondCardPlayed.getColor() ||
                    firstCardPlayed.getValue() == secondCardPlayed.getValue());
        }

    }

    @Test
    void checkforWinner(){
        GameState game = new GameState(8);

        while(!game.currentPlayer.playerHand.getHand().isEmpty()){
            game.currentPlayer.playerHand.getHand().remove(game.currentPlayer.playerHand.getHand().size() - 1);
        }
        game.currentPlayer.updateCardCount();
        Player winner = game.checkForWinner();
        assertEquals(game.currentPlayer, winner);
    }


    @Test
    void determineNextPlayer() {
        GameState newGame = new GameState(8);
        newGame.determineNextPlayer();
        assertEquals( newGame.players.get(1), newGame.nextPlayer);

        newGame.reverseDirection();
        newGame.determineNextPlayer();
        assertEquals(newGame.nextPlayer, newGame.players.get(newGame.players.size() - 1));

        newGame.currentPlayer = newGame.players.get(newGame.players.size() -1);
        newGame.determineNextPlayer();
        assertEquals(newGame.nextPlayer, newGame.players.get(newGame.players.size() - 2));

        newGame.reverseDirection();
        newGame.determineNextPlayer();
        assertEquals(newGame.nextPlayer, newGame.players.get(0));
    }

    @Test
    void hasAnyValidCardCheck() {
        GameState game = new GameState(8);
        game.initDecks();
        game.discardDeck.push(new Card("Blue", "9"));
        Card topCard = game.discardDeck.peek();
        ArrayList<Integer> validCards = new ArrayList<>();
        //Player newPlayer = new Player();
       // newPlayer.playerHand.getHand().add(new Card("Blue", "5"));
        validCards = game.hasAnyValidCardCheck(game.currentPlayer);
        System.out.println(game.discardDeck.peek().getColor());
        System.out.println(game.discardDeck.peek().getValue());
        System.out.println(game.currentPlayer.cardCount);
        System.out.println("================\n");
        System.out.println(validCards);
        System.out.println("================1\n");

        for(int i = 0; i < game.currentPlayer.playerHand.getHand().size(); i++){
            System.out.println(game.currentPlayer.playerHand.getHand().get(i).getColor());
            System.out.println(game.currentPlayer.playerHand.getHand().get(i).getValue());
            System.out.println("================2\n");
        }
        for(int cardIndex = 0; cardIndex < validCards.size(); cardIndex++){
            Card validCard = game.currentPlayer.playerHand.getHand().get(validCards.get(cardIndex));
            assertTrue(validCard.getColor().equals(topCard.getColor())
                    || validCard.getValue().equals(topCard.getValue()));
        }


    }

    @Test
    void updateCurrentCard(){
        GameState game = new GameState(8);
        game.discardDeck.push(new Card("Blue", "8"));
        game.updateCurrentCard();
        assertEquals(game.currentCard.getColor(), "Blue");
        assertEquals(game.currentCard.getValue(), "8");
    }

    @Test
    void drawCard() {
        GameState game = new GameState(8);
        game.initDecks();
        Card cardToDraw = game.drawDeck.peek();
        Card drawnCard = game.drawCard(game.currentPlayer);

        assertEquals(cardToDraw.getValue(), drawnCard.getValue());
        assertEquals(cardToDraw.getColor(),drawnCard.getColor());
    }

    @Test
    void drawCardValid() {
        GameState game = new GameState(8);
        game.initDecks();
        Card sampleCard = new Card("Blue", "5");
        game.discardDeck.push(sampleCard);

        Card sampleDrawnCard = new Card("Blue", "9");
        game.drawDeck.push(sampleDrawnCard);

        Card drawnCard = game.drawCard(game.currentPlayer);
        boolean match = game.drawCardValid(drawnCard);
        assertEquals(match, true);

        sampleDrawnCard = new Card("Yellow", "5");
        game.drawDeck.push(sampleDrawnCard);
        drawnCard = game.drawCard(game.currentPlayer);
        match = game.drawCardValid(drawnCard);
        assertEquals(match, true);

        sampleDrawnCard = new Card("Blue", "Reverse");
        game.drawDeck.push(sampleDrawnCard);
        drawnCard = game.drawCard(game.currentPlayer);
        match = game.drawCardValid(drawnCard);
        assertEquals(match, true);

        sampleDrawnCard = new Card("Yellow", "WildCard");
        game.drawDeck.push(sampleDrawnCard);
        drawnCard = game.drawCard(game.currentPlayer);
        match = game.drawCardValid(drawnCard);
        assertEquals(match, false);

        sampleDrawnCard = new Card("Blue", "Skip");
        game.drawDeck.push(sampleDrawnCard);
        drawnCard = game.drawCard(game.currentPlayer);
        match = game.drawCardValid(drawnCard);
        assertEquals(match, true);

        sampleDrawnCard = new Card("Blue", "DrawTwo");
        game.drawDeck.push(sampleDrawnCard);
        drawnCard = game.drawCard(game.currentPlayer);
        match = game.drawCardValid(drawnCard);
        assertEquals(match, true);

        sampleDrawnCard = new Card("Yellow", "Reverse");
        game.drawDeck.push(sampleDrawnCard);
        drawnCard = game.drawCard(game.currentPlayer);
        match = game.drawCardValid(drawnCard);
        assertEquals(match, false);


    }

    @Test
    void reverseDirection() {
        GameState newGame = new GameState(8);
        newGame.reverseDirection();
        assertEquals(newGame.directionClockwise, false);
        newGame.reverseDirection();
        assertEquals(newGame.directionClockwise, true);
    }

    @Test
    void skipNextPlayer() {
        GameState game = new GameState(8);
        game.skipNextPlayer();
        assertEquals(game.nextPlayer, game.players.get(2));

        game.reverseDirection();
        game.skipNextPlayer();
        assertEquals(game.nextPlayer, game.players.get(game.players.size() - 1));

    }

    @Test
    void skippedPlayerDrawNumCard() {
        GameState game = new GameState(8);
        game.determineNextPlayer();
        game.skippedPlayerDrawNumCard(2);
        assertEquals(game.nextPlayer.playerHand.getHand().size(), 9);
        game.skippedPlayerDrawNumCard(4);
        assertEquals(game.nextPlayer.playerHand.getHand().size(), 13);

    }



    @Test
    void specialCardActions() {
        GameState game = new GameState(8);
        game.discardDeck.push(new Card("Blue","Reverse"));
        game.specialCardActions();
        assertEquals(false, game.directionClockwise);

        game.discardDeck.push(new Card("Yellow", "Skip"));
        game.specialCardActions();
        assertEquals(game.players.get(6), game.nextPlayer);
        game.currentPlayer = game.nextPlayer;

        game.discardDeck.push(new Card("Green", "DrawTwo"));
        game.specialCardActions();
        assertEquals(game.players.get(5).cardCount, 9);
        assertEquals(game.players.get(4), game.nextPlayer );

        game.discardDeck.push(new Card("Blue", "WildCard"));
        game.specialCardActions();
        assertEquals("Blue",game.currentCard.getColor());

        game.discardDeck.push(new Card("Blue", "WildDrawFourCard"));
        game.specialCardActions();
        //assertEquals(game.players.get(3));
        assertEquals(11 ,game.players.get(4).cardCount);
        assertEquals(game.players.get(3), game.nextPlayer);
    }
}