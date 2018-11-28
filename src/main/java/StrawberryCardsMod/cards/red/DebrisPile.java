package StrawberryCardsMod.cards.red;

import com.megacrit.cardcrawl.actions.common.*;
import com.megacrit.cardcrawl.cards.*;
import com.megacrit.cardcrawl.characters.*;
import com.megacrit.cardcrawl.core.*;
import com.megacrit.cardcrawl.dungeons.*;
import com.megacrit.cardcrawl.localization.*;
import com.megacrit.cardcrawl.monsters.*;

import StrawberryCardsMod.cards.*;
import StrawberryCardsMod.powers.*;

public class DebrisPile extends AbstractStrawberryCardsCard {

    public static final String ID = "strawberryCards:DebrisPile";
    private static final CardStrings CARD_STRINGS = CardCrawlGame.languagePack.getCardStrings(ID);
    public static final String NAME = CARD_STRINGS.NAME;
    public static final String IMAGE_PATH = "cards/red/debrisPile.png";
    private static final int COST = 1;
    public static final String DESCRIPTION = CARD_STRINGS.DESCRIPTION;
    private static final int MAGIC_NUMBER = 3;
    private static final int UPGRADE_PLUS_MAGIC_NUMBER = 1;

    public DebrisPile() {
        super(ID, NAME, IMAGE_PATH, COST, DESCRIPTION, AbstractCard.CardType.SKILL, AbstractCard.CardColor.RED, AbstractCard.CardRarity.COMMON, AbstractCard.CardTarget.SELF);
        this.magicNumber = this.baseMagicNumber = MAGIC_NUMBER;
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(p, p, new DebrisPilePower(p, this.magicNumber), this.magicNumber));
    }

    public AbstractCard makeCopy() {
        return new DebrisPile();
    }

    public void upgrade() {
        if (!this.upgraded) {
            this.upgradeName();

            this.upgradeMagicNumber(UPGRADE_PLUS_MAGIC_NUMBER);
        }
    }
}
