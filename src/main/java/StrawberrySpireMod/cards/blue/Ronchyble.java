package StrawberrySpireMod.cards.blue;

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

public class Ronchyble extends AbstractStrawberrySpireCard {

    public static final String ID = "strawberrySpire:Ronchyble";
    private static final CardStrings CARD_STRINGS = CardCrawlGame.languagePack.getCardStrings(ID);
    public static final String NAME = CARD_STRINGS.NAME;
    public static final String IMAGE_PATH = "StrawberrySpireModResources/cards/blue/ronchyble.png";
    private static final int COST = 1;
    public static final String DESCRIPTION = CARD_STRINGS.DESCRIPTION;
    private static final int MAGIC_NUMBER = 2;
    private static final int UPGRADE_PLUS_MAGIC_NUMBER = 1;

    public Ronchyble() {
        super(ID, NAME, IMAGE_PATH, COST, DESCRIPTION, AbstractCard.CardType.POWER, AbstractCard.CardColor.BLUE, AbstractCard.CardRarity.RARE, AbstractCard.CardTarget.SELF);
        this.magicNumber = this.baseMagicNumber = MAGIC_NUMBER;
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        AbstractCard burnTemplate = new Burn();
        burnTemplate.upgrade();
        AbstractDungeon.actionManager.addToBottom(new MakeTempCardInHandAction(burnTemplate.makeStatEquivalentCopy(), 2, false));
        AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(p, p, new RonchyblePower(p, this.magicNumber), this.magicNumber));
    }

    public AbstractCard makeCopy() {
        return new Ronchyble();
    }

    public void upgrade() {
        if (!this.upgraded) {
            this.upgradeName();
            initializeDescription();

            this.upgradeMagicNumber(UPGRADE_PLUS_MAGIC_NUMBER);
        }
    }
}
