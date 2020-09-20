package UNO.tests;

import UNO.Card;
import UNO.DiscardDeck;
import UNO.PlayableDeck;
import org.junit.jupiter.api.Test;

//import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

import static org.junit.jupiter.api.Assertions.*;

class PlayableDeckTest {

    @Test
    void PlayableDeck() {
        PlayableDeck newPlayDeck = new PlayableDeck();
        assertEquals(newPlayDeck.getDeck().size(), 108);
        DiscardDeck newDiscardDeck = new DiscardDeck();
        Card lastCardPlayed = new Card(null, null);
        for(int cardIndex = 0; cardIndex < 108; cardIndex++) {
            newDiscardDeck.push(newPlayDeck.pop());
            if (newPlayDeck.getDeck().empty()) {
                lastCardPlayed = new Card(newDiscardDeck.peek().getColor(), newDiscardDeck.peek().getValue());
                PlayableDeck discardToPlayable = new PlayableDeck(newDiscardDeck);
                assertEquals(newDiscardDeck.peek().getColor(), lastCardPlayed.getColor());
                assertEquals(newDiscardDeck.peek().getValue(),lastCardPlayed.getValue());
            }
        }



    }

    @Test
    void createRegularNumCards() {
        PlayableDeck testDeck = new PlayableDeck(true);
        
        testDeck.createRegularNumCards(testDeck.getDeck(), 19);
        assertEquals(testDeck.getDeck().size(), 76);
        int countRed = 0;
        ArrayList<String> valueRed = new ArrayList<>();
        int countBlue = 0;
        ArrayList<String> valueBlue = new ArrayList<>();
        int countYellow = 0;
        ArrayList<String> valueYellow = new ArrayList<>();
        int countGreen = 0;
        ArrayList<String> valueGreen = new ArrayList<>();
        for(int cardIndex = 0; cardIndex < 76; cardIndex++){
            Card poppedCard = testDeck.pop();
            switch (poppedCard.getColor()) {
                case "Red":
                    countRed++;
                    valueRed.add(poppedCard.getValue());
                    break;
                case "Blue":
                    countBlue++;
                    valueBlue.add(poppedCard.getValue());
                    break;
                case "Yellow":
                    countYellow++;
                    valueYellow.add(poppedCard.getValue());
                    break;
                case "Green":
                    countGreen++;
                    valueGreen.add(poppedCard.getValue());
                    break;
            }
        }
        assertEquals(countRed, 19);
        assertEquals(countBlue, 19);
        assertEquals(countYellow, 19);
        assertEquals(countGreen, 19);
        ArrayList<String> cardValues = new
                ArrayList<>(Arrays.asList("1", "2", "3", "4", "5", "6", "7", "8", "9", "0", "1", "2", "3", "4", "5", "6", "7", "8", "9"));
        Collections.sort(cardValues);
        Collections.sort(valueRed);
        Collections.sort(valueBlue);
        Collections.sort(valueGreen);
        Collections.sort(valueYellow);
        assertEquals(valueBlue, cardValues);
        assertEquals(valueRed, cardValues);
        assertEquals(valueYellow, cardValues);
        assertEquals(valueGreen, cardValues);
    }

    @Test
    void createReverseCards() {
        PlayableDeck testDeck = new PlayableDeck(true);
        testDeck.createReverseCards(testDeck.getDeck(), 8);
        assertEquals(testDeck.getDeck().size(), 8);
        int countRed = 0;

        int countBlue = 0;

        int countYellow = 0;

        int countGreen = 0;

        for(int cardIndex = 0; cardIndex < 8; cardIndex++){
            Card poppedCard = testDeck.pop();
            switch (poppedCard.getColor()) {
                case "Red":
                    countRed++;
                    assertEquals(poppedCard.getValue(), "Reverse");

                    break;
                case "Blue":
                    countBlue++;
                    assertEquals(poppedCard.getValue(), "Reverse");

                    break;
                case "Yellow":
                    countYellow++;
                    assertEquals(poppedCard.getValue(), "Reverse");

                    break;
                case "Green":
                    countGreen++;
                    assertEquals(poppedCard.getValue(), "Reverse");
                    break;
            }
        }
        assertEquals(countRed, 2);
        assertEquals(countBlue, 2);
        assertEquals(countYellow, 2);
        assertEquals(countGreen, 2);

    }

