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
import StrawberrySpireMod.powers.*;

public class Neurons extends AbstractStrawberrySpireCard {

    public static final String ID = "strawberrySpire:Neurons";
    private static final CardStrings CARD_STRINGS = CardCrawlGame.languagePack.getCardStrings(ID);
    public static final String NAME = CARD_STRINGS.NAME;
    public static final String UPGRADE_NAME = "Newrons";
    public static final String IMAGE_PATH = "StrawberrySpireModResources/cards/colorless/neurons.png";
    private static final int COST = 3;
    private static final int UPGRADE_COST = 2;
    public static final String DESCRIPTION = CARD_STRINGS.DESCRIPTION;

    public Neurons() {
        super(ID, NAME, IMAGE_PATH, COST, DESCRIPTION, AbstractCard.CardType.POWER, AbstractCard.CardColor.COLORLESS, AbstractCard.CardRarity.RARE, CardTarget.SELF);
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(p, p, new ConfusionPower(p)));
        AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(p, p, new NeuronsPower(p)));
    }

    public AbstractCard makeCopy() {
        return new Neurons();
    }

    public void upgrade() {
        if (!this.upgraded) {
            this.upgradeName();
            this.name = UPGRADE_NAME;

            this.upgradeBaseCost(UPGRADE_COST);
        }
    }
}