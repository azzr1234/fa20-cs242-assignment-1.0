package UNO;
import UNO_GUI.*;
import com.sun.xml.internal.ws.api.ha.StickyFeature;

import javax.swing.tree.ExpandVetoException;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;



public class GameState{
    public PlayableDeck drawDeck;
    public DiscardDeck discardDeck;
    public ArrayList<Player> players;
    public Player currentPlayer;
    public Player nextPlayer;
    public Card currentCard;
    public boolean directionClockwise;
    public boolean unoSaid;
    public UnoGUI gui;
    public AI baseAIPlayer;
    public AI stratAIPlayer;

    /**
     * Constructor for a new game of UNO.
     * drawDeck
     * This is the deck players will draw from during their turn.
     *
     * discardDeck
     * Deck that will hold all the played cards after each players turn
     *
     * players
     * ArrayList of players in the game.
     *
     * currentPlayer
     * Instantiated to be the first element in players arraylist at the beginning of the game.
     * Updated after every turn and when a special effect card dealing with skipping turn or reverse direction is played
     *
     * nextPlayer
     * Updated after a special effects card is played, such as a reverse direction, a skip next player card and wild draw 4 card.
     *
     * currentCard
     * Takes the last placed card from the discard deck to compare against potential cards to play
     *
     * directionClockwise
     * Updated after any time a reverse card is played.
     * True denotes clockwise; false denotes counter clockwise
     *
     * @param numPlayers Number of players that will be playing game
     */
    public GameState(int numPlayers){
        this.drawDeck = new PlayableDeck();
        this.discardDeck = new DiscardDeck();
        this.baseAIPlayer = new BaseLineAI(this);
        this.stratAIPlayer = new StrategicAI(this);
        this.players = createPlayers(numPlayers);
        this.currentPlayer = this.players.get(0);
        this.directionClockwise = true;
        this.unoSaid = false;
        this.determineNextPlayer();
        this.initDecks();
        this.gui = new UnoGUI(this);

    }

    /**
     *
     * @param numPlayers Number of players to be created
     * @return Players are in a ArrayList
     */
    public ArrayList<Player> createPlayers(int numPlayers){
        ArrayList<Player> gamePlayers = new ArrayList<>();
        //Player addAI = new BaseLineAI(this);
        this.baseAIPlayer.playerHand.createHand(this.drawDeck, 7);
        this.baseAIPlayer.updateCardCount();
        gamePlayers.add(this.baseAIPlayer);
        //Player addStratAI = new StrategicAI(this);
        this.stratAIPlayer.playerHand.createHand(this.drawDeck, 7);
        this.stratAIPlayer.updateCardCount();
        gamePlayers.add(this.stratAIPlayer);
        for(int playerIndex = 0; playerIndex < numPlayers; playerIndex++){
            Player addPlayer = new Player();
            addPlayer.playerHand.createHand(this.drawDeck, 7);
            addPlayer.updateCardCount();
            gamePlayers.add(addPlayer);
        }

        return gamePlayers;
    }

    /**
     * Initialize decks for play
     * Put a card onto discard deck until a card that is not a WildCard or a WildDrawFour card is drawn
     */
    public void initDecks(){
        this.discardDeck.push(this.drawDeck.pop());
        while(this.discardDeck.peek().getValue().equals("WildCard") || this.discardDeck.peek().getValue().equals("WildDrawFourCard")){
            this.discardDeck.push(this.drawDeck.pop());
        }
        this.currentCard = this.discardDeck.peek();
    }

