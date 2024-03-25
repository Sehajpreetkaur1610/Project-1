package blackjack;

import java.util.ArrayList;
import java.util.List;

public class Player {
    private final String name;
    private final List<Card> hand;

    public Player(String name) {
        this.name = name;
        this.hand = new ArrayList<>();
    }

    public String getName() {
        return name;
    }

    public List<Card> getHand() {
        return hand;
    }

    public void addCard(Card card) {
        hand.add(card);
    }

    public void clearHand() {
        hand.clear();
    }

    public int calculateScore() {
        int score = 0;
        int numAces = 0;

        for (Card card : hand) {
            score += card.getValue();
            if (card.getValue() == 11) {
                numAces++;
            }
        }

        // Adjust score for Aces if needed
        while (score > 21 && numAces > 0) {
            score -= 10;
            numAces--;
        }

        return score;
    }

    // Multiplicity annotation to show association with Deck class (1 to 1)
    // Each player interacts with one deck
    public void interactWithDeck(Deck deck) {
        Card drawnCard = deck.drawCard();
        if (drawnCard != null) {
            hand.add(drawnCard);
            System.out.println(name + " drew a card: " + drawnCard);
        } else {
            System.out.println("The deck is empty.");
        }
    }

    // Method for splitting the hand if needed
    public void splitHand() {
        // Implement split logic here if needed
        // This method can be used to handle splitting the hand into two separate hands
    }
}
