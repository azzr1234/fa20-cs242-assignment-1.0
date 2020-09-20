package UNO;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

class HandTest {

    @Test
    void getHand() {
        Hand myHand = new Hand();
        assertEquals(myHand.getHand(), new ArrayList<Card>());
    }

    @Test
    void createHand() {
        Hand myHand = new Hand();
        PlayableDeck drawDeck = new PlayableDeck();
        myHand.createHand(drawDeck, 7);
        assertEquals(myHand.getHand().size(), 7);
    }
}