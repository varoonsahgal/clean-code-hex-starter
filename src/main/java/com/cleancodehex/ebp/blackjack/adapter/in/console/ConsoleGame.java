package com.cleancodehex.ebp.blackjack.adapter.in.console;

import com.cleancodehex.ebp.blackjack.domain.Game;
import org.fusesource.jansi.Ansi;
import org.fusesource.jansi.AnsiConsole;

import static org.fusesource.jansi.Ansi.ansi;

// all of the display related logic in this class technically does not
// really belong to the domain of GAME, because Game should be UI agnostic
// and ONLY focused on business logic.
// imagine if we want to add a GUI or Web interface later - all this ANSI stuff
// would need to be removed/refactored out...or we might end up adding code
// that talks to the web in this file as well...

// what we want to do instead is maintain the Business logic here AND ONLY
// the business logic
// this class - should NOT know anything about the external world...
// so the Game class would be INSIDE the hexagon, and the UI stuff would be OUTSIDE the hexagon
// in a separate class that we call an adapter..

public class ConsoleGame {

    private final Game game;
    private final ConsoleHand consoleDealer;
    private final ConsoleHand consolePlayer;

    public static void main(String[] args) {
        displayWelcomeScreen();
        waitForEnterFromUser();

        playGame();

        resetScreen();
    }

    private static void resetScreen() {
        System.out.println(ansi().reset());
    }

    private static void playGame() {
        ConsoleGame consoleGame = new ConsoleGame();
        consoleGame.initialDealAndPlay();
    }

    private static void waitForEnterFromUser() {
        System.out.println(ansi()
                                   .cursor(3, 1)
                                   .fgBrightBlack().a("Hit [ENTER] to start..."));

        System.console().readLine();
    }

    private static void displayWelcomeScreen() {
        AnsiConsole.systemInstall();
        System.out.println(ansi()
                                   .bgBright(Ansi.Color.WHITE)
                                   .eraseScreen()
                                   .cursor(1, 1)
                                   .fgGreen().a("Welcome to")
                                   .fgRed().a(" JitterTed's")
                                   .fgBlack().a(" BlackJack game"));
    }

    public ConsoleGame() {
        game = new Game();
        consoleDealer = new ConsoleHand(game.getDealerHand());
        consolePlayer = new ConsoleHand(game.getPlayerHand());
    }

    public void initialDealAndPlay() {
        game.initialDeal();
        playerTurnWithDisplay();
        game.getDealerHand().dealerMustDrawCard();
        dealerTurn();
        displayFinalGameState();
        displayOutcome();
    }

    private void dealerTurn() {
        if (!game.getPlayerHand().isBusted()) {
            while (game.getDealerHand().dealerMustDrawCard()) {
                game.getDealerHand().drawFrom(null);
            }
        }
    }

    private void playerTurnWithDisplay() {
        while (!game.getPlayerHand().isBusted()) {
            displayGameState();
            System.out.println("[H]it or [S]tand?");
            String playerChoice = new java.util.Scanner(System.in).nextLine().toLowerCase();
            if (playerChoice.startsWith("s")) {
                break;
            }
            if (playerChoice.startsWith("h")) {
                game.getPlayerHand().drawFrom(null);
            } else {
                System.out.println("You need to [H]it or [S]tand");
            }
        }
    }

    private void displayOutcome() {
        if (game.getPlayerHand().isBusted()) {
            System.out.println("You Busted, so you lose.  💸");
        } else if (game.getDealerHand().isBusted()) {
            System.out.println("Dealer went BUST, Player wins! Yay for you!! 💵");
        } else if (game.getPlayerHand().beats(game.getDealerHand())) {
            System.out.println("You beat the Dealer! 💵");
        } else if (game.getPlayerHand().pushes(game.getDealerHand())) {
            System.out.println("Push: Nobody wins, we'll call it even.");
        } else {
            System.out.println("You lost to the Dealer. 💸");
        }
    }

    private void displayGameState() {
        System.out.print(ansi().eraseScreen().cursor(1, 1));
        System.out.println("Dealer has: ");
        System.out.println(consoleDealer.displayFaceUpCard());

        // second card is the hole card, which is hidden, or "face down"
        displayBackOfCard();

        System.out.println();
        System.out.println("Player has: ");
        consolePlayer.display();
        System.out.println(" (" + consolePlayer.displayValue() + ")");
    }

    private void displayBackOfCard() {
        System.out.print(
                ansi()
                        .cursorUp(7)
                        .cursorRight(12)
                        .a("┌─────────┐").cursorDown(1).cursorLeft(11)
                        .a("│░░░░░░░░░│").cursorDown(1).cursorLeft(11)
                        .a("│░ J I T ░│").cursorDown(1).cursorLeft(11)
                        .a("│░ T E R ░│").cursorDown(1).cursorLeft(11)
                        .a("│░ T E D ░│").cursorDown(1).cursorLeft(11)
                        .a("│░░░░░░░░░│").cursorDown(1).cursorLeft(11)
                        .a("└─────────┘"));
    }

    private void displayFinalGameState() {
        System.out.print(ansi().eraseScreen().cursor(1, 1));
        System.out.println("Dealer has: ");
        consoleDealer.display();
        System.out.println(" (" + consoleDealer.displayValue() + ")");

        System.out.println();
        System.out.println("Player has: ");
        consolePlayer.display();
        System.out.println(" (" + consolePlayer.displayValue() + ")");
    }
}
