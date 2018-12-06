package StrawberrySpireMod.cards.curses;

import com.megacrit.cardcrawl.actions.common.*;
import com.megacrit.cardcrawl.actions.utility.*;
import com.megacrit.cardcrawl.cards.*;
import com.megacrit.cardcrawl.characters.*;
import com.megacrit.cardcrawl.dungeons.*;
import com.megacrit.cardcrawl.monsters.*;
import com.megacrit.cardcrawl.relics.*;

import StrawberrySpireMod.cards.*;
import StrawberrySpireMod.powers.*;

public class Annoyance extends AbstractStrawberrySpireCard { // Not actually added into the game for everyone's sanity.

    public static final String ID = "strawberrySpire:Annoyance";
    public static final String NAME = "Annoyance";
    public static final String IMAGE_PATH = "cards/placeholder/blankSkill.png";
    private static final int COST = -2;
    public static final String DESCRIPTION = "Unplayable. NL Whenever you draw this card, Exhaust it and become annoying.";

    public Annoyance() {
        super(ID, NAME, IMAGE_PATH, COST, DESCRIPTION, AbstractCard.CardType.CURSE, AbstractCard.CardColor.CURSE, AbstractCard.CardRarity.CURSE, AbstractCard.CardTarget.NONE);
    }

    public void triggerWhenDrawn()
    {
        AbstractDungeon.actionManager.addToBottom(new ExhaustSpecificCardAction(this, AbstractDungeon.player.hand));
        AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(AbstractDungeon.player, AbstractDungeon.player, new AnnoyancePower(AbstractDungeon.player)));
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        if (p.hasRelic(BlueCandle.ID)) {
            useBlueCandle(p);
        } else {
            AbstractDungeon.actionManager.addToBottom(new UseCardAction(this));
        }
    }

    public AbstractCard makeCopy() {
        return new Annoyance();
    }

    public void upgrade() {

    }
}