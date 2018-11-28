package StrawberryCardsMod.cards.green;

import com.megacrit.cardcrawl.actions.*;
import com.megacrit.cardcrawl.actions.common.*;
import com.megacrit.cardcrawl.cards.*;
import com.megacrit.cardcrawl.characters.*;
import com.megacrit.cardcrawl.core.*;
import com.megacrit.cardcrawl.dungeons.*;
import com.megacrit.cardcrawl.localization.*;
import com.megacrit.cardcrawl.monsters.*;
import com.megacrit.cardcrawl.powers.*;

import StrawberryCardsMod.cards.*;

public class Trickbox extends AbstractStrawberryCardsCard {

    public static final String ID = "strawberryCards:Trickbox";
    private static final CardStrings CARD_STRINGS = CardCrawlGame.languagePack.getCardStrings(ID);
    public static final String NAME = CARD_STRINGS.NAME;
    public static final String UPGRADE_NAME = CARD_STRINGS.EXTENDED_DESCRIPTION[0];
    public static final String IMAGE_PATH = "cards/green/trickbox.png";
    private static final int COST = 0;
    public static final String DESCRIPTION = CARD_STRINGS.DESCRIPTION;
    private static final int MAGIC_NUMBER = 4;
    private static final int UPGRADE_PLUS_MAGIC_NUMBER = 2;

    public Trickbox() {
        super(ID, NAME, IMAGE_PATH, COST, DESCRIPTION, AbstractCard.CardType.SKILL, AbstractCard.CardColor.GREEN, AbstractCard.CardRarity.UNCOMMON, CardTarget.ALL_ENEMY);
        this.magicNumber = this.baseMagicNumber = MAGIC_NUMBER;
        this.isInnate = true;
        this.exhaust = true;
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        for (AbstractMonster monster : AbstractDungeon.getMonsters().monsters) {
            if ((!monster.isDead) && (!monster.isDying)) {
                AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(monster, p, new PoisonPower(monster, p, this.magicNumber), this.magicNumber, AbstractGameAction.AttackEffect.POISON));
            }
        }
    }

    public AbstractCard makeCopy() {
        return new Trickbox();
    }

    public void upgrade() {
        if (!this.upgraded) {
            this.upgradeName();
            this.name = UPGRADE_NAME;

            this.upgradeMagicNumber(UPGRADE_PLUS_MAGIC_NUMBER);
        }
    }
}