    /**
     * Player turn process. Checks to see if they have any valid cards. If not, then take a card from the playable deck.
     * Gives a change to either play the drawn card if valid, and if they would like to play the card
     * Initiates actions if any special card, reverse, skip, drawtwo, etc, is played
     * determines who the next player is and takes the special card action into calculation
     *  @param p player who is playing turn
     */
    public void playTurn(Player p){
        ArrayList<Integer> validCardsInHand = hasAnyValidCardCheck(p);
        if(!validCardsInHand.isEmpty()){
            if(validCardsInHand.size() >= 2){
                doPlayerChoice(p, validCardsInHand);
            }else{
                playOneCardChoice(p,validCardsInHand);
            }
            p.updateCardCount();
        }else {
            gui.noValidCardsDrawCards();
            Card drawnCard = drawCard(p);
            while(drawCardValid(drawnCard) == false){
                p.playerHand.getHand().add(drawnCard);
                p.updateCardCount();
                drawnCard = drawCard(p);
            }
            this.discardDeck.push(drawnCard);
        }
        specialCardActions();
        sayUno(p);
        determineNextPlayer();
        setNextPlayer();
    }

    /**
     * Gets color input from player
     * @return
     */
    private String getColorChange(){
        System.out.println("Input a new Color to set Wild Card color!");
        BufferedReader rd = new BufferedReader(new InputStreamReader(System.in));
        String input = "";
        try {
            input = rd.readLine();
        } catch (IOException e){
            e.printStackTrace();
        }
        return input.substring(0, 1).toUpperCase() + input.substring(1);
    }

    /**
     * Get choice from player of playing 1 or 2 cards
     * @param p
     * @param validCardsInHand
     */
    protected void doPlayerChoice(Player p, ArrayList<Integer> validCardsInHand){
        while(true) {
            try {
                System.out.println("Would you like to play more than one card?(y/n)");
                String choice = getPlayerChoice();
                if (choice.equals("y") == false && choice.equals("n") == false) {
                    throw new IOException("Input must be either y or n");
                }
                if (choice.equals("y")) {
                    playTwoCardsChoice(p, validCardsInHand);
                } else if (choice.equals("n")) {

                    playOneCardChoice(p, validCardsInHand);
                } else {
                    throw new IOException("Please input lower case y, for yes or n, for no");
                }
                break;
            } catch (IOException e) {
                System.out.println(e.getMessage());
            }
        }
    }

    protected void playOneCardChoice(Player p, ArrayList<Integer> validCardsInHand){
        System.out.println("Please Enter the card index. Ex. 3");
        while(true) {
            String[] chosen_index = getSingleCardIndexInput();
            try {
                if (!validCardsInHand.contains(Integer.parseInt(chosen_index[0]))) {
                    throw new IOException("You have entered an invalid of cards to play. Please enter valid index.");
                } else {
                    playCard(p, validCardsInHand, chosen_index);
                }
                break;
            } catch (IOException e) {
                System.out.println(e.getMessage());
            }
        }
    }


    private void playTwoCardsChoice(Player p, ArrayList<Integer> validCardsInHand){
        System.out.println("Please Enter the card indices seperated by a space. Max 2 cards. Ex. 1 4 ");
        while(true) {
            String[] chosen_indices = getCardIndexInput();
            try {
                if (!validCardsInHand.contains(Integer.parseInt(chosen_indices[0])) || !validCardsInHand.contains(Integer.parseInt(chosen_indices[1]))) {

                    throw new IOException("You have entered an invalid of cards to play. Please enter 2 indices.");
                } else {
                    playTwoCards(p, validCardsInHand, chosen_indices);
                }
                break;
            } catch (IOException e) {
                System.out.println(e.getMessage());
            }
        }
    }

    /**
     * Get player choice of player more than one card
     * @return
     */
    private String getPlayerChoice(){
        BufferedReader rd = new BufferedReader(new InputStreamReader(System.in));
        String input = "";
        try {
            input = rd.readLine();

        } catch (IOException e){
            System.out.println(e.getMessage());
        }
        return input;
    }

    private String[] getSingleCardIndexInput(){
        BufferedReader rd = new BufferedReader(new InputStreamReader(System.in));
        String input = "";
        try {
            input = rd.readLine();
        } catch (IOException e){
            e.printStackTrace();
        }
        String[] index = {input};
        return index;
    }


    /**
     * Get the index of the cards the player would like to play
     * @return
     */
    private String[] getCardIndexInput(){
        BufferedReader rd = new BufferedReader(new InputStreamReader(System.in));
        String index_input = "";
        try {
            index_input = rd.readLine();
        } catch (IOException e){
            e.printStackTrace();
        }
        String[] indices= index_input.split(" ");;
        return indices;
    }

