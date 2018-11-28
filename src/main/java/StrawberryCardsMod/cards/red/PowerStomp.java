package StrawberryCardsMod.cards.red;

import com.megacrit.cardcrawl.actions.*;
import com.megacrit.cardcrawl.actions.common.*;
import com.megacrit.cardcrawl.cards.*;
import com.megacrit.cardcrawl.characters.*;
import com.megacrit.cardcrawl.core.*;
import com.megacrit.cardcrawl.dungeons.*;
import com.megacrit.cardcrawl.localization.*;
import com.megacrit.cardcrawl.monsters.*;

import StrawberryCardsMod.actions.unique.*;
import StrawberryCardsMod.cards.*;

public class PowerStomp extends AbstractStrawberryCardsCard {

    public static final String ID = "strawberryCards:PowerStomp";
    private static final CardStrings CARD_STRINGS = CardCrawlGame.languagePack.getCardStrings(ID);
    public static final String NAME = CARD_STRINGS.NAME;
    public static final String IMAGE_PATH = "cards/red/powerStomp.png";
    private static final int COST = 2;
    public static final String DESCRIPTION = CARD_STRINGS.DESCRIPTION;
    private static final int ATTACK_DAMAGE = 14;
    private static final int UPGRADE_PLUS_DAMAGE = 3;
    private static final int MAGIC_NUMBER = 8;
    private static final int UPGRADE_PLUS_MAGIC_NUMBER = 3;

    public PowerStomp() {
        super(ID, NAME, IMAGE_PATH, COST, DESCRIPTION, AbstractCard.CardType.ATTACK, AbstractCard.CardColor.RED, AbstractCard.CardRarity.UNCOMMON, AbstractCard.CardTarget.ENEMY);
        this.damage = this.baseDamage = ATTACK_DAMAGE;
        this.magicNumber = this.baseMagicNumber = MAGIC_NUMBER;
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        AbstractDungeon.actionManager.addToBottom(new DamageAction(m, new DamageInfo(p, this.damage, this.damageTypeForTurn), AbstractGameAction.AttackEffect.BLUNT_HEAVY));
        AbstractDungeon.actionManager.addToBottom(new PowerStompAction(m, this.magicNumber));
    }

    public AbstractCard makeCopy() {
        return new PowerStomp();
    }

    public void upgrade() {
        if (!this.upgraded) {
            this.upgradeName();

            this.upgradeDamage(UPGRADE_PLUS_DAMAGE);
            this.upgradeMagicNumber(UPGRADE_PLUS_MAGIC_NUMBER);
        }
    }
}
