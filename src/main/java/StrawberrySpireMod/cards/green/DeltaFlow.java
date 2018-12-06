package StrawberrySpireMod.cards.green;

import com.megacrit.cardcrawl.actions.common.*;
import com.megacrit.cardcrawl.cards.*;
import com.megacrit.cardcrawl.characters.*;
import com.megacrit.cardcrawl.core.*;
import com.megacrit.cardcrawl.dungeons.*;
import com.megacrit.cardcrawl.localization.*;
import com.megacrit.cardcrawl.monsters.*;
import com.megacrit.cardcrawl.powers.*;

import StrawberrySpireMod.cards.*;

public class DeltaFlow extends AbstractStrawberrySpireCard {

    public static final String ID = "strawberrySpire:DeltaFlow";
    private static final CardStrings CARD_STRINGS = CardCrawlGame.languagePack.getCardStrings(ID);
    public static final String NAME = CARD_STRINGS.NAME;
    public static final String IMAGE_PATH = "cards/green/deltaFlow.png";
    private static final int COST = 1;
    public static final String DESCRIPTION = CARD_STRINGS.DESCRIPTION;
    public static final String UPGRADE_DESCRIPTION = CARD_STRINGS.UPGRADE_DESCRIPTION;
    private static final int MAGIC_NUMBER = 1;
    private static final int UPGRADE_PLUS_MAGIC_NUMBER = 1;

    public DeltaFlow() {
        super(ID, NAME, IMAGE_PATH, COST, DESCRIPTION, AbstractCard.CardType.SKILL, AbstractCard.CardColor.GREEN, AbstractCard.CardRarity.COMMON, AbstractCard.CardTarget.SELF);
        this.magicNumber = this.baseMagicNumber = MAGIC_NUMBER;
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        AbstractDungeon.actionManager.addToBottom(new DrawCardAction(p, 2));
        AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(p, p, new DrawCardNextTurnPower(p, this.magicNumber), this.magicNumber));
    }

    public AbstractCard makeCopy() {
        return new DeltaFlow();
    }

    public void upgrade() {
        if (!this.upgraded) {
            this.upgradeName();
            this.rawDescription = UPGRADE_DESCRIPTION;
            initializeDescription();

            this.upgradeMagicNumber(UPGRADE_PLUS_MAGIC_NUMBER);
        }
    }
}
