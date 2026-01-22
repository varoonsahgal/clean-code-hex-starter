package com.cleancodehex.ebp.blackjack.adapter.in.console;

import com.cleancodehex.ebp.blackjack.domain.Card;
import com.cleancodehex.ebp.blackjack.domain.Rank;
import org.fusesource.jansi.Ansi;

import static org.fusesource.jansi.Ansi.ansi;

public class ConsoleCard {
    public static String display(Card card) {
        String[] lines = new String[7];
        lines[0] = "┌─────────┐";
        lines[1] = String.format("│%s%s       │", card.getRank().display(), card.getRank() == Rank.TEN ? "" : " ");
        lines[2] = "│         │";
        lines[3] = String.format("│    %s    │", card.getSuit().symbol());
        lines[4] = "│         │";
        lines[5] = String.format("│       %s%s│", card.getRank() == Rank.TEN ? "" : " ", card.getRank().display());
        lines[6] = "└─────────┘";

        Ansi.Color cardColor = card.getSuit().isRed() ? Ansi.Color.RED : Ansi.Color.BLACK;
        return ansi()
                .fg(cardColor).toString()
                + String.join(ansi().cursorDown(1)
                                    .cursorLeft(11)
                                    .toString(), lines);
    }
}
