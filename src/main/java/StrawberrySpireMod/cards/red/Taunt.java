package StrawberrySpireMod.cards.red;

import com.badlogic.gdx.math.*;
import com.megacrit.cardcrawl.actions.animations.*;
import com.megacrit.cardcrawl.actions.common.*;
import com.megacrit.cardcrawl.actions.utility.*;
import com.megacrit.cardcrawl.cards.*;
import com.megacrit.cardcrawl.characters.*;
import com.megacrit.cardcrawl.core.*;
import com.megacrit.cardcrawl.dungeons.*;
import com.megacrit.cardcrawl.localization.*;
import com.megacrit.cardcrawl.monsters.*;
import com.megacrit.cardcrawl.powers.*;

import StrawberrySpireMod.cards.*;

import java.util.ArrayList;

public class Taunt extends AbstractStrawberrySpireCard {

    public static final String ID = "strawberrySpire:Taunt";
    private static final CardStrings CARD_STRINGS = CardCrawlGame.languagePack.getCardStrings(ID);
    public static final String NAME = CARD_STRINGS.NAME;
    public static final String IMAGE_PATH = "StrawberrySpireModResources/cards/red/taunt.png";
    private static final int COST = 0;
    public static final String DESCRIPTION = CARD_STRINGS.DESCRIPTION;
    private static final int MAGIC_NUMBER = 1;
    private static final int UPGRADE_PLUS_MAGIC_NUMBER = 1;


    public Taunt() {
        super(ID, NAME, IMAGE_PATH, COST, DESCRIPTION, AbstractCard.CardType.SKILL, AbstractCard.CardColor.RED, AbstractCard.CardRarity.UNCOMMON, AbstractCard.CardTarget.ENEMY);
        this.magicNumber = this.baseMagicNumber = MAGIC_NUMBER;
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        AbstractDungeon.actionManager.addToBottom(new SFXAction("VO_CHAMP_2A"));
        AbstractDungeon.actionManager.addToBottom(new TalkAction(true, getTaunt(), 0.0F, 2.0F));
        AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(m, p, new WeakPower(m, this.magicNumber, false), this.magicNumber));
        AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(m, p, new VulnerablePower(m, this.magicNumber, false), this.magicNumber));
    }

    private String getTaunt() {
        if (MathUtils.randomBoolean(0.02F)) {
            return CARD_STRINGS.EXTENDED_DESCRIPTION[4];
        }
        ArrayList<String> taunts = new ArrayList();
        taunts.add(CARD_STRINGS.EXTENDED_DESCRIPTION[0]);
        taunts.add(CARD_STRINGS.EXTENDED_DESCRIPTION[1]);
        taunts.add(CARD_STRINGS.EXTENDED_DESCRIPTION[2]);
        taunts.add(CARD_STRINGS.EXTENDED_DESCRIPTION[3]);
        return taunts.get(MathUtils.random(taunts.size() - 1));
    }

    public AbstractCard makeCopy() {
        return new Taunt();
    }

    public void upgrade() {
        if (!this.upgraded) {
            this.upgradeName();

            this.upgradeMagicNumber(UPGRADE_PLUS_MAGIC_NUMBER);
        }
    }
}
