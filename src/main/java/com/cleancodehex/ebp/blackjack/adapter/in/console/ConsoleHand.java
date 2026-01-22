package com.cleancodehex.ebp.blackjack.adapter.in.console;

import com.cleancodehex.ebp.blackjack.domain.Hand;

import java.util.stream.Collectors;

import static org.fusesource.jansi.Ansi.ansi;

public class ConsoleHand {
    private final Hand hand;

    public ConsoleHand(Hand hand) {
        this.hand = hand;
    }

    public String displayFaceUpCard() {
        return ConsoleCard.display(hand.getCards().get(0));
    }

    public void display() {
        System.out.println(hand.getCards().stream()
                                .map(ConsoleCard::display)
                                .collect(Collectors.joining(
                                        ansi().cursorUp(6).cursorRight(1).toString())));
    }

    public String displayValue() {
        return String.valueOf(hand.value());
    }
}
