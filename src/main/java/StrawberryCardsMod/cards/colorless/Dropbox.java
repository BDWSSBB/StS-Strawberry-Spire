package StrawberryCardsMod.cards.colorless;

import com.megacrit.cardcrawl.actions.*;
import com.megacrit.cardcrawl.actions.common.*;
import com.megacrit.cardcrawl.cards.*;
import com.megacrit.cardcrawl.characters.*;
import com.megacrit.cardcrawl.core.*;
import com.megacrit.cardcrawl.dungeons.*;
import com.megacrit.cardcrawl.localization.*;
import com.megacrit.cardcrawl.monsters.*;

import StrawberryCardsMod.cards.*;

public class Dropbox extends AbstractStrawberryCardsCard {

    public static final String ID = "strawberryCards:Dropbox";
    private static final CardStrings CARD_STRINGS = CardCrawlGame.languagePack.getCardStrings(ID);
    public static final String NAME = CARD_STRINGS.NAME;
    public static final String IMAGE_PATH = "cards/colorless/dropbox.png";
    private static final int COST = 3;
    public static final String DESCRIPTION = CARD_STRINGS.DESCRIPTION;
    public static final String UPGRADE_DESCRIPTION = CARD_STRINGS.UPGRADE_DESCRIPTION;
    private static final int ATTACK_DAMAGE = 18;
    private static final int UPGRADE_PLUS_DAMAGE = 6;
    private static final int MAGIC_NUMBER = 1;
    private static final int UPGRADE_PLUS_MAGIC_NUMBER = 1;

    public Dropbox() {
        super(ID, NAME, IMAGE_PATH, COST, DESCRIPTION, AbstractCard.CardType.ATTACK, AbstractCard.CardColor.COLORLESS, AbstractCard.CardRarity.RARE, AbstractCard.CardTarget.ENEMY);
        this.damage = this.baseDamage = ATTACK_DAMAGE;
        this.magicNumber = this.baseMagicNumber = MAGIC_NUMBER;
        this.exhaust = true;
        this.tags.add(CardTags.HEALING);
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        for (int i = 0; i < this.magicNumber; i++) { // This has to be first because potions don't spawn if Dropbox is lethal.
            AbstractDungeon.actionManager.addToBottom(new ObtainPotionAction(AbstractDungeon.returnRandomPotion(true)));
        }
        AbstractDungeon.actionManager.addToBottom(new DamageAction(m, new DamageInfo(p, this.damage, this.damageTypeForTurn), AbstractGameAction.AttackEffect.BLUNT_HEAVY));
        AbstractDungeon.actionManager.addToBottom(new HealAction(p, p, 6));
    }

    public AbstractCard makeCopy() {
        return new Dropbox();
    }

    public void upgrade() {
        if (!this.upgraded) {
            this.upgradeName();
            this.rawDescription = UPGRADE_DESCRIPTION;
            initializeDescription();

            this.upgradeDamage(UPGRADE_PLUS_DAMAGE);
            this.upgradeMagicNumber(UPGRADE_PLUS_MAGIC_NUMBER);
        }
    }
}