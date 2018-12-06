package StrawberrySpireMod.cards.blue;

import com.megacrit.cardcrawl.cards.*;
import com.megacrit.cardcrawl.characters.*;
import com.megacrit.cardcrawl.core.*;
import com.megacrit.cardcrawl.dungeons.*;
import com.megacrit.cardcrawl.localization.*;
import com.megacrit.cardcrawl.monsters.*;

import StrawberrySpireMod.actions.unique.*;
import StrawberrySpireMod.cards.*;

public class Gammacceleration extends AbstractStrawberrySpireCard {

    public static final String ID = "strawberrySpire:Gammacceleration";
    private static final CardStrings CARD_STRINGS = CardCrawlGame.languagePack.getCardStrings(ID);
    public static final String NAME = CARD_STRINGS.NAME;
    public static final String UPGRADE_NAME = CARD_STRINGS.EXTENDED_DESCRIPTION[0];
    public static final String IMAGE_PATH = "cards/blue/gammacceleration.png";
    private static final int COST = -1;
    public static final String DESCRIPTION = CARD_STRINGS.DESCRIPTION;
    public static final String UPGRADE_DESCRIPTION = CARD_STRINGS.UPGRADE_DESCRIPTION;

    public Gammacceleration() {
        super(ID, NAME, IMAGE_PATH, COST, DESCRIPTION, AbstractCard.CardType.SKILL, AbstractCard.CardColor.BLUE, AbstractCard.CardRarity.RARE, AbstractCard.CardTarget.SELF);
        this.exhaust = true;
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        AbstractDungeon.actionManager.addToBottom(new GammaccelerationAction(this.upgraded , this.freeToPlayOnce, this.energyOnUse));
    }

    public AbstractCard makeCopy() {
        return new Gammacceleration();
    }

    public void upgrade() {
        if (!this.upgraded) {
            this.upgradeName();
            this.name = UPGRADE_NAME;
            this.rawDescription = UPGRADE_DESCRIPTION;
            initializeDescription();
        }
    }
}
