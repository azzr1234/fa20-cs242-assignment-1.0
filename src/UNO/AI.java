package UNO;

import java.util.ArrayList;

abstract class AI extends Player{
    GameState game;
    public AI(GameState game){
        super();
        this.game = game;
    }
    abstract void playTurn();

}

class BaseLineAI extends AI{
    public BaseLineAI(GameState game){
        super(game);
    }

    void playTurn() {
        ArrayList<Integer> validCardsInHand = this.game.hasAnyValidCardCheck(this);
        if(!validCardsInHand.isEmpty()){
            playOneCard(validCardsInHand);
            this.updateCardCount();
        }else {
            //gui.noValidCardsDrawCards();
            Card drawnCard = this.game.drawCard(this);
            while(this.game.drawCardValid(drawnCard) == false){
                this.playerHand.getHand().add(drawnCard);
                this.updateCardCount();
                drawnCard = this.game.drawCard(this);
            }
            this.game.discardDeck.push(drawnCard);
        }
        specialAction();
        this.game.sayUno(this);
        this.game.determineNextPlayer();
        this.game.setNextPlayer();
    }

    void playOneCard(ArrayList<Integer> validCards){
        Card cardToRemove = this.playerHand.getHand().get(validCards.get(0));
        this.game.discardDeck.push(this.playerHand.getHand().remove(this.playerHand.getHand().indexOf(cardToRemove)));
    }

    void specialAction(){
        this.game.currentCard = this.game.discardDeck.peek();
        switch (this.game.currentCard.getValue()) {
            case "Reverse":
                this.game.reverseDirection();
                this.game.updateCurrentCard();
                break;
            case "Skip":
                this.game.skipNextPlayer();
                this.game.updateCurrentCard();
                break;
            case "DrawTwo":
                this.game.skippedPlayerDrawNumCard(2);
                this.game.determineNextPlayer();
                this.game.currentPlayer = this.game.nextPlayer;
                this.game.determineNextPlayer();
                this.game.updateCurrentCard();
                break;
            case "WildCard":
                this.game.currentCard = new Card("Blue", "WildCard");
                break;
            case "WildDrawFourCard":
                this.game.currentCard = new Card("Green", "WildDrawFourCard");
                this.game.skippedPlayerDrawNumCard(4);
                this.game.skipNextPlayer();
                break;
        }
    }


}

class StrategicAI extends AI{
    public StrategicAI(GameState game){
        super(game);
    }

    void playTurn() {
        ArrayList<Integer> validCardsInHand = this.game.hasAnyValidCardCheck(this);
        ArrayList<Integer> specialCardsInHand = checkforActionCards(validCardsInHand);
        if(specialCardsInHand.size() >= 2){
            playTwoCards(specialCardsInHand);
            this.updateCardCount();
        }else if(specialCardsInHand.size() == 1){
            playOneCard(specialCardsInHand);

            //gui.noValidCardsDrawCards();

        } else if(validCardsInHand.size() >= 2){
            playTwoCards(validCardsInHand);
            this.updateCardCount();

        }else if(validCardsInHand.size() == 1){
            playOneCard(validCardsInHand);
        }else{
            Card drawnCard = this.game.drawCard(this);
            while(!this.game.drawCardValid(drawnCard)){
                this.playerHand.getHand().add(drawnCard);
                this.updateCardCount();
                drawnCard = this.game.drawCard(this);
            }
            this.game.discardDeck.push(drawnCard);
        }
        specialAction();
        this.game.sayUno(this);
        this.game.determineNextPlayer();
        this.game.setNextPlayer();
    }

    void playOneCard(ArrayList<Integer> validCards){
        Card cardToRemove = this.playerHand.getHand().get(validCards.get(0));
        this.game.discardDeck.push(this.playerHand.getHand().remove(this.playerHand.getHand().indexOf(cardToRemove)));
    }

    void playTwoCards(ArrayList<Integer> validCards){

        Card cardToRemove = this.playerHand.getHand().get(validCards.get(0));
        Card secondCardToRemove = this.playerHand.getHand().get(validCards.get(1));
        this.game.discardDeck.push(this.playerHand.getHand().remove(this.playerHand.getHand().indexOf(cardToRemove)));
        this.game.discardDeck.push(this.playerHand.getHand().remove(this.playerHand.getHand().indexOf(secondCardToRemove)));
    }

    ArrayList<Integer> checkforActionCards(ArrayList<Integer> validCards){
        ArrayList<Integer> specialCards = new ArrayList<Integer>();
        for(int i : validCards){
            if(this.playerHand.getHand().get(i).getValue().equals("DrawTwoCard")){
                specialCards.add(i);
            }else if(this.playerHand.getHand().get(i).getValue().equals("WildDrawFourCard")){
                specialCards.add(i);
            }
        }
        return specialCards;
    }

    void specialAction(){
        this.game.currentCard = this.game.discardDeck.peek();
        switch (this.game.currentCard.getValue()) {
            case "Reverse":
                this.game.reverseDirection();
                this.game.updateCurrentCard();
                break;
            case "Skip":
                this.game.skipNextPlayer();
                this.game.updateCurrentCard();
                break;
            case "DrawTwo":
                this.game.skippedPlayerDrawNumCard(2);
                this.game.determineNextPlayer();
                this.game.currentPlayer = this.game.nextPlayer;
                this.game.determineNextPlayer();
                this.game.updateCurrentCard();
                break;
            case "WildCard":
                this.game.currentCard = new Card("Blue", "WildCard");
                break;
            case "WildDrawFourCard":
                this.game.currentCard = new Card("Green", "WildDrawFourCard");
                this.game.skippedPlayerDrawNumCard(4);
                this.game.skipNextPlayer();
                break;
        }
    }


}


