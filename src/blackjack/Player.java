package blackjack;

import java.util.ArrayList;
import java.util.List;

public class Player {
    private final String name;
    private final List<Card> hand;
    private Dealer dealer; // Reference to the Dealer class

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

    public void setDealer(Dealer dealer) {
        this.dealer = dealer;
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

    public void performActionBasedOnDealerScore() {
        if (dealer != null) {
            // Implement logic based on dealer's score, such as player's actions
            
        }
    }

    void interactWithDeck(Deck aThis) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    void splitHand() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
}
