package blackjack;

import java.util.Scanner;

public class Blackjack {
    private final Deck deck;
    private final Player player;
    private final Dealer dealer;
    private final Scanner scanner;
    private static final int MAX_ROUNDS = 10; // Maximum number of rounds

    public Blackjack(Deck deck, Player player, Dealer dealer, Scanner scanner) {
        this.deck = deck;
        this.player = player;
        this.dealer = dealer;
        this.scanner = scanner;
    }

    public void playGame() {
        System.out.println("Welcome to Blackjack!");

        askPlayerName(); // Ask for player's name

        try (Scanner scanner = new Scanner(System.in)) { // Closing scanner resource properly
            int round = 0; // Track the number of rounds played
            while (round < MAX_ROUNDS) { // Limit the number of rounds to prevent infinite loop
                round++; // Increment round counter
                deck.shuffle();
                player.clearHand();
                dealer.clearHand();

                dealInitialCards();

                System.out.println("Your hand: " + player.getHand());
                System.out.println("Dealer's hand: " + dealer.getHand().get(0) + " and [Hidden Card]");

                OUTER:
                while (true) {
                    System.out.println("\nDo you want to hit, stand, double down, or split? (h/s/d/sp)");
                    String choice = scanner.nextLine().toLowerCase();
                    switch (choice) {
                        case "h" -> {
                            player.addCardToHand(deck.drawCard());
                            System.out.println("Your hand: " + player.getHand());
                            if (player.calculateScore() > 21) {
                                System.out.println("Bust! Your score is over 21.");
                                determineWinner(); // Determine winner when player busts
                                break OUTER; // Break out of the outer loop if the player busts
                            }
                        }
                        case "s" -> {
                            break OUTER; // Break out of the outer loop if the player stands
                        }
                        case "d" -> {
                            // Implement double down logic
                            System.out.println("Double down!");
                            player.addCardToHand(deck.drawCard());
                            if (player.calculateScore() > 21) {
                                System.out.println("Bust! Your score is over 21.");
                                determineWinner(); // Determine winner when player busts
                                break OUTER; // Break out of the outer loop if the player busts
                            }
                            break; // Continue the game after doubling down
                        }
                        case "sp" -> {
                            // Implement split logic
                            System.out.println("Split!");
                            player.splitHand();
                            System.out.println("Your hands: " + player.getHand());
                            break OUTER; // Break out of the outer loop after splitting
                        }
                        default -> {
                            System.out.println("Invalid choice. Please enter 'h', 's', 'd', or 'sp'.");
                            continue; // Continue to the next iteration if the choice is invalid
                        }
                    }
                }

                if (player.calculateScore() <= 21) { // Ensure player score is valid before continuing
                    while (dealer.calculateScore() < 17) {
                        dealer.addCard(deck.drawCard());
                    }

                    System.out.println("\nYour hand: " + player.getHand() + " (Score: " + player.calculateScore() + ")");
                    System.out.println("Dealer's hand: " + dealer.getHand() + " (Score: " + dealer.calculateScore() + ")");

                    determineWinner(); // Determine winner after both player and dealer actions
                }

                System.out.println("\nDo you want to play another round? (yes/no)");
                String input = scanner.nextLine().toLowerCase();

                if (!input.equals("yes")) {
                    System.out.println("Goodbye! Thanks for playing, " + player.getName() + ".");
                    break;
                }
            }
        }
    }

    private void askPlayerName() {
        System.out.println("Enter your name:");
        String playerName = scanner.nextLine();
        player.setName(playerName);
        System.out.println("Welcome, " + playerName + "!");
    }

    private void dealInitialCards() {
        player.addCardToHand(deck.drawCard());
        dealer.addCard(deck.drawCard());
        player.addCardToHand(deck.drawCard());
        dealer.addCard(deck.drawCard());
    }

    private void determineWinner() {
        int playerScore = player.calculateScore();
        int dealerScore = dealer.calculateScore();

        if (playerScore > 21) {
            System.out.println("You busted! Dealer wins.");
        } else if (dealerScore > 21) {
            System.out.println("Dealer busted! You win.");
        } else if (playerScore > dealerScore) {
            System.out.println("You win!");
        } else if (playerScore < dealerScore) {
            System.out.println("Dealer wins.");
        } else {
            System.out.println("It's a tie!");
        }
    }

    public static void main(String[] args) {
        Deck deck = new Deck();
        Player player = new Player();
        Dealer dealer = new Dealer();
        Scanner scanner = new Scanner(System.in);

        Blackjack blackjackGame = new Blackjack(deck, player, dealer, scanner);
        blackjackGame.playGame();
    }
}