    /**
     * After determining next Player, the new player should go.
     */
    protected void setNextPlayer(){
        this.currentPlayer = this.nextPlayer;
        determineNextPlayer();
    }



    /**
     * Completes any actions for when a special card is played
     */
    public void specialCardActions(){
        this.currentCard = this.discardDeck.peek();
        switch (this.currentCard.getValue()) {
            case "Reverse":
                reverseDirection();
                updateCurrentCard();
                break;
            case "Skip":
                skipNextPlayer();
                updateCurrentCard();
                break;
            case "DrawTwo":

                skippedPlayerDrawNumCard(2);
                determineNextPlayer();
                this.currentPlayer = this.nextPlayer;
                determineNextPlayer();
                updateCurrentCard();
                break;
            case "WildCard":
                String newColor = getColorChange();
                this.currentCard = new Card(newColor, "WildCard");

                //this.discardDeck.pe
                break;
            case "WildDrawFourCard":
                newColor = getColorChange();
                this.currentCard = new Card(newColor, "WildDrawFourCard");

                skippedPlayerDrawNumCard(4);

                skipNextPlayer();
                break;
        }
        //
    }

    /**
     * Action for when a player wants to play a card
     * @param p player that will play a card
     * @param validCards ArrayList of cards that are valid to play from the current top card on the discard pile
     * @param chosen_index
     */
    public void playCard(Player p, ArrayList<Integer> validCards, String[] chosen_index){
        try{
            if(validCards.contains(Integer.parseInt(chosen_index[0]))){
                Card cardToRemove = p.playerHand.getHand().get(Integer.parseInt(chosen_index[0]));
                this.discardDeck.push(p.playerHand.getHand().remove(p.playerHand.getHand().indexOf(cardToRemove)));
                System.out.println("You Have Played " + cardToRemove.getColor() + " " + cardToRemove.getValue());
                System.out.println("Press any enter to acknowledge");
                gui.acknowledgeChoice();
            }else{
                throw new Exception("Your chosen cards were not a valid card to play. Please choose again.");
            }
        }catch(Exception e){
            System.out.println(e.getMessage());
        }

    }

    /**
     * Action for player to play two cards at once. Both Cards must match the color or value of the most
     * recent played card on the discard deck
     * @param p player to play cards
     * @param validCards ArrayList of valid cards to be able to play
     */
    public void playTwoCards(Player p, ArrayList<Integer> validCards, String[] indices){
        try{
            if(!validCards.contains(Integer.parseInt(indices[0])) || !validCards.contains(Integer.parseInt(indices[1]))){
                throw new Exception("Your chosen card was not a valid card to play. Please choose again");
            }
            if(validCards.contains(Integer.parseInt(indices[0])) && validCards.contains(Integer.parseInt(indices[1]))){

                Card cardToRemove = p.playerHand.getHand().get(Integer.parseInt(indices[0]));
                Card secondCardToRemove = p.playerHand.getHand().get(Integer.parseInt(indices[1]));
                this.discardDeck.push(p.playerHand.getHand().remove(p.playerHand.getHand().indexOf(cardToRemove)));
                this.discardDeck.push(p.playerHand.getHand().remove(p.playerHand.getHand().indexOf(secondCardToRemove)));
                System.out.println("You Have Played " + cardToRemove.getColor() + " " + cardToRemove.getValue() +
                        " and " + secondCardToRemove.getColor() + " " + secondCardToRemove.getValue());
                System.out.println("Press enter to acknowledge");
                gui.acknowledgeChoice();
            }
        }catch(Exception e){
            System.out.println(e.getMessage());
        }
    }

    /**
     * Checks to see if there is a winner amongst the players.
     * @return the player who is the winner
     */
    public Player checkForWinner(){
        for (Player player : this.players) {
            if (player.cardCount == 0) {
                return player;
            }
        }
        return null;
    }

