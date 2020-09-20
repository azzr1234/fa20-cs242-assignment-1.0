package UNO;
import org.junit.jupiter.api.Test;

import java.util.Stack;
import java.util.ArrayList;

public class PlayableDeck extends Deck{


    /**
     * Contructor for the PlayableDeck Class, which is the deck players will draw from and also get cards distributed
     * from at the beginning of the game.
     * PlayableDeck is created with all the cards needed to start a game.
     */
    public PlayableDeck() {
        super();

        createRegularNumCards(this.getDeck(), 19); // 19 for each color * 4 for each color = 76 cards

        createReverseCards(this.getDeck(), 8); // 2 for each color * 4 for each color = 8 cards

        createSkipCards(this.getDeck(), 8); // 2 for each color * 4 for each color = 8 cards

        createDrawTwoCards(this.getDeck(), 8);

        createWildCards(this.getDeck(), 4); // 1 for each color * 4 for each color = 8 cards

        createWildDrawFourCards(this.getDeck(), 4); // 1 for each color * 4 for each color = 8 cards

        shuffleDeck(this.getDeck());

    }

    /**
     * Constructor for an empty playable deck for testing
     * @param empty
     */
    public PlayableDeck(boolean empty){
        super();
    }

    /**
     * Constructor for a playable deck from a discard deck
     * @param d discard deck to create the new playable from
     */
    public PlayableDeck(DiscardDeck d) {
        super();
        Card last_card_played = d.pop();
        for(int cardIndex = 0; cardIndex < d.getDeck().size(); cardIndex++) {
            this.getDeck().push(d.pop());
        }
        shuffleDeck(this.getDeck());
        d.push(last_card_played);
    }

    /**
     * This function is in charge of creating all regular cards with a color and number value
     * There is a single zero value care and 2, 1-9, number value cards
     * @param d Deck to push into
     * @param num_cards_needed number of cards to create and push
     */
    public void createRegularNumCards(Stack<Card> d, int num_cards_needed) {
        String[] colors = {"Red", "Blue", "Green","Yellow"};
        for(int colorIndex = 0; colorIndex < colors.length; colorIndex++) {
            for (int numCards = 1; numCards <= num_cards_needed; numCards++) {
                RegularNumCard regCard = new RegularNumCard(colors[colorIndex], Integer.toString(numCards % 10));
                d.push(regCard);
            }
        }
    }

    /**
     * This function creates 8 reverse cards. 2 for each color with a value of "Reverse".
     *  Takes the deck we are putting the cards into and the number of cards to make as param
     * @param d Deck to push into
     * @param count number of cards to create push
     */
    public void createReverseCards(Stack<Card> d, int count) {
        String[] colors = {"Red", "Blue", "Green","Yellow"};
        for (int colorIndex = 0; colorIndex < colors.length; colorIndex++) {
            for (int numReverseCards = 0; numReverseCards < count / 4; numReverseCards++) {
                ReverseCard revCard = new ReverseCard(colors[colorIndex]);
                d.push(revCard);
            }
        }
    }

    /**
     * This function create 8 skip cards. 2 for each color with a value of "Skip"
     * Takes the deck we are putting the cards into and the number of cards to make as param
     * @param d Deck to push into
     * @param count Number of cards to create push
     */
    public void createSkipCards(Stack<Card> d, int count) {
        String[] colors = {"Red", "Blue", "Green","Yellow"};
        for (int colorIndex = 0; colorIndex < colors.length; colorIndex++)  {
            for (int numSkipCards = 0; numSkipCards < count / 4; numSkipCards++) {
                SkipCard skipCard = new SkipCard(colors[colorIndex]);
                d.push(skipCard);
            }
        }
    }

    /**
     * This function creates 8 DrawTwo cards. 2 for each color with a value of "DrawTwo"
     * Takes the deck we are putting the cards into and the number of cards to make as param
     * @param d Deck to push into
     * @param count Number of Cards to create and push
     */
    public void createDrawTwoCards(Stack<Card> d, int count) {
        String[] colors = {"Red", "Blue", "Green","Yellow"};
        for (int colorIndex = 0; colorIndex < colors.length; colorIndex++)  {
            for (int numSkipCards = 0; numSkipCards < count / 4 ; numSkipCards++) {
                DrawTwoCard skipCard = new DrawTwoCard(colors[colorIndex]);
                d.push(skipCard);
            }
        }
    }

    /**
     * This function creates 4 Wild cards. 1 for each color deck with a value of "WildCard"
     * Takes the deck we are putting the cards into and the number of cards to make as param
     * @param d Deck to push into
     * @param count Number of cards to create and push
     */
    public void createWildCards(Stack<Card> d, int count) {
        for (int numWildCards = 0; numWildCards < count; numWildCards++ ) {
            WildCard wildCard = new WildCard(null);
            d.push(wildCard);
        }
    }

    /**
     * This function creates 4 Wild Draw 4 cards. 1 for each color deck witha value of "WildDrawFour"
     * Takes the deck we are putting the cards into and the number of cards to make as param
     * @param d Deck to push into
     * @param count Number of Cards to create and push
     */
    public void createWildDrawFourCards(Stack<Card> d, int count) {
        for (int numWild4Cards = 0; numWild4Cards < count; numWild4Cards++) {
            WildDrawFourCard wild4Card = new WildDrawFourCard(null);
            d.push(wild4Card);
        }
    }


}


