package StrawberrySpireMod.cards.colorless;

import com.megacrit.cardcrawl.actions.common.*;
import com.megacrit.cardcrawl.cards.*;
import com.megacrit.cardcrawl.characters.*;
import com.megacrit.cardcrawl.core.*;
import com.megacrit.cardcrawl.dungeons.*;
import com.megacrit.cardcrawl.localization.*;
import com.megacrit.cardcrawl.monsters.*;
import com.megacrit.cardcrawl.powers.*;

import StrawberrySpireMod.cards.*;

public class Flashback extends AbstractStrawberrySpireCard {

    public static final String ID = "strawberrySpire:Flashback";
    private static final CardStrings CARD_STRINGS = CardCrawlGame.languagePack.getCardStrings(ID);
    public static final String FLASHBACK_NAME = CARD_STRINGS.NAME;
    public static final String INVERT_NAME = CARD_STRINGS.EXTENDED_DESCRIPTION[0];
    public static final String DUALITY_NAME = CARD_STRINGS.EXTENDED_DESCRIPTION[2];
    public static final String MONOCHROME_NAME = CARD_STRINGS.EXTENDED_DESCRIPTION[4];
    public static final String FLASHBACK_IMAGE_PATH = "StrawberrySpireMod/cards/colorless/flashback.png";
    public static final String INVERT_IMAGE_PATH = "StrawberrySpireMod/cards/colorless/invert.png";
    public static final String DUALITY_IMAGE_PATH = "StrawberrySpireMod/cards/colorless/duality.png";
    public static final String MONOCHROME_IMAGE_PATH = "StrawberrySpireMod/cards/colorless/monochrome.png";
    private static final int COST = 0;
    public static final String FLASHBACK_DESCRIPTION = CARD_STRINGS.DESCRIPTION;
    public static final String INVERT_DESCRIPTION = CARD_STRINGS.EXTENDED_DESCRIPTION[1];
    public static final String DUALITY_DESCRIPTION = CARD_STRINGS.EXTENDED_DESCRIPTION[3];
    public static final String MONOCHROME_DESCRIPTION = CARD_STRINGS.EXTENDED_DESCRIPTION[5];
    private static final int FLASHBACK_BLOCK_NUMBER = 5;
    private static final int INVERT_MAGIC_NUMBER = 3;
    private static final int DUALITY_MAGIC_NUMBER = 1;

    public Flashback() {
        super(ID, FLASHBACK_NAME, FLASHBACK_IMAGE_PATH, COST, FLASHBACK_DESCRIPTION, AbstractCard.CardType.POWER, AbstractCard.CardColor.COLORLESS, AbstractCard.CardRarity.RARE, AbstractCard.CardTarget.SELF);
        this.block = this.baseBlock = FLASHBACK_BLOCK_NUMBER;
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        if (this.timesUpgraded == 0) {
            AbstractDungeon.actionManager.addToBottom(new GainBlockAction(p, p, this.block));
        }
        else if (this.timesUpgraded == 1) {
            AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(p, p, new MetallicizePower(p, this.magicNumber), this.magicNumber));
        }
        else if (this.timesUpgraded == 2) {
            AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(p, p, new AfterImagePower(p, this.magicNumber), this.magicNumber));
        }
        else {
            AbstractCard temp = this.makeCopy();
            for (int i = 0; i < 2; i++) {
                temp.upgrade();
            }
            AbstractDungeon.actionManager.addToBottom(new MakeTempCardInHandAction(temp));
            temp = this.makeCopy();
            temp.upgrade();
            AbstractDungeon.actionManager.addToBottom(new MakeTempCardInHandAction(temp));
            temp = this.makeCopy();
            AbstractDungeon.actionManager.addToBottom(new MakeTempCardInHandAction(temp));
        }
    }

    public AbstractCard makeCopy() {
        return new Flashback();
    }

    public void upgrade() {
        if (this.canUpgrade()) {
            if (this.timesUpgraded == 0) {
                this.name = INVERT_NAME;
                this.rawDescription = INVERT_DESCRIPTION;
                this.loadCardImage(INVERT_IMAGE_PATH);
                this.textureImg = INVERT_IMAGE_PATH;
                this.baseBlock = this.block = -1;
                this.magicNumber = this.baseMagicNumber = INVERT_MAGIC_NUMBER;
            }
            else if (this.timesUpgraded == 1) {
                this.name = DUALITY_NAME;
                this.rawDescription = DUALITY_DESCRIPTION;
                this.loadCardImage(DUALITY_IMAGE_PATH);
                this.textureImg = DUALITY_IMAGE_PATH;
                this.baseBlock = this.block = -1;
                this.magicNumber = this.baseMagicNumber = DUALITY_MAGIC_NUMBER;
            }
            else {
                this.name = MONOCHROME_NAME;
                this.rawDescription = MONOCHROME_DESCRIPTION;
                this.loadCardImage(MONOCHROME_IMAGE_PATH);
                this.textureImg = MONOCHROME_IMAGE_PATH;
                this.baseBlock = this.block = -1;
                this.magicNumber = this.baseMagicNumber = -1;
            }
            this.timesUpgraded += 1;
            initializeTitle();
            initializeDescription();
        }
    }

    public boolean canUpgrade() {
        if (this.timesUpgraded < 3) {
            return true;
        }
        else {
            return false;
        }
    }
}
