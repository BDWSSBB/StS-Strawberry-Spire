package StrawberrySpireMod.cards.red;

import com.badlogic.gdx.math.MathUtils;
import com.megacrit.cardcrawl.actions.animations.*;
import com.megacrit.cardcrawl.actions.common.*;
import com.megacrit.cardcrawl.actions.utility.*;
import com.megacrit.cardcrawl.cards.*;
import com.megacrit.cardcrawl.characters.*;
import com.megacrit.cardcrawl.core.*;
import com.megacrit.cardcrawl.dungeons.*;
import com.megacrit.cardcrawl.localization.*;
import com.megacrit.cardcrawl.monsters.*;

import StrawberrySpireMod.cards.*;

public class DraftPunk extends AbstractStrawberrySpireCard {

    public static final String ID = "strawberrySpire:DraftPunk";
    private static final CardStrings CARD_STRINGS = CardCrawlGame.languagePack.getCardStrings(ID);
    public static final String NAME = CARD_STRINGS.NAME;
    public static final String IMAGE_PATH = "StrawberrySpireModResources/cards/red/draftPunk.png";
    private static final int COST = 1;
    public static final String DESCRIPTION = CARD_STRINGS.DESCRIPTION;
    private static final int MAGIC_NUMBER = 2;
    private static final int UPGRADE_PLUS_MAGIC_NUMBER = 1;

    public DraftPunk() {
        super(ID, NAME, IMAGE_PATH, COST, DESCRIPTION, AbstractCard.CardType.SKILL, AbstractCard.CardColor.RED, AbstractCard.CardRarity.COMMON, AbstractCard.CardTarget.NONE);
        this.magicNumber = this.baseMagicNumber = MAGIC_NUMBER;
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        if (MathUtils.randomBoolean(0.003F)) {
            AbstractDungeon.actionManager.addToBottom(new TalkAction(true, "Burger NL Nuggets NL Nuggets NL Burger", 0.1F, 2.0F));
        }
        else if (MathUtils.randomBoolean(0.03F)) {
            AbstractDungeon.actionManager.addToBottom(new TalkAction(true, "Harder NL Better NL Faster NL Stronger", 0.1F, 2.0F));
        }
        AbstractDungeon.actionManager.addToBottom(new ExhaustAction(p, p, this.magicNumber, true, false, false));
        AbstractDungeon.actionManager.addToBottom(new WaitAction(0.25F));
        for (int i = 0; i < this.magicNumber; i++) {
            AbstractDungeon.actionManager.addToBottom(new MakeTempCardInHandAction(AbstractDungeon.returnTrulyRandomCardInCombat().makeCopy(), false));
        }
    }

    public AbstractCard makeCopy() {
        return new DraftPunk();
    }

    public void upgrade() {
        if (!this.upgraded) {
            this.upgradeName();

            this.upgradeMagicNumber(UPGRADE_PLUS_MAGIC_NUMBER);
        }
    }
}