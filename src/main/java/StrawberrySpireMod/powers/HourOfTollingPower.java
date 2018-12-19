package StrawberrySpireMod.powers;

import com.megacrit.cardcrawl.actions.common.*;
import com.megacrit.cardcrawl.core.*;
import com.megacrit.cardcrawl.dungeons.*;
import com.megacrit.cardcrawl.localization.*;
import com.megacrit.cardcrawl.powers.*;

public class HourOfTollingPower extends AbstractStrawberrySpirePower {

    public static final String POWER_ID = "strawberrySpire:HourOfTolling";
    private static final PowerStrings POWER_STRINGS = CardCrawlGame.languagePack.getPowerStrings(POWER_ID);
    public static final String NAME = POWER_STRINGS.NAME;
    public static final String[] DESCRIPTIONS = POWER_STRINGS.DESCRIPTIONS;
    private int strengthAmount;
    private int numberOfTurns;

    public HourOfTollingPower(AbstractCreature owner, int strengthAmount, int numberOfTurns) {
        this.ID = POWER_ID;
        this.name = NAME;
        this.type = AbstractPower.PowerType.BUFF;
        this.owner = owner;
        this.amount = 1;
        this.strengthAmount = strengthAmount;
        this.numberOfTurns = numberOfTurns;
        updateDescription();
        loadRegion("time");
    }

    public void updateDescription() {
        this.description = DESCRIPTIONS[0] + this.strengthAmount + DESCRIPTIONS[1] + this.numberOfTurns + DESCRIPTIONS[2];
    }

    public void atEndOfRound() {
        flashWithoutSound();
        this.amount++;
        if (this.amount % this.numberOfTurns == 0) {
            this.amount = 0;
            this.playApplyPowerSfx();
            AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(this.owner, this.owner, new StrengthPower(this.owner, this.strengthAmount), this.strengthAmount));
        }
    }
}
