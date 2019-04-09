package StrawberrySpireMod.powers;

import com.megacrit.cardcrawl.actions.utility.*;
import com.megacrit.cardcrawl.cards.*;
import com.megacrit.cardcrawl.core.*;
import com.megacrit.cardcrawl.dungeons.*;
import com.megacrit.cardcrawl.localization.*;
import com.megacrit.cardcrawl.powers.*;

import StrawberrySpireMod.actions.unique.*;

public class MinuteOfSilencePower extends AbstractStrawberrySpirePower {

    public static final String POWER_ID = "strawberrySpire:MinuteOfSilence";
    private static final PowerStrings POWER_STRINGS = CardCrawlGame.languagePack.getPowerStrings(POWER_ID);
    public static final String NAME = POWER_STRINGS.NAME;
    public static final String[] DESCRIPTIONS = POWER_STRINGS.DESCRIPTIONS;
    private int numberOfCards;
    private int strengthDownAmount;

    public MinuteOfSilencePower(AbstractCreature owner, int numberOfCards, int strengthDownAmount) {
        this.ID = POWER_ID;
        this.name = NAME;
        this.type = AbstractPower.PowerType.BUFF;
        this.owner = owner;
        this.amount = 0;
        this.numberOfCards = numberOfCards;
        this.strengthDownAmount = strengthDownAmount;
        updateDescription();
        loadRegion("time");
    }

    public void updateDescription() {
        this.description = DESCRIPTIONS[0] + this.numberOfCards + DESCRIPTIONS[1] + this.strengthDownAmount + DESCRIPTIONS[2];
    }

    public void onUseCard(AbstractCard card, UseCardAction action) {
        flashWithoutSound();
        this.amount++;
        if (this.amount % this.numberOfCards == 0) {
            this.amount = 0;
            this.playApplyPowerSfx();
            AbstractDungeon.actionManager.addToBottom(new AddActionLaterAction
                    (new ApplyDebuffAndInverseAction(AbstractDungeon.player, this.owner,
                            new StrengthPower(AbstractDungeon.player, -this.strengthDownAmount), -this.strengthDownAmount,
                            new GainStrengthPower(AbstractDungeon.player, this.strengthDownAmount), this.strengthDownAmount), 1));
        }
    }
}
