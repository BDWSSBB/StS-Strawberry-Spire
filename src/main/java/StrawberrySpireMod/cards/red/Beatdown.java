package StrawberrySpireMod.cards.red;

import com.megacrit.cardcrawl.cards.*;
import com.megacrit.cardcrawl.characters.*;
import com.megacrit.cardcrawl.core.*;
import com.megacrit.cardcrawl.dungeons.*;
import com.megacrit.cardcrawl.localization.*;
import com.megacrit.cardcrawl.monsters.*;

import StrawberrySpireMod.actions.unique.*;
import StrawberrySpireMod.cards.*;

public class Beatdown extends AbstractStrawberrySpireCard {

    public static final String ID = "strawberrySpire:Beatdown";
    private static final CardStrings CARD_STRINGS = CardCrawlGame.languagePack.getCardStrings(ID);
    public static final String NAME = CARD_STRINGS.NAME;
    public static final String IMAGE_PATH = "cards/red/beatdown.png";
    private static final int COST = -1;
    public static final String DESCRIPTION = CARD_STRINGS.DESCRIPTION;
    private static final int ATTACK_DAMAGE = 4;
    private static final int UPGRADE_PLUS_DAMAGE = 2;

    public Beatdown() {
        super(ID, NAME, IMAGE_PATH, COST, DESCRIPTION, AbstractCard.CardType.ATTACK, AbstractCard.CardColor.RED, AbstractCard.CardRarity.RARE, AbstractCard.CardTarget.ENEMY);
        this.damage = this.baseDamage = ATTACK_DAMAGE;
        this.exhaust = true;
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        AbstractDungeon.actionManager.addToBottom(new BeatdownAction(m, new DamageInfo(p, this.damage, this.damageTypeForTurn), this.freeToPlayOnce, this.energyOnUse));
    }

    public AbstractCard makeCopy() {
        return new Beatdown();
    }

    public void upgrade() {
        if (!this.upgraded) {
            this.upgradeName();

            this.upgradeDamage(UPGRADE_PLUS_DAMAGE);
        }
    }
}