    /**
     * After a players turn, a special card may have been played. This takes that into account and determines the next player
     */
    public void determineNextPlayer(){
        int playerIndex = this.players.indexOf(this.currentPlayer);
        if(this.directionClockwise){
            if(playerIndex == players.size() - 1){
                this.nextPlayer = this.players.get(0);
            }else{
                this.nextPlayer = this.players.get(playerIndex + 1);
            }
        }else if(!this.directionClockwise){
            if(playerIndex == 0){
                this.nextPlayer = players.get(this.players.size() - 1);
            }else{
                this.nextPlayer = players.get(playerIndex - 1);
            }
        }
    }

    /**
     * Creates an ArrayList of indexs in the player hand that is a valid card to play
     * @param p player with hand to check
     * @return ArrayList with card indexes
     */
    public ArrayList<Integer> hasAnyValidCardCheck(Player p){
        ArrayList<Integer> allValidCards = new ArrayList<>();

        int cardIndex = 0;
        for(Card checkCard : p.playerHand.getHand()){

            try{
                if(checkCard.getValue().equals("WildCard") || checkCard.getValue().equals("WildDrawFourCard") ){
                    allValidCards.add(cardIndex);
                }
                if (checkCard.getColor().equals(this.currentCard.getColor()) ||
                        checkCard.getValue().equals(this.currentCard.getValue())) {

                    allValidCards.add(cardIndex);
                }
            }catch(NullPointerException e){

            }

            cardIndex++;
        }


        return allValidCards;
    }

    /**
     * Updates the currentCard for Gamestate to hold by peeking at the top of the discard pile
     */
    public void updateCurrentCard(){
        this.currentCard = this.discardDeck.peek();
    }

    /**
     * Action for a player to take a card from the playableDeck
     * @param p player to take card
     * @return card that has been drawn
     */
    public Card drawCard(Player p){
        if(this.drawDeck.getDeck().empty()){
            this.drawDeck = new PlayableDeck(this.discardDeck);
        }
        return this.drawDeck.getDeck().pop();
    }

    /**
     * Checks to see if the drawn card is valid to play right away onto the discard deck
     * @param drawnCard card to check if valid
     * @return boolean value of if valid
     */
    public boolean drawCardValid(Card drawnCard){
        if(drawnCard.getColor().equals(this.discardDeck.peek().getColor()) || drawnCard.getValue().equals(this.discardDeck.peek().getValue())){
            return true;
        }
        return false;
    }

    /**
     * Reverses the direction of play by updating the directionClockwise variable held by Gamestate
     */
    public void reverseDirection(){
        if(this.directionClockwise == true){
            this.directionClockwise = false;
        }else{
            this.directionClockwise = true;
        }
    }

    /**
     * Skips the next player in rotation if a Skip, DrawTwo, or a WildDrawFour card is played
     */
    public void skipNextPlayer(){
        determineNextPlayer();
        this.currentPlayer = this.nextPlayer;
        determineNextPlayer();
    }

    /**
     * When a DrawTwo or WildDrawFour card is played. The next player is skipped in the turn rotation and also has to
     * draw a number of cards into their hand
     * @param numCardsToTake Number of cards to add to hand
     */
    public void skippedPlayerDrawNumCard(int numCardsToTake){
        Player nextInLine;
        int currentPlayerRotationPlace = this.players.indexOf(this.currentPlayer);
        if(this.directionClockwise)
            if(currentPlayerRotationPlace == (players.size() - 1)){
                nextInLine = this.players.get(0);
            }else{
                nextInLine = this.players.get(this.players.indexOf(currentPlayer) + 1);
        }else{
            if(currentPlayerRotationPlace == 0){
                nextInLine = this.players.get(this.players.size() - 1);
            }else{
                nextInLine = this.players.get(this.players.indexOf(currentPlayer) - 1);
            }
        }
        for(int numCards = 0; numCards < numCardsToTake; numCards++){
            nextInLine.playerHand.getHand().add(this.drawDeck.pop());
        }
        nextInLine.updateCardCount();
    }

    public void sayUno(Player p){
        if(p.cardCount == 1){
            System.out.println("UNO!");
            this.unoSaid = true;
        }
    }


}
