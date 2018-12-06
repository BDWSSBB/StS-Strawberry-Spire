package StrawberrySpireMod.cards.blue;

import com.megacrit.cardcrawl.actions.*;
import com.megacrit.cardcrawl.actions.common.*;
import com.megacrit.cardcrawl.cards.*;
import com.megacrit.cardcrawl.characters.*;
import com.megacrit.cardcrawl.core.*;
import com.megacrit.cardcrawl.dungeons.*;
import com.megacrit.cardcrawl.localization.*;
import com.megacrit.cardcrawl.monsters.*;
import com.megacrit.cardcrawl.ui.panels.*;

import StrawberrySpireMod.cards.*;

public class THEL extends AbstractStrawberrySpireCard {

    public static final String ID = "strawberrySpire:THEL";
    private static final CardStrings CARD_STRINGS = CardCrawlGame.languagePack.getCardStrings(ID);
    public static final String NAME = CARD_STRINGS.NAME;
    public static final String IMAGE_PATH = "cards/blue/THEL.png";
    private static final int COST = 4;
    public static final String DESCRIPTION = CARD_STRINGS.DESCRIPTION;
    private static final int ATTACK_DAMAGE = 30;
    private static final int UPGRADE_PLUS_DAMAGE = 10;

    public THEL() {
        super(ID, NAME, IMAGE_PATH, COST, DESCRIPTION, AbstractCard.CardType.ATTACK, AbstractCard.CardColor.BLUE, AbstractCard.CardRarity.UNCOMMON, AbstractCard.CardTarget.ENEMY);
        this.damage = this.baseDamage = ATTACK_DAMAGE;
    }

    public void triggerAfterCardPlayed() {
        this.cost = 4;
        this.costForTurn = 4;
        this.isCostModified = false;
    }

    public void triggerAtEndOfTurnFromAnywhere() {
        this.updateCost(-EnergyPanel.totalCount);
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        AbstractDungeon.actionManager.addToBottom(new DamageAction(m, new DamageInfo(p, this.damage, this.damageTypeForTurn), AbstractGameAction.AttackEffect.FIRE));
    }

    public AbstractCard makeCopy() {
        return new THEL();
    }

    public void upgrade() {
        if (!this.upgraded) {
            this.upgradeName();

            this.upgradeDamage(UPGRADE_PLUS_DAMAGE);
        }
    }
}
