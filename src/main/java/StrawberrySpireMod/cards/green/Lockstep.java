package StrawberrySpireMod.cards.green;

import com.megacrit.cardcrawl.actions.common.*;
import com.megacrit.cardcrawl.cards.*;
import com.megacrit.cardcrawl.characters.*;
import com.megacrit.cardcrawl.core.*;
import com.megacrit.cardcrawl.dungeons.*;
import com.megacrit.cardcrawl.localization.*;
import com.megacrit.cardcrawl.monsters.*;

import StrawberrySpireMod.cards.*;
import StrawberrySpireMod.powers.*;

public class Lockstep extends AbstractStrawberrySpireCard {

    public static final String ID = "strawberrySpire:Lockstep";
    private static final CardStrings CARD_STRINGS = CardCrawlGame.languagePack.getCardStrings(ID);
    public static final String NAME = CARD_STRINGS.NAME;
    public static final String IMAGE_PATH = "cards/green/lockstep.png";
    private static final int COST = 0;
    public static final String DESCRIPTION = CARD_STRINGS.DESCRIPTION;
    public static final String UPGRADE_DESCRIPTION = CARD_STRINGS.UPGRADE_DESCRIPTION;

    public Lockstep() {
        super(ID, NAME, IMAGE_PATH, COST, DESCRIPTION, AbstractCard.CardType.POWER, AbstractCard.CardColor.GREEN, AbstractCard.CardRarity.RARE, AbstractCard.CardTarget.SELF);
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        if (this.upgraded) {
            AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(p, p, new LockstepOnBeatPower(p)));
            if (p.hasPower(LockstepOffBeatPower.POWER_ID)) {
                AbstractDungeon.actionManager.addToBottom(new RemoveSpecificPowerAction(p, p, LockstepOffBeatPower.POWER_ID));
            }
        }
        else {
            AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(p, p, new LockstepOffBeatPower(p)));
            if (p.hasPower(LockstepOnBeatPower.POWER_ID)) {
                AbstractDungeon.actionManager.addToBottom(new RemoveSpecificPowerAction(p, p, LockstepOnBeatPower.POWER_ID));
            }
        }
    }

    public AbstractCard makeCopy() {
        return new Lockstep();
    }

    public void upgrade() {
        if (!this.upgraded) {
            this.upgradeName();
            this.rawDescription = UPGRADE_DESCRIPTION;
            initializeDescription();
        }
    }
}