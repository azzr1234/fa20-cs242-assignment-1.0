package UNO;
import java.util.*;

import java.util.Collections;
import java.util.Stack;



public class Deck {

    public Stack<Card> deck;

    /**
     * Deck constructor. Instantiates as a Stack<Card>
     */
    public Deck() {
        this.deck = new Stack<Card>();
    }

    /**
     * @param d shuffles the deck
     */
    public void shuffleDeck(Stack<Card> d) {
        Collections.shuffle(d);
    }

    /**
     * @return Deck object
     */
    public Stack<Card> getDeck(){
        return this.deck;
    }

    /**
     * @return the first/top card on this deck, but does not remove from deck
     */
    public Card peek() {
        return this.getDeck().peek();
    }

    /**
     * @return removed card from the first/top of this deck
     */
    public Card pop() {
        return this.getDeck().pop();
    }

    /**
     * @param card_to_push Card to push into respective deck
     */
    public void push(Card card_to_push) {
        this.getDeck().push(card_to_push);
    }

}




