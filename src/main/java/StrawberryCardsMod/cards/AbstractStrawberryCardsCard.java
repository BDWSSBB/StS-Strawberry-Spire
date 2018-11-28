package StrawberryCardsMod.cards;

import com.megacrit.cardcrawl.cards.*;

import basemod.abstracts.*;

public abstract class AbstractStrawberryCardsCard extends CustomCard {

    public AbstractStrawberryCardsCard(
            final String id,
            final String name,
            final String imagePath,
            final int cost,
            final String rawDescription,
            final AbstractCard.CardType type,
            final AbstractCard.CardColor color,
            final AbstractCard.CardRarity rarity,
            final AbstractCard.CardTarget target) {
        super(id, name, imagePath, cost, rawDescription, type, color, rarity, target);
    }

    public void triggerAfterCardPlayed() {}

    public void triggerAtEndOfTurnFromAnywhere() {}
}