    @Test
    void createSkipCards() {
        PlayableDeck testDeck = new PlayableDeck(true);
        testDeck.createSkipCards(testDeck.getDeck(), 8);
        assertEquals(testDeck.getDeck().size(), 8);
        int countRed = 0;

        int countBlue = 0;

        int countYellow = 0;

        int countGreen = 0;

        for(int cardIndex = 0; cardIndex < 8; cardIndex++){
            Card poppedCard = testDeck.pop();
            switch (poppedCard.getColor()) {
                case "Red":
                    countRed++;
                    assertEquals(poppedCard.getValue(), "Skip");

                    break;
                case "Blue":
                    countBlue++;
                    assertEquals(poppedCard.getValue(), "Skip");

                    break;
                case "Yellow":
                    countYellow++;
                    assertEquals(poppedCard.getValue(), "Skip");

                    break;
                case "Green":
                    countGreen++;
                    assertEquals(poppedCard.getValue(), "Skip");
                    break;
            }
        }
        assertEquals(countRed, 2);
        assertEquals(countBlue, 2);
        assertEquals(countYellow, 2);
        assertEquals(countGreen, 2);
    }

    @Test
    void createDrawTwoCards() {
        PlayableDeck testDeck = new PlayableDeck(true);
        testDeck.createDrawTwoCards(testDeck.getDeck(), 8);
        assertEquals(testDeck.getDeck().size(), 8);
        int countRed = 0;

        int countBlue = 0;

        int countYellow = 0;

        int countGreen = 0;

        for(int cardIndex = 0; cardIndex < 8; cardIndex++){
            Card poppedCard = testDeck.pop();
            switch (poppedCard.getColor()) {
                case "Red":
                    countRed++;
                    assertEquals(poppedCard.getValue(), "DrawTwo");

                    break;
                case "Blue":
                    countBlue++;
                    assertEquals(poppedCard.getValue(), "DrawTwo");

                    break;
                case "Yellow":
                    countYellow++;
                    assertEquals(poppedCard.getValue(), "DrawTwo");

                    break;
                case "Green":
                    countGreen++;
                    assertEquals(poppedCard.getValue(), "DrawTwo");
                    break;
            }
        }
        assertEquals(countRed, 2);
        assertEquals(countBlue, 2);
        assertEquals(countYellow, 2);
        assertEquals(countGreen, 2);
    }

    @Test
    void createWildCards() {
        PlayableDeck testDeck = new PlayableDeck(true);
        testDeck.createWildCards(testDeck.getDeck(), 4);
        assertEquals(testDeck.getDeck().size(), 4);
        /*int countRed = 0;

        int countBlue = 0;

        int countYellow = 0;

        int countGreen = 0;

        for(int cardIndex = 0; cardIndex < 8; cardIndex++){
            Card poppedCard = testDeck.pop();
            if(poppedCard.getColor() == "Red"){
                countRed++;
                assertEquals(poppedCard.getValue(), "WildCard");

            }else if(poppedCard.getColor() == "Blue"){
                countBlue++;
                assertEquals(poppedCard.getValue(), "WildCard");

            }else if(poppedCard.getColor() == "Yellow"){
                countYellow++;
                assertEquals(poppedCard.getValue(), "WildCard");

            }else if(poppedCard.getColor() == "Green"){
                countGreen++;
                assertEquals(poppedCard.getValue(), "WildCard");
            }
        }
        assertEquals(countRed, 2);
        assertEquals(countBlue, 2);
        assertEquals(countYellow, 2);
        assertEquals(countGreen, 2);
        */
    }

    @Test
    void createWildDrawFourCards() {
        PlayableDeck testDeck = new PlayableDeck(true);
        testDeck.createWildDrawFourCards(testDeck.getDeck(), 4);
        assertEquals(testDeck.getDeck().size(), 4);

    }
}