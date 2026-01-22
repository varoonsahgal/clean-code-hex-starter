package com.cleancodehex.ebp.blackjack.adapter.in.console;

import com.cleancodehex.ebp.blackjack.domain.Card;
import com.cleancodehex.ebp.blackjack.domain.Rank;
import com.cleancodehex.ebp.blackjack.domain.Suit;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class CardDisplayTest {

    public static final Suit DUMMY_SUIT = Suit.HEARTS;

    @Test
    public void displayTenAsString() {
        Card card = new Card(DUMMY_SUIT, Rank.TEN);
        assertThat(ConsoleCard.display(card)).isEqualTo("\u001B[31m┌─────────┐\u001B[1B\u001B[11D│10       │\u001B[1B\u001B[11D│         │\u001B[1B\u001B[11D│    ♥    │\u001B[1B\u001B[11D│         │\u001B[1B\u001B[11D│       10│\u001B[1B\u001B[11D└─────────┘");
    }

    @Test
    public void displayNonTenAsString() {
        Card card = new Card(DUMMY_SUIT, Rank.QUEEN);
        assertThat(ConsoleCard.display(card)).isEqualTo("\u001B[31m┌─────────┐\u001B[1B\u001B[11D│Q        │\u001B[1B\u001B[11D│         │\u001B[1B\u001B[11D│    ♥    │\u001B[1B\u001B[11D│         │\u001B[1B\u001B[11D│        Q│\u001B[1B\u001B[11D└─────────┘");
    }
}
