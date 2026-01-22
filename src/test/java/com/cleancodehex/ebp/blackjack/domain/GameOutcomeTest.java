package com.cleancodehex.ebp.blackjack.domain;

import org.junit.jupiter.api.Test;

import java.util.Iterator;
import java.util.List;

import static org.assertj.core.api.Assertions.*;

public class GameOutcomeTest {

    @Test
    public void playerBeatsDealer() {
        Deck stubDeck = new StubDeck();

        // by calling this constructor it will
        // call shuffle which introduces randomness
        // which means that we cannot guarantee the order of cards:
        //Deck mytestdeck = new Deck();


        Game game = new Game(stubDeck);
        game.initialDeal();

        game.playerStands();   // ensure player does not take more cards
        game.dealerTakeTurn();     // dealer completes its turn

        String outcome = game.determineOutcome();

        assertThat(outcome)
                .isEqualTo("player wins");
    }

    private class StubDeck extends Deck {

        private final List<Card> cards = List.of(
                new Card(Suit.HEARTS, Rank.TEN),    // Player card 1
                new Card(Suit.SPADES, Rank.SIX),    // Dealer card 1
                new Card(Suit.DIAMONDS, Rank.NINE), // Player card 2
                new Card(Suit.CLUBS, Rank.FIVE),    // Dealer card 2
                new Card(Suit.HEARTS, Rank.SEVEN)     // Dealer card 3 (if needed)
        );

        private final Iterator<Card> iterator = cards.iterator();
        @Override
        public Card draw() {
            return iterator.next();
        }
    }
}
