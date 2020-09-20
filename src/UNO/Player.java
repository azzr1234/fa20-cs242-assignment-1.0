/**
 * @param playerHand
 * From the hand class. Every player gets a hand object to hold their cards
 *
 * @param cardCount
 * Counts the number of cards in hand to see if they are a potential winner, when the count is zero
 */
package UNO;


public class Player {
    public Hand playerHand;
    int cardCount;

    /**
     * Constructor for Player class. Creates hand and cardCount of hand.
     */
    public Player(){
        this.playerHand = new Hand();
        this.cardCount = this.playerHand.getHand().size();
    }

    /**
     * Updates the cardCount after each turn
     */
    public void updateCardCount(){
        this.cardCount = this.playerHand.getHand().size();
    }
}
