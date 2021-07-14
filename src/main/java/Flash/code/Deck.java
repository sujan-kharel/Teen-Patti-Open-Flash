package Flash.code;

import java.util.ArrayList;
import java.util.Random;

public class Deck {
    private ArrayList<Card> cards;

    public Deck()
    {
        this.cards = new ArrayList<>();
    }


    public void CreateFullDeck()
    {
        for(Suit cardSuit : Suit.values())
        {
            for(Rank cardValue : Rank.values())
            {
                this.cards.add(new Card(cardValue, cardSuit));

            }

        }




        // initialize each card to its corresponding image
        // prints the whole deck of cards, in order (spades -> club -> heart -> diamond)
    }

    public void ShuffleCard()
    {
        ArrayList<Card> tempDeck = new ArrayList<>();
        Random random = new Random();
        int randomCardIndex = 0;
        int deckSize = this.cards.size();

        for(int i = 0; i < deckSize; i++)
        {
            randomCardIndex = random.nextInt(this.cards.size());
            tempDeck.add(this.cards.get(randomCardIndex));
            this.cards.remove(randomCardIndex);
        }
        this.cards= tempDeck;
    }

    @Override
    public String toString()
    {
        String cardListOutput = "";
        for(Card aCard : this.cards)
        {
            cardListOutput += aCard.toString() + "\n";
        }
        return cardListOutput;
    }

    public void removeCard(int i)
    {
        this.cards.remove(i);
    }
    // get and set method
    public Card getCard(int i)
    {
        return this.cards.get(i);
    }

    public void addCard(Card addCard)
    {
        this.cards.add(addCard);
    }

    // to draw from the deck
    public void draw( Deck comingFrom)
    {
        this.cards.add(comingFrom.getCard(0));
        comingFrom.removeCard(0);
    }


}
