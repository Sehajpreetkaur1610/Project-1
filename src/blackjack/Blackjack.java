package blackjack;

import java.util.Scanner;

public class Blackjack {
    private final Deck deck;
    private Player player;
    private final Player dealer;
    private final Scanner scanner;

    public Blackjack() {
        deck = new Deck();
        dealer = new Player("Dealer");
        scanner = new Scanner(System.in);
    }

    public void askPlayerName() {
        System.out.println("Enter your name:");
        String playerName = scanner.nextLine();
        player = new Player(playerName);
        System.out.println("Welcome, " + playerName + "!");
    }

    public void dealInitialCards() {
        player.addCard(deck.drawCard());
        dealer.addCard(deck.drawCard());
        player.addCard(deck.drawCard());
        dealer.addCard(deck.drawCard());
    }

    public void playGame() {
        System.out.println("Welcome to Blackjack!");

        askPlayerName(); // Ask for player's name

        try (scanner) {
            while (true) {
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
                            player.addCard(deck.drawCard());
                            System.out.println("Your hand: " + player.getHand());
                            if (player.calculateScore() > 21) {
                                System.out.println("Bust! Your score is over 21.");
                                break OUTER;
                            }
                        }
                        case "s" -> {
                            break OUTER;
                        }
                        case "d" -> {
                            // Implement double down logic
                            System.out.println("Double down!");
                            player.addCard(deck.drawCard());
                            break OUTER;
                        }
                        case "sp" -> {
                            // Implement split logic
                            System.out.println("Split!");
                            player.splitHand();
                            System.out.println("Your hands: " + player.getHand());
                            break OUTER;
                        }
                        default -> System.out.println("Invalid choice. Please enter 'h', 's', 'd', or 'sp'.");
                    }
                }

                while (dealer.calculateScore() < 17) {
                    dealer.addCard(deck.drawCard());
                }

                System.out.println("\nYour hand: " + player.getHand() + " (Score: " + player.calculateScore() + ")");
                System.out.println("Dealer's hand: " + dealer.getHand() + " (Score: " + dealer.calculateScore() + ")");

                determineWinner();

                System.out.println("\nDo you want to play another round? (yes/no)");
                String input = scanner.nextLine().toLowerCase();

                if (!input.equals("yes")) {
                    System.out.println("Goodbye! Thanks for playing, " + player.getName() + ".");
                    break;
                }
            }
        }
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
        Blackjack blackjackGame = new Blackjack();
        blackjackGame.playGame();
    }
}
