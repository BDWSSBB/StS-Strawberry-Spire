package StrawberryCardsMod.cards.green;

import com.megacrit.cardcrawl.cards.*;
import com.megacrit.cardcrawl.characters.*;
import com.megacrit.cardcrawl.core.*;
import com.megacrit.cardcrawl.dungeons.*;
import com.megacrit.cardcrawl.localization.*;
import com.megacrit.cardcrawl.monsters.*;

import StrawberryCardsMod.actions.unique.*;
import StrawberryCardsMod.cards.*;

public class Urgency extends AbstractStrawberryCardsCard {

    public static final String ID = "strawberryCards:Urgency";
    private static final CardStrings CARD_STRINGS = CardCrawlGame.languagePack.getCardStrings(ID);
    public static final String NAME = CARD_STRINGS.NAME;
    public static final String IMAGE_PATH = "cards/green/urgency.png";
    private static final int COST = 0;
    public static final String DESCRIPTION = CARD_STRINGS.DESCRIPTION;
    private static final int MAGIC_NUMBER = 2;
    private static final int UPGRADE_PLUS_MAGIC_NUMBER = 1;

    public Urgency() {
        super(ID, NAME, IMAGE_PATH, COST, DESCRIPTION, AbstractCard.CardType.SKILL, AbstractCard.CardColor.GREEN, AbstractCard.CardRarity.UNCOMMON, AbstractCard.CardTarget.ENEMY);
        this.magicNumber = this.baseMagicNumber = MAGIC_NUMBER;
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        AbstractDungeon.actionManager.addToBottom(new UrgencyAction(m, this.magicNumber));
    }

    public AbstractCard makeCopy() {
        return new Urgency();
    }

    public void upgrade() {
        if (!this.upgraded) {
            this.upgradeName();

            this.upgradeMagicNumber(UPGRADE_PLUS_MAGIC_NUMBER);
        }
    }
}
