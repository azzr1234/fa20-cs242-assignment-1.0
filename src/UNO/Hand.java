package UNO;

import java.util.ArrayList;

public class Hand{
    public ArrayList<Card> myHand;

    /**
     * Hand constructor
     */
    public Hand(){
        this.myHand = new ArrayList<Card>();
    }

    /**
     *
     * @return hand data structure
     *
     */
    public ArrayList<Card> getHand(){
        return this.myHand;
    }

    /**
     *
     * @param drawDeck Deck to draw from when creating hand
     * @param numCards number of cards to draw
     */
    public void createHand(PlayableDeck drawDeck, int numCards){
        for(int cardIndex = 0; cardIndex < 7; cardIndex++){
            this.getHand().add(drawDeck.pop());
        }
    }



}