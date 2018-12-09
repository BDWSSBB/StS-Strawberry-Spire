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
    public static final String NAME = CARD_STRINGS.NAME;
    public static final String FLASHBACK_IMAGE_PATH = "cards/colorless/flashback.png";
    public static final String INVERT_IMAGE_PATH = "cards/colorless/invert.png";
    public static final String DUALITY_IMAGE_PATH = "cards/colorless/duality.png";
    public static final String MONOCHROME_IMAGE_PATH = "cards/colorless/monochrome.png";
    private static final int COST = 0;
    public static final String DESCRIPTION = CARD_STRINGS.DESCRIPTION;
    public static final String UPGRADE_DESCRIPTION = CARD_STRINGS.UPGRADE_DESCRIPTION;
    private static final int FLASHBACK_BLOCK_NUMBER = 5;
    private static final int INVERT_MAGIC_NUMBER = 3;
    private static final int DUALITY_MAGIC_NUMBER = 1;

    public Flashback() {
        super(ID, NAME, FLASHBACK_IMAGE_PATH, COST, DESCRIPTION, AbstractCard.CardType.POWER, AbstractCard.CardColor.COLORLESS, AbstractCard.CardRarity.RARE, AbstractCard.CardTarget.SELF);
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
                this.name = CARD_STRINGS.EXTENDED_DESCRIPTION[0];
                this.rawDescription = CARD_STRINGS.EXTENDED_DESCRIPTION[1];
                this.loadCardImage(INVERT_IMAGE_PATH);
                this.textureImg = INVERT_IMAGE_PATH;
                this.baseBlock = this.block = 0;
                this.magicNumber = this.baseMagicNumber = INVERT_MAGIC_NUMBER;
            }
            else if (this.timesUpgraded == 1) {
                this.name = CARD_STRINGS.EXTENDED_DESCRIPTION[2];
                this.rawDescription = CARD_STRINGS.EXTENDED_DESCRIPTION[3];
                this.loadCardImage(DUALITY_IMAGE_PATH);
                this.textureImg = DUALITY_IMAGE_PATH;
                this.baseBlock = this.block = 0;
                this.magicNumber = this.baseMagicNumber = DUALITY_MAGIC_NUMBER;
            }
            else {
                this.name = CARD_STRINGS.EXTENDED_DESCRIPTION[4];
                this.rawDescription = CARD_STRINGS.EXTENDED_DESCRIPTION[5];
                this.loadCardImage(MONOCHROME_IMAGE_PATH);
                this.textureImg = MONOCHROME_IMAGE_PATH;
                this.baseBlock = this.block = 0;
                this.magicNumber = this.baseMagicNumber = 0;
            }
            this.timesUpgraded += 1;
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
