package com.cleancodehex.ebp.blackjack.adapter.in.console;

import com.cleancodehex.ebp.blackjack.domain.Card;
import com.cleancodehex.ebp.blackjack.domain.Hand;
import com.cleancodehex.ebp.blackjack.domain.Rank;
import com.cleancodehex.ebp.blackjack.domain.Suit;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

public class ConsoleHandDisplayTest {

  @Test
  public void displayFaceUpCard() {
    Hand hand = new Hand(List.of(new Card(Suit.HEARTS, Rank.ACE)));
    ConsoleHand consoleHand = new ConsoleHand(hand);

    assertThat(consoleHand.displayFaceUpCard())
        .isNotEmpty();
  }
}
