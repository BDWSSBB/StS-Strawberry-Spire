package StrawberrySpireMod.cards.green;

import StrawberrySpireMod.actions.unique.ApplyDebuffAndInverseAction;
import StrawberrySpireMod.helpers.ConfigHelper;
import com.megacrit.cardcrawl.actions.common.*;
import com.megacrit.cardcrawl.cards.*;
import com.megacrit.cardcrawl.characters.*;
import com.megacrit.cardcrawl.core.*;
import com.megacrit.cardcrawl.dungeons.*;
import com.megacrit.cardcrawl.localization.*;
import com.megacrit.cardcrawl.monsters.*;
import com.megacrit.cardcrawl.powers.*;

import StrawberrySpireMod.cards.*;
import StrawberrySpireMod.powers.*;

public class Pant extends AbstractStrawberrySpireCard {

    public static final String ID = "strawberrySpire:Pant";
    private static final CardStrings CARD_STRINGS = CardCrawlGame.languagePack.getCardStrings(ID);
    public static final String NAME = CARD_STRINGS.NAME;
    public static final String UPGRADE_NAME = CARD_STRINGS.EXTENDED_DESCRIPTION[0];
    public static final String IMAGE_PATH = "StrawberrySpireModResources/cards/green/pant.png";
    private static final int COST = 0;
    public static final String DESCRIPTION = CARD_STRINGS.DESCRIPTION;
    public static final String UPGRADE_DESCRIPTION = CARD_STRINGS.UPGRADE_DESCRIPTION;
    private static final int MAGIC_NUMBER = 2;

    public Pant() {
        super(ID, NAME, IMAGE_PATH, COST, DESCRIPTION, AbstractCard.CardType.SKILL, AbstractCard.CardColor.GREEN, AbstractCard.CardRarity.COMMON, AbstractCard.CardTarget.NONE);
        this.magicNumber = this.baseMagicNumber = MAGIC_NUMBER;
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        AbstractDungeon.actionManager.addToBottom(new ApplyDebuffAndInverseAction(p, p, new StrengthPower(p, -this.magicNumber), -this.magicNumber,  new GainStrengthPower(p, this.magicNumber), this.magicNumber));
        AbstractDungeon.actionManager.addToBottom(new ApplyDebuffAndInverseAction(p, p, new DexterityPower(p, -this.magicNumber), -this.magicNumber,  new GainDexterityPower(p, this.magicNumber), this.magicNumber));
        if (this.upgraded) {
            AbstractDungeon.actionManager.addToBottom(new GainEnergyAction(2));
        }
        else {
            AbstractDungeon.actionManager.addToBottom(new GainEnergyAction(1));
        }
    }

    public AbstractCard makeCopy() {
        return new Pant();
    }

    public void upgrade() {
        if (!this.upgraded) {
            this.upgradeName();
            if (ConfigHelper.useSpecialUpgradeNames) {
                this.name = UPGRADE_NAME;
                initializeTitle();
            }
            this.rawDescription = UPGRADE_DESCRIPTION;
            initializeDescription();
        }
    }
}