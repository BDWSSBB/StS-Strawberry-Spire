package StrawberryCardsMod.cards.blue;

import com.megacrit.cardcrawl.cards.*;
import com.megacrit.cardcrawl.characters.*;
import com.megacrit.cardcrawl.core.*;
import com.megacrit.cardcrawl.dungeons.*;
import com.megacrit.cardcrawl.localization.*;
import com.megacrit.cardcrawl.monsters.*;

import StrawberryCardsMod.actions.unique.*;
import StrawberryCardsMod.cards.*;

public class NeonFlight extends AbstractStrawberryCardsCard {

    public static final String ID = "strawberryCards:NeonFlight";
    private static final CardStrings CARD_STRINGS = CardCrawlGame.languagePack.getCardStrings(ID);
    public static final String NAME = CARD_STRINGS.NAME;
    public static final String IMAGE_PATH = "cards/blue/neonFlight.png";
    private static final int COST = 1;
    public static final String DESCRIPTION = CARD_STRINGS.DESCRIPTION;
    private static final int BLOCK_AMOUNT = 5;
    private static final int UPGRADE_PLUS_BLOCK = 2;

    public NeonFlight() {
        super(ID, NAME, IMAGE_PATH, COST, DESCRIPTION, AbstractCard.CardType.SKILL, AbstractCard.CardColor.BLUE, AbstractCard.CardRarity.COMMON, AbstractCard.CardTarget.SELF);
        this.block = this.baseBlock = BLOCK_AMOUNT;
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        AbstractDungeon.actionManager.addToBottom(new NeonFlightAction(this.block));
    }

    public AbstractCard makeCopy() {
        return new NeonFlight();
    }

    public void upgrade() {
        if (!this.upgraded) {
            this.upgradeName();

            this.upgradeBlock(UPGRADE_PLUS_BLOCK);
        }
    }
}