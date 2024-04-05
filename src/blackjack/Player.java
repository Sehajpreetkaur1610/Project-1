package blackjack;

import java.util.ArrayList;
import java.util.List;

public class Player {
    private String name;
    private List<Card> hand;

    public Player() {
        this.hand = new ArrayList<>();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Card> getHand() {
        return hand;
    }

    public void addCardToHand(Card card) {
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

    public void splitHand() {
        if (hand.size() != 2 || !hand.get(0).equals(hand.get(1))) {
            System.out.println("Cannot split hand.");
            return;
        }
        
        Card secondCard = hand.remove(1); // Remove the second card from the hand
        List<Card> newHand = new ArrayList<>(); // Create a new hand with the second card
        newHand.add(secondCard);
        // Assuming you have a method to create a new Player with a given hand
        // You need to replace this line with your actual implementation
        // player.add(new Player(newHand)); 
    }
}
