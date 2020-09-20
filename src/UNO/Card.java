package UNO;
import java.util.Scanner;

public class Card{
    private final String color;
    private final String value;

    /**
     * @param color Represents the color of the card in a String
     * @param value Represents the value of the card in a String
     */
    public Card(String color, String value){
        this.color = color;
        this.value = value;
    }

    /**
     * @return Color of this card
     */
    public String getColor(){
        return this.color;
    }

    /**
     * @return Number or special card value, reverse, skip, etc, of the card
     */
    public String getValue(){
        return this.value;
    }

}

/**
 * Class representing regular card with number value
 */
class RegularNumCard extends Card{
    public RegularNumCard(String cardColor, String cardNum){
        super(cardColor, cardNum);
    }
}

/**
 * Class representing ReverseCard and has card value of "Reverse"
 */
class ReverseCard extends Card{
    public ReverseCard(String cardColor){
        super(cardColor,"Reverse");
    }
}

/**
 * Class representing ReverseCard and has card value of "Skip"
 */
class SkipCard extends Card{
    public SkipCard(String cardColor){
        super(cardColor, "Skip");
    }
}

/**
 * Class representing ReverseCard and has card value of "DrawTwo"
 */
class DrawTwoCard extends Card{
    public DrawTwoCard(String cardColor){
        super(cardColor, "DrawTwo");
    }
}

/**
 * Class representing ReverseCard and has card value of "WildCard"
 */
class WildCard extends Card{
    public WildCard(String cardColor){
        super(cardColor, "WildCard");
    }
}

/**
 * Class representing ReverseCard and has card value of "WildDrawFourCard"
 */
class WildDrawFourCard extends Card{
    public WildDrawFourCard(String cardColor){
        super(cardColor, "WildDrawFourCard");
    }
}


