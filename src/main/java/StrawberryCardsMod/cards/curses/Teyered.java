package StrawberryCardsMod.cards.curses;

import com.megacrit.cardcrawl.actions.common.*;
import com.megacrit.cardcrawl.actions.utility.*;
import com.megacrit.cardcrawl.cards.*;
import com.megacrit.cardcrawl.characters.*;
import com.megacrit.cardcrawl.core.*;
import com.megacrit.cardcrawl.dungeons.*;
import com.megacrit.cardcrawl.localization.*;
import com.megacrit.cardcrawl.monsters.*;
import com.megacrit.cardcrawl.powers.*;
import com.megacrit.cardcrawl.relics.*;

import StrawberryCardsMod.cards.*;

public class Teyered extends AbstractStrawberryCardsCard {

    public static final String ID = "strawberryCards:Teyered";
    private static final CardStrings CARD_STRINGS = CardCrawlGame.languagePack.getCardStrings(ID);
    public static final String NAME = CARD_STRINGS.NAME;
    public static final String IMAGE_PATH = "cards/curse/teyered.png";
    private static final int COST = -2;
    public static final String DESCRIPTION = CARD_STRINGS.DESCRIPTION;

    public Teyered() {
        super(ID, NAME, IMAGE_PATH, COST, DESCRIPTION, AbstractCard.CardType.CURSE, AbstractCard.CardColor.CURSE, AbstractCard.CardRarity.CURSE, AbstractCard.CardTarget.NONE);
    }

    public void triggerWhenDrawn()
    {
        AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(AbstractDungeon.player, AbstractDungeon.player, new NoDrawPower(AbstractDungeon.player)));
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        if (p.hasRelic(BlueCandle.ID)) {
            useBlueCandle(p);
        } else {
            AbstractDungeon.actionManager.addToBottom(new UseCardAction(this));
        }
    }

    public AbstractCard makeCopy() {
        return new Teyered();
    }

    public void upgrade() {

    }
}
