package StrawberrySpireMod.cards.red;

import StrawberrySpireMod.helpers.ConfigHelper;
import com.megacrit.cardcrawl.actions.common.*;
import com.megacrit.cardcrawl.cards.*;
import com.megacrit.cardcrawl.cards.status.*;
import com.megacrit.cardcrawl.characters.*;
import com.megacrit.cardcrawl.core.*;
import com.megacrit.cardcrawl.dungeons.*;
import com.megacrit.cardcrawl.localization.*;
import com.megacrit.cardcrawl.monsters.*;

import StrawberrySpireMod.cards.*;
import StrawberrySpireMod.powers.*;

public class Conflagrate extends AbstractStrawberrySpireCard {

    public static final String ID = "strawberrySpire:Conflagrate";
    private static final CardStrings CARD_STRINGS = CardCrawlGame.languagePack.getCardStrings(ID);
    public static final String NAME = CARD_STRINGS.NAME;
    public static final String UPGRADE_NAME = CARD_STRINGS.EXTENDED_DESCRIPTION[0];
    public static final String IMAGE_PATH = "StrawberrySpireModResources/cards/red/conflagrate.png";
    private static final int COST = 1;
    public static final String DESCRIPTION = CARD_STRINGS.DESCRIPTION;
    private static final int MAGIC_NUMBER = 7;
    private static final int UPGRADE_PLUS_MAGIC_NUMBER = 3;

    public Conflagrate() {
        super(ID, NAME, IMAGE_PATH, COST, DESCRIPTION, AbstractCard.CardType.POWER, AbstractCard.CardColor.RED, AbstractCard.CardRarity.UNCOMMON, AbstractCard.CardTarget.SELF);
        this.magicNumber = this.baseMagicNumber = MAGIC_NUMBER;
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        AbstractDungeon.actionManager.addToBottom(new MakeTempCardInDrawPileAction(new Burn(), 2, true, true));
        AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(p, p, new ConflagratePower(p, this.magicNumber), this.magicNumber));
    }

    public AbstractCard makeCopy() {
        return new Conflagrate();
    }

    public void upgrade() {
        if (!this.upgraded) {
            this.upgradeName();
            if (ConfigHelper.useSpecialUpgradeNames) {
                this.name = UPGRADE_NAME;
                initializeTitle();
            }
            this.upgradeMagicNumber(UPGRADE_PLUS_MAGIC_NUMBER);
        }
    }
}
