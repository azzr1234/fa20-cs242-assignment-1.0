package UNO.tests;

import UNO.Card;
import UNO.Deck;
import org.junit.jupiter.api.Test;

import java.util.Stack;

import static org.junit.jupiter.api.Assertions.*;

class DeckTest {

    @Test
    void shuffleDeck() {

    }

    @Test
    void getDeck() {
        Deck newDeck = new Deck();
        assertEquals(newDeck.getDeck(), new Stack<Card>());
    }

    @Test
    void peek() {
        Deck newDeck = new Deck();
        Card newCard = new Card("yellow", "5");
        Card secondCard = new Card("Blue","7");
        newDeck.push(newCard);
        newDeck.push(secondCard);
        assertEquals(newDeck.peek(), secondCard);
    }

    @Test
    void pop() {
        Deck newDeck = new Deck();
        Card newCard = new Card("yellow", "5");
        newDeck.push(newCard);
        assertEquals(newDeck.pop(), newCard);
    }

    @Test
    void push() {
        Deck newDeck = new Deck();
        Card newCard = new Card("yellow", "5");
        Card secondCard = new Card("Blue", "7");
        newDeck.push(newCard);
        newDeck.push(secondCard);
        assertEquals(newDeck.peek(), secondCard);
    }
}