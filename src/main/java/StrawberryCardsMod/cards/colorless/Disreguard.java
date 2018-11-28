package StrawberryCardsMod.cards.colorless;

import com.megacrit.cardcrawl.actions.common.*;
import com.megacrit.cardcrawl.cards.*;
import com.megacrit.cardcrawl.characters.*;
import com.megacrit.cardcrawl.core.*;
import com.megacrit.cardcrawl.dungeons.*;
import com.megacrit.cardcrawl.localization.*;
import com.megacrit.cardcrawl.monsters.*;
import com.megacrit.cardcrawl.powers.*;

import StrawberryCardsMod.cards.*;

public class Disreguard extends AbstractStrawberryCardsCard {

    public static final String ID = "strawberryCards:Disreguard";
    private static final CardStrings CARD_STRINGS = CardCrawlGame.languagePack.getCardStrings(ID);
    public static final String NAME = CARD_STRINGS.NAME;
    public static final String UPGRADE_NAME = CARD_STRINGS.EXTENDED_DESCRIPTION[0];
    public static final String IMAGE_PATH = "cards/colorless/disreguard.png";
    private static final int COST = 1;
    public static final String DESCRIPTION = CARD_STRINGS.DESCRIPTION;
    private static final int BLOCK_AMOUNT = 7;
    private static final int UPGRADE_PLUS_BLOCK = 3;

    public Disreguard() {
        super(ID, NAME, IMAGE_PATH, COST, DESCRIPTION, AbstractCard.CardType.SKILL, AbstractCard.CardColor.COLORLESS, AbstractCard.CardRarity.UNCOMMON, AbstractCard.CardTarget.SELF);
        this.block = this.baseBlock = BLOCK_AMOUNT;
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        AbstractDungeon.actionManager.addToBottom(new GainBlockAction(p, p, this.block));
        AbstractDungeon.actionManager.addToBottom(new RemoveSpecificPowerAction(p, p, FrailPower.POWER_ID));
        AbstractDungeon.actionManager.addToBottom(new RemoveSpecificPowerAction(p, p, VulnerablePower.POWER_ID));
    }

    public AbstractCard makeCopy() {
        return new Disreguard();
    }

    public void upgrade() {
        if (!this.upgraded) {
            this.upgradeName();
            this.name = UPGRADE_NAME;

            this.upgradeBlock(UPGRADE_PLUS_BLOCK);
        }
    }
}
