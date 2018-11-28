package StrawberryCardsMod.cards.green;

import com.megacrit.cardcrawl.actions.*;
import com.megacrit.cardcrawl.actions.common.*;
import com.megacrit.cardcrawl.cards.*;
import com.megacrit.cardcrawl.characters.*;
import com.megacrit.cardcrawl.core.*;
import com.megacrit.cardcrawl.dungeons.*;
import com.megacrit.cardcrawl.localization.*;
import com.megacrit.cardcrawl.monsters.*;

import StrawberryCardsMod.cards.*;
import StrawberryCardsMod.powers.*;

public class Plunge extends AbstractStrawberryCardsCard {

    public static final String ID = "strawberryCards:Plunge";
    private static final CardStrings CARD_STRINGS = CardCrawlGame.languagePack.getCardStrings(ID);
    public static final String NAME = CARD_STRINGS.NAME;
    public static final String IMAGE_PATH = "cards/green/plunge.png";
    private static final int COST = 2;
    public static final String DESCRIPTION = CARD_STRINGS.DESCRIPTION;
    private static final int ATTACK_DAMAGE = 12;
    private static final int UPGRADE_PLUS_DAMAGE = 5;

    public Plunge() {
        super(ID, NAME, IMAGE_PATH, COST, DESCRIPTION, AbstractCard.CardType.ATTACK, AbstractCard.CardColor.GREEN, AbstractCard.CardRarity.UNCOMMON, AbstractCard.CardTarget.ENEMY);
        this.damage = this.baseDamage = ATTACK_DAMAGE;
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        AbstractDungeon.actionManager.addToBottom(new DamageAction(m, new DamageInfo(p, this.damage, this.damageTypeForTurn), AbstractGameAction.AttackEffect.SLASH_HEAVY));
        AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(m, p, new PlungePower(m)));
    }

    public AbstractCard makeCopy() {
        return new Plunge();
    }

    public void upgrade() {
        if (!this.upgraded) {
            this.upgradeName();

            this.upgradeDamage(UPGRADE_PLUS_DAMAGE);
        }
    }
}
