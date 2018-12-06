package StrawberrySpireMod.cards.blue;

import com.megacrit.cardcrawl.actions.defect.*;
import com.megacrit.cardcrawl.cards.*;
import com.megacrit.cardcrawl.characters.*;
import com.megacrit.cardcrawl.core.*;
import com.megacrit.cardcrawl.dungeons.*;
import com.megacrit.cardcrawl.localization.*;
import com.megacrit.cardcrawl.monsters.*;

import StrawberrySpireMod.cards.*;

public class Salvo extends AbstractStrawberrySpireCard {

    public static final String ID = "strawberrySpire:Salvo";
    private static final CardStrings CARD_STRINGS = CardCrawlGame.languagePack.getCardStrings(ID);
    public static final String NAME = CARD_STRINGS.NAME;
    public static final String IMAGE_PATH = "cards/blue/salvo.png";
    private static final int COST = 2;
    private static final int UPGRADE_COST = 1;
    public static final String DESCRIPTION = CARD_STRINGS.DESCRIPTION;

    public Salvo() {
        super(ID, NAME, IMAGE_PATH, COST, DESCRIPTION, AbstractCard.CardType.SKILL, AbstractCard.CardColor.BLUE, AbstractCard.CardRarity.UNCOMMON, AbstractCard.CardTarget.NONE);
        this.exhaust = true;
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        for (int i = 0; i < AbstractDungeon.player.orbs.size(); i++) {
            AbstractDungeon.actionManager.addToBottom(new AnimateOrbAction(1));
            AbstractDungeon.actionManager.addToBottom(new EvokeWithoutRemovingOrbAction(1));
            AbstractDungeon.actionManager.addToBottom(new AnimateOrbAction(1));
            AbstractDungeon.actionManager.addToBottom(new EvokeOrbAction(1));
        }
    }

    public AbstractCard makeCopy() {
        return new Salvo();
    }

    public void upgrade() {
        if (!this.upgraded) {
            this.upgradeName();

            this.upgradeBaseCost(UPGRADE_COST);
        }
    }
}
