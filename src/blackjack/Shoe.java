package blackjack;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Shoe {
    private final List<Deck> decks;
    private final int numberOfDecks;

    public Shoe(int numberOfDecks) {
        this.numberOfDecks = numberOfDecks;
        decks = new ArrayList<>();
        initializeShoe();
    }

    private void initializeShoe() {
        for (int i = 0; i < numberOfDecks; i++) {
            decks.add(new Deck());
        }
    }

    public void shuffleShoe() {
        for (Deck deck : decks) {
            deck.shuffle();
        }
        Collections.shuffle(decks);
    }

    public Card drawCard() {
        if (!decks.isEmpty()) {
            Deck selectedDeck = decks.get(0);
            Card drawnCard = selectedDeck.drawCard();
            if (selectedDeck.size() == 0) {
                decks.remove(0); // Remove the empty deck from the shoe
            }
            return drawnCard;
        }
        return null; // Return null if the shoe is empty
    }

    public int size() {
        int totalSize = 0;
        for (Deck deck : decks) {
            totalSize += deck.size();
        }
        return totalSize;
    }
}
