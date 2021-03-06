package StrawberrySpireMod.cards.blue;

import StrawberrySpireMod.helpers.ConfigHelper;
import com.megacrit.cardcrawl.actions.common.*;
import com.megacrit.cardcrawl.cards.*;
import com.megacrit.cardcrawl.characters.*;
import com.megacrit.cardcrawl.core.*;
import com.megacrit.cardcrawl.dungeons.*;
import com.megacrit.cardcrawl.localization.*;
import com.megacrit.cardcrawl.monsters.*;

import StrawberrySpireMod.cards.*;
import StrawberrySpireMod.powers.*;

public class AngeryBot extends AbstractStrawberrySpireCard {

    public static final String ID = "strawberrySpire:AngeryBot";
    private static final CardStrings CARD_STRINGS = CardCrawlGame.languagePack.getCardStrings(ID);
    public static final String NAME = CARD_STRINGS.NAME;
    public static final String UPGRADE_NAME = CARD_STRINGS.EXTENDED_DESCRIPTION[0];
    public static final String IMAGE_PATH = "StrawberrySpireModResources/cards/blue/angeryBot.png";
    private static final int COST = 1;
    public static final String DESCRIPTION = CARD_STRINGS.DESCRIPTION;
    public static final String UPGRADE_DESCRIPTION = CARD_STRINGS.UPGRADE_DESCRIPTION;
    private static final int MAGIC_NUMBER = 1;

    public AngeryBot() {
        super(ID, NAME, IMAGE_PATH, COST, DESCRIPTION, AbstractCard.CardType.POWER, AbstractCard.CardColor.BLUE, CardRarity.UNCOMMON, AbstractCard.CardTarget.SELF);
        this.magicNumber = this.baseMagicNumber = MAGIC_NUMBER;
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(p, p, new AngeryBotPower(p, this.magicNumber), this.magicNumber));
    }

    public AbstractCard makeCopy() {
        return new AngeryBot();
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

            this.isInnate = true;
        }
    }
}