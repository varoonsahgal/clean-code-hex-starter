package com.cleancodehex.ebp.blackjack.domain;

// what should Game be responsible for in a BlackJack game?
// it runs the game loop - the interaction between player and dealer
// it's the orchestrator!

public class Game {

    private final Deck deck;

    private final Hand dealerHand = new Hand();
    private final Hand playerHand = new Hand();

    public Game() {
        deck = new Deck();
    }

    public Game(Deck deck) {
        this.deck = deck;
    }

    public void initialDeal() {
        dealRoundOfCards();
        dealRoundOfCards();
    }

    public void play(PlayerInputProvider inputProvider) {
        playerTurn(inputProvider);
        dealerTurn();
    }

    public void playerHitRequest() {
        playerHand.drawFrom(deck);
    }

    public void playerStands() {
        // Player has decided to stand
    }

    public void dealerTakeTurn() {
        dealerTurn();
    }

    public Hand getDealerHand() {
        return dealerHand;
    }

    public Hand getPlayerHand() {
        return playerHand;
    }

    private void dealRoundOfCards() {
        // why: players first because this is the rule
        playerHand.drawFrom(deck);
        dealerHand.drawFrom(deck);
    }

    public String determineOutcome() {
        if (playerHand.isBusted()) {
            return "player busted";
        } else if (dealerHand.isBusted()) {
            return "dealer busted";
        } else if (playerHand.beats(dealerHand)) {
            return "player wins";
        } else if (playerHand.pushes(dealerHand)) {
            return "push";
        } else {
            return "dealer wins";
        }
    }

    private void dealerTurn() {
        // Dealer makes its choice automatically based on a simple heuristic (<=16 must hit, =>17 must stand)
        if (!playerHand.isBusted()) {
            while (dealerHand.dealerMustDrawCard()) {
                dealerHand.drawFrom(deck);
            }
        }
    }

    private void playerTurn(PlayerInputProvider inputProvider) {
        while (!playerHand.isBusted()) {
            String playerChoice = inputProvider.getPlayerChoice().toLowerCase();
            if (playerChoice.startsWith("s")) {
                break;
            }
            if (playerChoice.startsWith("h")) {
                playerHand.drawFrom(deck);
            }
        }
    }

    public interface PlayerInputProvider {
        String getPlayerChoice();
    }
}

