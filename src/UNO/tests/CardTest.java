package UNO.tests;

import UNO.Card;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CardTest {
    /**
     * Checks color is correct
     */
    @Test
    void getColor(){
        Card newCard = new Card("yellow", "5");
        assertEquals(newCard.getColor(), "yellow");
    }

    /**
     * Checks value is correct
     */
    @Test
    void getValue(){
        Card newCard = new Card("yellow", "5");
        assertEquals(newCard.getValue(), "5");
    }
}