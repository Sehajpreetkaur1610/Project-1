package blackjack;

import java.util.ArrayList;
import java.util.List;

public class Dealer {
    private final List<Card> hand;
    // Reference to the Player class

    public Dealer() {
        this.hand = new ArrayList<>();
    }

    public List<Card> getHand() {
        return hand;
    }

    public void setPlayer(Player player) {
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

    public void performActionBasedOnPlayerScore() {
        // Implement logic based on player's score, such as hitting or standing
        
    }
}
