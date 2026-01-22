package com.cleancodehex.ebp.blackjack;

import java.util.stream.Collectors;

import static org.fusesource.jansi.Ansi.ansi;

public class ConsoleHand {
    public static String displayFaceUpCard(Hand hand) {
        return ConsoleCard.display(hand.getCards().get(0));
    }

    public static String cardsAsString(Hand hand) {
        return hand.getCards().stream()
                   .map(ConsoleCard::display)
                   .collect(Collectors.joining(
                                 ansi().cursorUp(6).cursorRight(1).toString()));
    }
}
