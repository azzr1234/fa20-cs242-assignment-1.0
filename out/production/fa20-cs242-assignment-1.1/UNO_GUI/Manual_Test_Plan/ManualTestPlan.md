**Manual Test Plan**

**IDE**<br/>
Intellij Community v2020.2<br/>
Eclipse v4.16<br/>

**Start Game**<br/>
After beginning the game, you will be greeted by the opening statement and then asked to input 
a number of players. 
![OpeningImage](OpeningStatement.png "Title")<br/>
Make sure to input an integer.


**Player's Turn**<br/>
After the game has started the first player will be told to initiate their turn.
When it is your a turn to play you will be prompted as such,<br/>
<br/>
![PlayerTurn](PlayerTurn.png )<br/>
After you press Enter, you will be shown you cards.<br/>
![ShowCards](ShowCards.png)<br/>
In this Uno game, you may put more than one card onto the discard deck at a time
as long as the cards match the color or value of the card most recently played by the previous player.
If you have more than one valid card to play you will be given the option to play up to 2 cards.<br/>
Note: The last card you play is the only card that will take affect into the game<br/>
![PlayTwoCards](twoCardsToPlay.png)
<br/>
<br/>
If you only have 1 valid card to play, you will not be given the option to play more than one card,<br/>
![PlayOneCard](oneCardToPlay.png)
<br/>
<br/>
When a Wild Card type card is played you will be prompted to enter a color,<br/>
![WildCard](wildCardToPlay.png)
<br/>
<br/>
In the case you do not have any valid cards to play, you will be prompted as such.<br/>
![DrawCards](noValidCardToPlay.png)<br/>
When you do not have any cards to play, the player has to keep taking cards until a playable card is drawn.
This completes your turn, and is now the next players turn.
<br/>
<br/>

If you play an invalid card you will get a message as such,<br/>
![InvalidCard](InvalidCard.png)<br/>
<br/>
<br/>
When it is the AI's turn to play, you will be prompted to press enter and their turn will be played.<br/>
![Baseline](Baseline.png)<br/>

![Strategic](StrategicAI.png)<br/>

<br/>


When it is the next players turn, the console will no longer show the previous players hand.
For this reason, make sure to keep the console at a medium length, only enough to see the longest prompt of 10 lines.

**End Game**<br/>
When there is a player with no cards left the game will be over.
You will see a message as such,<br/>
![GameEnded](GameFinished.png)

